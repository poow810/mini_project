package com.hawoon.temperatureSensor;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TemperatureService {

    private static final String ARDUINO_URL = "http://165.246.116.81/"; // 통신을 위한 URL

    private final TemperatureRepository temperatureRepository;

    public TemperatureEntity save(TemperatureDto dto){
        return temperatureRepository.save(dto.toEntity());
    }
    public List<TemperatureEntity> getAllTemperatures() {
        return temperatureRepository.findAll();
    }


}
