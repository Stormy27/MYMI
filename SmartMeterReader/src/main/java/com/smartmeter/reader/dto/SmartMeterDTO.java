package com.smartmeter.reader.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@NoArgsConstructor
@Data
public class SmartMeterDTO implements Serializable {
    private String id;
    private String ipAddress;
}
