package com.hawoon.temperatureSensor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


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
}



