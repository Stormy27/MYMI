package com.smartmeter.reader.service;

import com.smartmeter.reader.dto.TransactionDTO;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringJUnitConfig
@SpringBootTest
public class SmartMeterControllerTest {
    @Mock
    private SmartMeterService smartMeterService;

    @InjectMocks
    private SmartMeterController smartMeterController;

    @Test
    public void testRegisterSmartMeter_Success() {
        TransactionDTO transactionDTO = new TransactionDTO();

        when(smartMeterService.saveSmartMeter(transactionDTO)).thenReturn(Collections.emptyList());

        ResponseEntity<String> response = smartMeterController.registerSmartMeter(transactionDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode(), "Status should be 200 OK");
        assertEquals("Smart meters were registered.", response.getBody(), "Response body mismatch");
    }

    @Test
    public void testRegisterSmartMeter_Exception() {
        TransactionDTO transactionDTO = new TransactionDTO();

        when(smartMeterService.saveSmartMeter(transactionDTO)).thenThrow(new RuntimeException("Unexpected error"));

        ResponseEntity<String> response = smartMeterController.registerSmartMeter(transactionDTO);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode(), "Status should be 500 Internal Server Error");
        assertTrue(response.getBody().contains("An unexpected error occurred: Unexpected error"), "Response body mismatch");
    }
}
