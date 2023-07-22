package com.hawoon.temperatureSensor.controller;

import com.hawoon.temperatureSensor.TemperatureDto;
import com.hawoon.temperatureSensor.TemperatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class TemperatureController {

    private final TemperatureService temperatureService;

    @Autowired
    public TemperatureController(TemperatureService temperatureService) {
        this.temperatureService = temperatureService;
    }


    @GetMapping("/test")
    public TemperatureDto displayTemperature(Model model) {
        return temperatureService.fetchTemperatureFromArduino();
    }

    //
    @GetMapping("/measure")
    public List<TemperatureDto> getMeasuredTemperature() {
        return temperatureService.getTemperature();
    }
}



