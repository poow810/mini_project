package com.hawoon.temperatureSensor;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TemperatureDto {
    private double celsius;
    private double fahrenheit;

    public TemperatureEntity toEntity() {
        return TemperatureEntity.builder()
                .celsius(celsius)
                .fahrenheit(fahrenheit).build();

    }
}
