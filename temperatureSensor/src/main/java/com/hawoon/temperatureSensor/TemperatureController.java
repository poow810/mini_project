package com.hawoon.temperatureSensor;

import org.springframework.beans.factory.annotation.Autowired;
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
    public String displayTemperature(Model model) {
        TemperatureDto temperatureDto = temperatureService.fetchTemperatureFromArduino();
        if (temperatureDto != null) {
            double celsius = temperatureDto.getCelsius();
            double fahrenheit = temperatureDto.getFahrenheit();
            String arduinoIP = "http://165.246.116.118/";

            model.addAttribute("celsius", celsius);
            model.addAttribute("fahrenheit", fahrenheit);
            model.addAttribute("arduinoIP", arduinoIP);

            temperatureService.saveTemperature(temperatureDto);
        }
        return "templates/test/temperature.html";
    }
}



