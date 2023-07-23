package com.hawoon.temperatureSensor;

import com.hawoon.temperatureSensor.Dto.AvgTemperatureDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TemperatureRepository extends JpaRepository<TemperatureEntity, Long> {
    @Query("SELECT NEW com.hawoon.temperatureSensor.Dto.AvgTemperatureDto(AVG(te.celsius), AVG(te.fahrenheit), DATE(te.timeStamp)) FROM TemperatureEntity te GROUP BY DATE(te.timeStamp)")
    List<AvgTemperatureDto> findAverageTemperaturePerDay();
}


