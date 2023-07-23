package com.hawoon.temperatureSensor;

import com.hawoon.temperatureSensor.Dto.AvgTemperatureDto;
import com.hawoon.temperatureSensor.Dto.TemperatureDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TemperatureService {

    private static final String ARDUINO_URL = "http://192.168.0.3:80/"; // 통신을 위한 URL
    private final RestTemplate restTemplate;
    private final TemperatureRepository temperatureRepository;

    @Autowired
    public TemperatureService(TemperatureRepository temperatureRepository) {
        this.restTemplate = new RestTemplate();
        this.temperatureRepository = temperatureRepository;
    }

    // 전달된 온도 값을 Repository에 저장
    public void saveTemperature(TemperatureDto temperatureDto) {
        if (temperatureDto != null) {
            temperatureRepository.save(temperatureDto.toEntity());
        }
    }

    // 온도 목록 조회
    public List<TemperatureDto> getTemperature() { // Repository에서 가져온 데이터(엔티티 -> DTO)로 변환하며 캡슐화
        return temperatureRepository.findAll().stream() // 서비스 계층에서는 직접적인 엔티티로 반환하는 것보다 DTO를 이용해 캡슐화
                .map(this::convertToDto).collect(Collectors.toList());
    }


    // Arduino의 데이터를 받아오기 위한 메서드
    @Scheduled(fixedRate = 5000)
    public TemperatureDto fetchTemperatureFromArduino() {
        TemperatureDto parsingData = null;
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(ARDUINO_URL, String.class);
            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                String responseBody = response.getBody();
                parsingData = parseTemperatureResponse(responseBody);
            } else {
                System.out.println("Failed to fetch data from Arduino server.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (parsingData != null) {
            parsingData.setTimeStamp(LocalDateTime.now());
            saveTemperature(parsingData);
        }
        return parsingData;
    }

    // json 형식으로 parsing
    private TemperatureDto parseTemperatureResponse(String responseBody) {
        String temperatureLine = responseBody.split("\n")[3];
        String[] tempValues = temperatureLine.split(" ")[1].split(":");

        double c = Double.parseDouble(tempValues[0]);
        double f = Double.parseDouble(tempValues[1]);

        LocalDateTime now = LocalDateTime.now();

        TemperatureDto parsingData = new TemperatureDto(c, f, now);
        return parsingData;
    }

    private TemperatureDto convertToDto(TemperatureEntity entity) {
        return new TemperatureDto(entity.getCelsius(), entity.getFahrenheit(), entity.getTimeStamp());
    }

    public List<AvgTemperatureDto> getAverageTemperaturePerDay() {
        return temperatureRepository.findAverageTemperaturePerDay();
    }
}