package com.smartmeter.reader.mapping;

import com.smartmeter.reader.dto.SmartMeterDTO;
import com.smartmeter.reader.dto.TransactionDTO;
import com.smartmeter.reader.model.SmartMeter;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SmartMeterMapperTest {

    @Test
    public void testFromTransactionDTO() {
        TransactionDTO transactionDTO = new TransactionDTO();

        SmartMeterDTO smartMeterDto1 = new SmartMeterDTO();
        smartMeterDto1.setId("1");
        smartMeterDto1.setIpAddress("192.168.1.1");

        SmartMeterDTO smartMeterDto2 = new SmartMeterDTO();
        smartMeterDto2.setId("2");
        smartMeterDto2.setIpAddress("192.168.1.2");

        transactionDTO.setSmartMeters(List.of(smartMeterDto1, smartMeterDto2));
        transactionDTO.setTransactionId("r12xk1");

        List<SmartMeter> smartMeters = SmartMeterMapper.fromTransactionDTO(transactionDTO);

        assertEquals(2, smartMeters.size(), "Should have 2 smart meters");

        SmartMeter smartMeter1 = smartMeters.get(0);
        assertEquals("1", smartMeter1.getId(), "ID mismatch for smart meter1");
        assertEquals("r12xk1", smartMeter1.getTransactionId(), "Transaction ID mismatch for smart meter1");
        assertEquals("192.168.1.1", smartMeter1.getIpAddress(), "IP Address mismatch for smart meter1");

        SmartMeter smartMeter2 = smartMeters.get(1);
        assertEquals("2", smartMeter2.getId(), "ID mismatch for smart meter 2");
        assertEquals("r12xk1", smartMeter2.getTransactionId(), "Transaction ID mismatch for smart meter2");
        assertEquals("192.168.1.2", smartMeter2.getIpAddress(), "IP Address mismatch for smart meter2");
    }
}
