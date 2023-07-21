package com.hawoon.temperatureSensor;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Service
public class TemperatureService {

    private static final String ARDUINO_URL = "http://165.246.116.168/"; // 통신을 위한 URL
    private final RestTemplate restTemplate;
    private final TemperatureRepository temperatureRepository;

    public TemperatureService(TemperatureRepository temperatureRepository) {
        this.restTemplate = new RestTemplate();
        this.temperatureRepository = temperatureRepository;
    }

    public void saveTemperature(TemperatureDto temperatureDto) {
        if (temperatureDto != null) {
            temperatureRepository.save(temperatureDto.toEntity());

//            TemperatureEntity temperatureEntity = convertToEntity(temperatureDto);
//            temperatureRepository.save(temperatureEntity);
        }
    }

    public TemperatureDto fetchTemperatureFromArduino() {
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(ARDUINO_URL, String.class);
            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                return response.getBody();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private TemperatureEntity convertToEntity(TemperatureDto dto) {
        TemperatureEntity entity = new TemperatureEntity();
        entity.setTimestamp(LocalDateTime.now());
        entity.setCelsius(dto.getCelsius());
        entity.setFahrenheit(dto.getFahrenheit());
        return entity;

    }
}