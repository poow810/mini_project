package com.hawoon.temperatureSensor.controller;

import com.hawoon.temperatureSensor.TemperatureDto;
import com.hawoon.temperatureSensor.TemperatureService;
import lombok.experimental.PackagePrivate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
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
//    @GetMapping("/measure")
//    public List<TemperatureDto> getMeasuredTemperature() {
//        return temperatureService.getTemperature();
//    }

    @GetMapping("/measure")
    public String showTemperatureData(Model model) {
        List<TemperatureDto> data = temperatureService.getTemperature();
        model.addAttribute("data", data);
        return "test/temperature";
    }

    @GetMapping("/measure/data")
    @ResponseBody
    public List<TemperatureDto> getMeasuredTemperature() {
        return temperatureService.getTemperature();
    }
}



