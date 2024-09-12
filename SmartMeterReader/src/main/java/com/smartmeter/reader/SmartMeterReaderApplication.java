package com.smartmeter.reader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SmartMeterReaderApplication {
	public static void main(String[] args) {
		SpringApplication.run(SmartMeterReaderApplication.class, args);
	}
}
