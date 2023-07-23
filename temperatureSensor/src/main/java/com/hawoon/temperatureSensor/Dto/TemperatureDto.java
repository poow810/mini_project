package com.hawoon.temperatureSensor.Dto;


import com.hawoon.temperatureSensor.TemperatureEntity;
import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class TemperatureDto {

    // 온도 data
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
