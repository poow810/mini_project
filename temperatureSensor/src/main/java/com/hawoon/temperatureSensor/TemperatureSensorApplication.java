package com.hawoon.temperatureSensor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TemperatureSensorApplication {

	public static void main(String[] args) {
		SpringApplication.run(TemperatureSensorApplication.class, args);
	}

}
