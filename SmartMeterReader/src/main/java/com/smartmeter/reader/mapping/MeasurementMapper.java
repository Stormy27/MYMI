package com.smartmeter.reader.mapping;

import com.smartmeter.reader.dto.MeasurementResultDTO;
import com.smartmeter.reader.model.Measurement;

import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

public class MeasurementMapper {
    private MeasurementMapper() {
        throw new UnsupportedOperationException("Cannot instantiate MeasurementMapper");
    }

    /**
     * Converts a {@link MeasurementResultDTO} into a list of {@link Measurement} entities.
     *
     * @param measurementResultDTO the DTO containing voltage and current
     * @return a list of {@link Measurement} list of entities
     * @throws NoSuchElementException if a corresponding current value cannot be found for a specific timestamp
     */
    public static List<Measurement> fromSmartMeterMeasurementDTO(MeasurementResultDTO measurementResultDTO) {
        validate(measurementResultDTO);

        List<Measurement> measurements = new LinkedList<>();
        for (var entry : measurementResultDTO.getVoltage().entrySet()) {
            Measurement measurement = new Measurement();
            measurement.setTimestamp(ZonedDateTime.parse(entry.getKey()));
            measurement.setVoltage(entry.getValue());

            measurements.add(measurement);
        }

        for (var entry : measurementResultDTO.getCurrent().entrySet()) {
            Measurement measurement = measurements.stream()
                .filter(x -> x.getTimestamp().equals(ZonedDateTime.parse(entry.getKey())))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Can't find current value for time: " + entry.getValue()));

            measurement.setCurrent(entry.getValue());
        }

        return measurements;
    }

    private static void validate(MeasurementResultDTO measurementResultDTO) {
        if (measurementResultDTO.getVoltage() == null || measurementResultDTO.getVoltage().isEmpty()) {
            throw new NoSuchElementException("Measurement voltage data are empty.");
        }

        if (measurementResultDTO.getCurrent() == null || measurementResultDTO.getCurrent().isEmpty()) {
            throw new NoSuchElementException("Measurement current data are empty.");
        }

        if (measurementResultDTO.getVoltage().size() != measurementResultDTO.getCurrent().size()) {
            throw new NoSuchElementException("Measurement data are inconsistents.");
        }
    }
}
