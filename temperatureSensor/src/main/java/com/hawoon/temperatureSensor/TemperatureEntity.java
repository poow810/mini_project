package com.hawoon.temperatureSensor;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Entity
public class TemperatureEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double celsius;
    private double fahrenheit;
    private LocalDateTime timeStamp;

    @Builder
    public TemperatureEntity(double celsius, double fahrenheit, LocalDateTime timeStamp) {
        this.celsius = celsius;
        this.fahrenheit = fahrenheit;
        this.timeStamp = timeStamp;
    }
}