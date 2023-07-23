package com.hawoon.temperatureSensor.Dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AvgTemperatureDto {
    // 평균 온도 data
    private double avgCelsius;
    private double avgFahrenheit;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate timeStamp;

}
