package com.hawoon.temperatureSensor;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Entity
public class TemperatureEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ipAddress;
    private double celsius;
    private double fahrenheit;
    private LocalDateTime timestamp;

    @Builder
    public TemperatureEntity(Long id, String ipAddress, double celsius, double fahrenheit) {
        this.id = id;
        this.ipAddress = ipAddress;
        this.celsius = celsius;
        this.fahrenheit = fahrenheit;
    }


}