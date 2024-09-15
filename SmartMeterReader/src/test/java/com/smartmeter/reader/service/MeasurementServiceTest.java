package com.smartmeter.reader.service;

import com.smartmeter.reader.dto.MeasurementResultDTO;
import com.smartmeter.reader.model.Measurement;
import com.smartmeter.reader.model.SmartMeter;
import com.smartmeter.reader.repository.MeasurementRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@SpringBootTest
public class MeasurementServiceTest {

    @Mock
    private MeasurementRepository measurementRepository;

    @Mock
    private SmartMeterService smartMeterService;

    @InjectMocks
    private MeasurementService measurementService;

    @Test
    public void testSaveMeasurement_Success() {
        String smartMeterId = "123";
        MeasurementResultDTO measurementResultDTO = new MeasurementResultDTO();
        measurementResultDTO.setId(smartMeterId);
        measurementResultDTO.setVoltage(Map.of("2024-09-12T01:00:00Z", 230.0));
        measurementResultDTO.setCurrent(Map.of("2024-09-12T01:00:00Z", 10.0));

        SmartMeter smartMeter = new SmartMeter();
        smartMeter.setId(smartMeterId);

        when(smartMeterService.findById(smartMeterId)).thenReturn(Optional.of(smartMeter));

        Measurement measurement = new Measurement();
        measurement .setSmartMeter(smartMeter);

        when(measurementRepository.saveAll(anyList())).thenReturn(List.of(measurement));

        List<Measurement> savedMeasurements = measurementService.saveMeasurements(measurementResultDTO);

        assertNotNull(savedMeasurements, "Saved measurements should not be null");
        assertEquals(1, savedMeasurements.size(), "There should be one measurement saved");
        assertSame(smartMeter, savedMeasurements.get(0).getSmartMeter(), "Smart meter should be set");
    }

    @Test
    public void testSaveMeasurement_SmartMeterNotFound() {
        String smartMeterId = "123";
        MeasurementResultDTO measurementResultDTO = new MeasurementResultDTO();
        measurementResultDTO.setId(smartMeterId);

        when(smartMeterService.findById(smartMeterId)).thenReturn(Optional.empty());

        NoSuchElementException thrown = assertThrows(
                NoSuchElementException.class,
                () -> measurementService.saveMeasurements(measurementResultDTO),
                "Expected saveMeasurement to throw, but it didn't"
        );

        assertTrue(thrown.getMessage().contains(String.format("Can't found SmartMeter with id: %s", smartMeterId)));
    }
}
