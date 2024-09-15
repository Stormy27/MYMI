package com.smartmeter.reader.service;

import com.smartmeter.reader.dto.SmartMeterDTO;
import com.smartmeter.reader.dto.TransactionDTO;
import com.smartmeter.reader.model.SmartMeter;
import com.smartmeter.reader.repository.SmartMeterRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SmartMeterServiceTest {
    @Mock
    private SmartMeterRepository smartMeterRepository;

    @InjectMocks
    private SmartMeterService smartMeterService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindById() {
        String id = "12345";
        SmartMeter smartMeter = new SmartMeter();
        when(smartMeterRepository.findById(id)).thenReturn(Optional.of(smartMeter));

        Optional<SmartMeter> result = smartMeterService.findById(id);

        assertTrue(result.isPresent());
        assertEquals(smartMeter, result.get());
        verify(smartMeterRepository, times(1)).findById(id);
    }

    @Test
    void testSaveSmartMeter() {
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setTransactionId("tid");
        transactionDTO.setSmartMeters(List.of(new SmartMeterDTO()));

        SmartMeter smartMeter = new SmartMeter();
        List<SmartMeter> smartMeters = List.of(smartMeter);

        when(smartMeterRepository.saveAll(anyList())).thenReturn(smartMeters);

        List<SmartMeter> result = smartMeterService.saveSmartMeters(transactionDTO);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(smartMeter, result.get(0));

        verify(smartMeterRepository, times(1)).saveAll(anyList());
    }
}