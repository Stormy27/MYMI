package com.smartmeter.reader.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;

@NoArgsConstructor
@Data
public class MeasurementResultDTO implements Serializable {
    private String id;
    private Map<String, Double> voltage;
    private Map<String, Double> current;
}
