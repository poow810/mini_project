package com.hawoon.temperatureSensor;


import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class TemperatureDto {
    private Long id;
    private double celsius;
    private double fahrenheit;
    private String ipAddress;
    private LocalDateTime timestamp;

    public TemperatureEntity toEntity(){
        TemperatureEntity temperatureEntity = TemperatureEntity.builder()
                .id(id)
                .ipAddress(ipAddress)
                .celsius(celsius)
                .fahrenheit(fahrenheit)
                .build();
        return temperatureEntity;
    }

    @Builder
    public TemperatureDto (Long id, String ipAddress, double celsius, double fahrenheit, LocalDateTime timestamp) {
        this.id = id;
        this.ipAddress = ipAddress;
        this.celsius = celsius;
        this.fahrenheit = fahrenheit;
        this.timestamp = timestamp;
    }
}
