package com.hawoon.temperatureSensor.controller;


import com.hawoon.temperatureSensor.TemperatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InitialPageController {

    private final TemperatureService temperatureService;

    @Autowired
    public InitialPageController(TemperatureService temperatureService){
        this.temperatureService = temperatureService;
    }
    @GetMapping("/") // 기본 페이지
    public String homePage() {
        temperatureService.fetchTemperatureFromArduino();

        return "test/index.html";
    }
}
