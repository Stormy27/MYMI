package com.smartmeter.reader.service;

import com.smartmeter.reader.dto.MeasurementResultDTO;
import com.smartmeter.reader.model.Measurement;
import com.smartmeter.reader.model.SmartMeter;
import com.smartmeter.reader.repository.MeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

import static com.smartmeter.reader.mapping.MeasurementMapper.fromSmartMeterMeasurementDTO;

@Service
public class MeasurementService {
    private static final String CANT_FOUND_SMART_MSG = "Can't found SmartMeter with id: %s";
    private final MeasurementRepository measurementRepository;
    private final SmartMeterService smartMeterService;

    @Autowired
    public MeasurementService(MeasurementRepository measurementRepository, SmartMeterService smartMeterService) {
        this.measurementRepository = measurementRepository;
        this.smartMeterService = smartMeterService;
    }

    /**
     * Saves a list of {@link Measurement} entities created from a {@link MeasurementResultDTO}.
     *
     * @param measurementResult the DTO containing measurement data and the smart meter ID
     * @return a list of saved {@link Measurement} entities
     * @throws NoSuchElementException if the smart meter with the given ID cannot be found in DB
     */
    public List<Measurement> saveMeasurements(MeasurementResultDTO measurementResult) {
        final String smartMeterId = measurementResult.getId();
        SmartMeter smartMeter = smartMeterService.findById(smartMeterId)
                .orElseThrow(() -> new NoSuchElementException(String.format(CANT_FOUND_SMART_MSG, smartMeterId)));

        List<Measurement> measurements = fromSmartMeterMeasurementDTO(measurementResult);
        measurements.forEach(m -> m.setSmartMeter(smartMeter));

        return measurementRepository.saveAll(measurements);
    }
}
