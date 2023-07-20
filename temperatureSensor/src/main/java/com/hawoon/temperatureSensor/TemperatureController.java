package com.hawoon.temperatureSensor;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/temperature")
public class TemperatureController {

    @Autowired
    private TemperatureService temperatureService;
    @Autowired
    private TemperatureRepository temperatureRepository;

//    @GetMapping
//    public ResponseEntity<TemperatureDto> getTemperature(){
//        return temperatureRepository
//    }
}