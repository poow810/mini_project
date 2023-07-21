package com.hawoon.temperatureSensor;


import lombok.*;
import org.w3c.dom.html.HTMLImageElement;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class TemperatureDto {
    private double celsius;
    private double fahrenheit;
    private LocalDateTime timeStamp;

    public TemperatureEntity toEntity() {
        return TemperatureEntity.builder()
                .celsius(celsius)
                .fahrenheit(fahrenheit)
                .timeStamp(timeStamp).build();
    }
}
