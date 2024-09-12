package com.smartmeter.reader.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@Data
public class TransactionDTO implements Serializable {
    private String transactionId;
    private List<SmartMeterDTO> smartMeters;
}