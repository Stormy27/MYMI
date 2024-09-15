package com.smartmeter.reader.service;

import com.smartmeter.reader.dto.MeasurementResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;


@Component
public class SmartReaderScheduler {
        private final WebClient webClient;
        private final MeasurementService measurementService;

        @Autowired
        public SmartReaderScheduler(WebClient webClient, MeasurementService measurementService) {
            this.webClient = webClient;
            this.measurementService = measurementService;
        }

        /**
         * Scheduled task to read data from smart meters and save the measurements.
         * The task runs every 10 minutes at the start of the hour
         */
        @Scheduled(cron = "10 1-23 * * *")
        public void readSmartMeterData() {
            MeasurementResultDTO measurementResultDTO = webClient.get()
                .uri("/read")
                .retrieve()
                .bodyToMono(MeasurementResultDTO.class)
                .block();

            measurementService.saveMeasurement(measurementResultDTO);
        }
}
