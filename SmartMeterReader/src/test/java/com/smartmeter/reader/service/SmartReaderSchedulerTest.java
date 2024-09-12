package com.smartmeter.reader.service;

import com.smartmeter.reader.dto.MeasurementResultDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class SmartReaderSchedulerTest {
    @Mock
    private WebClient webClient;

    @Mock
    private WebClient.RequestHeadersUriSpec requestHeadersUriSpec;

    @Mock
    private WebClient.RequestHeadersSpec requestHeadersSpec;

    @Mock
    private WebClient.ResponseSpec responseSpec;

    @Mock
    private MeasurementService measurementService;

    @InjectMocks
    private SmartReaderScheduler smartReaderScheduler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testReadSmartMeterData() {
        MeasurementResultDTO mockResult = new MeasurementResultDTO();

        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(anyString())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(MeasurementResultDTO.class)).thenReturn(Mono.just(mockResult));

        smartReaderScheduler.readSmartMeterData();

        verify(measurementService, times(1)).saveMeasurement(mockResult);
    }
}