package com.hawoon.temperatureSensor;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RequiredArgsConstructor
@RestController
public class TemperatureController {

    private final TemperatureService temperatureService;

    @PostMapping("/api/temperatures")
    public ResponseEntity<TemperatureEntity> addTemperature(@RequestBody TemperatureDto dto) {
        TemperatureEntity savedTemperature = temperatureService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedTemperature);
    }



}