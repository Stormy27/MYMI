package com.smartmeter.reader.mapping;

import com.smartmeter.reader.dto.MeasurementResultDTO;
import com.smartmeter.reader.model.Measurement;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Executable;
import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class MeasurementMapperTest {

    public static final String TIMESTAMP_00 = "2024-09-01T00:00:00+02:00[Europe/Prague]";
    public static final String TIMESTAMP_15 = "2024-09-01T00:15:00+02:00[Europe/Prague]";

    @Test
    public void testFromSmartMeterMeasurementDTO() {
        Map<String, Double> voltageMap = Map.of(TIMESTAMP_00, 230.0, TIMESTAMP_15, 235.0);
        Map<String, Double> currentMap =Map.of("2024-09-01T00:00:00+02:00[Europe/Prague]", 10.0, "2024-09-01T00:15:00+02:00[Europe/Prague]", 11.0);

        MeasurementResultDTO dto = new MeasurementResultDTO();
        dto.setVoltage(voltageMap);
        dto.setCurrent(currentMap);

        List<Measurement> measurements = MeasurementMapper.fromSmartMeterMeasurementDTO(dto);

        assertEquals(2, measurements.size(), "Should have two measurements");

        Measurement measurement1 = measurements.stream()
            .filter(m -> m.getTimestamp().equals(ZonedDateTime.parse("2024-09-01T00:00:00+02:00[Europe/Prague]", DateTimeFormatter.ISO_ZONED_DATE_TIME)))
            .findFirst()
            .get();

        assertEquals(ZonedDateTime.parse("2024-09-01T00:00:00+02:00[Europe/Prague]", DateTimeFormatter.ISO_ZONED_DATE_TIME), measurement1.getTimestamp(), "Timestamp mismatch for measurement 1");
        assertEquals(230.0, measurement1.getVoltage(), "Voltage mismatch for measurement 1");
        assertEquals(10.0, measurement1.getCurrent(), "Current mismatch for measurement 1");

        Measurement measurement2 = measurements.stream()
            .filter(m -> m.getTimestamp().equals(ZonedDateTime.parse("2024-09-01T00:15:00+02:00[Europe/Prague]", DateTimeFormatter.ISO_ZONED_DATE_TIME)))
            .findFirst()
            .get();

        assertEquals(ZonedDateTime.parse("2024-09-01T00:15:00+02:00[Europe/Prague]", DateTimeFormatter.ISO_ZONED_DATE_TIME), measurement2.getTimestamp(), "Timestamp mismatch for measurement 2");
        assertEquals(235.0, measurement2.getVoltage(), "Voltage mismatch for measurement 2");
        assertEquals(11.0, measurement2.getCurrent(), "Current mismatch for measurement 2");
    }

    @Test
    public void testFromSmartMeterMeasurementDTO_throwsNoSuchElementException() {
        MeasurementResultDTO dto = new MeasurementResultDTO();

        Map<String, Double> voltageMap = new LinkedHashMap<>();
        voltageMap.put(TIMESTAMP_00, 230.0);

        dto.setVoltage(voltageMap);
        dto.setCurrent(new LinkedHashMap<>());

        assertThrows(NoSuchElementException.class,
                () -> MeasurementMapper.fromSmartMeterMeasurementDTO(dto),
                "Expected NoSuchElementException to be thrown"
        );
    }
}
