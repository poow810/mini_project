package com.hawoon.temperatureSensor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Service
public class TemperatureService {

    private static final String ARDUINO_URL = "http://165.246.116.168:80/"; // 통신을 위한 URL
    private final RestTemplate restTemplate;
    private final TemperatureRepository temperatureRepository;

    @Autowired
    public TemperatureService(TemperatureRepository temperatureRepository) {
        this.restTemplate = new RestTemplate();
        this.temperatureRepository = temperatureRepository;
    }

    public void saveTemperature(TemperatureDto temperatureDto) {
        if (temperatureDto != null) {
            temperatureRepository.save(temperatureDto.toEntity());
        }
    }

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

    private TemperatureDto parseTemperatureResponse(String responseBody) {
        String temperatureLine = responseBody.split("\n")[3];
        String[] tempValues = temperatureLine.split(" ")[1].split(":");

            double c = Double.parseDouble(tempValues[0]);
            double f = Double.parseDouble(tempValues[1]);

            LocalDateTime now = LocalDateTime.now();

            TemperatureDto parsingData = new TemperatureDto(c, f, now);
            return parsingData;
    }


    private TemperatureEntity convertToEntity(TemperatureDto dto) {
        TemperatureEntity entity = new TemperatureEntity();
        entity.setCelsius(dto.getCelsius());
        entity.setFahrenheit(dto.getFahrenheit());
        return entity;

    }
}