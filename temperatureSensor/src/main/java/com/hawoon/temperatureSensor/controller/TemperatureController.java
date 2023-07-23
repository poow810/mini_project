package com.hawoon.temperatureSensor.controller;

import com.hawoon.temperatureSensor.Dto.AvgTemperatureDto;
import com.hawoon.temperatureSensor.Dto.TemperatureDto;
import com.hawoon.temperatureSensor.TemperatureService;
import lombok.experimental.PackagePrivate;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/average")
    public String average(Model model) {
        List<AvgTemperatureDto> avgData = temperatureService.getAverageTemperaturePerDay();
        model.addAttribute("averageTemperatureTable", avgData);
        return "test/average";
    }

    @GetMapping("/average/avgData")
    @ResponseBody
    public List<AvgTemperatureDto> getAvgTemperature() {
        return temperatureService.getAverageTemperaturePerDay();
    }
}



