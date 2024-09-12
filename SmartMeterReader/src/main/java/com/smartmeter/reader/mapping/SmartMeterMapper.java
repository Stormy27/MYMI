package com.smartmeter.reader.mapping;


import com.smartmeter.reader.dto.TransactionDTO;
import com.smartmeter.reader.model.SmartMeter;
import java.util.LinkedList;
import java.util.List;

public class SmartMeterMapper {

    /**
     * Converts a {@link TransactionDTO} into a list of {@link SmartMeter} entities.
     *
     * @param transactionDTO the DTO containing transaction and smart meter information
     * @return a list of {@link SmartMeter} entities
     */
    public static List<SmartMeter> fromTransactionDTO(TransactionDTO transactionDTO) {
        List<SmartMeter> smartMeters = new LinkedList<>();

        for (var smartMeterDTO : transactionDTO.getSmartMeters()) {
            SmartMeter smartMeter = new SmartMeter();
            smartMeter.setId(smartMeterDTO.getId());
            smartMeter.setTransactionId(transactionDTO.getTransactionId());
            smartMeter.setIpAddress(smartMeterDTO.getIpAddress());

            smartMeters.add(smartMeter);
        }

        return smartMeters;
    }
}
