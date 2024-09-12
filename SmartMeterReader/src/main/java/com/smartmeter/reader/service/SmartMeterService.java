package com.smartmeter.reader.service;

import com.smartmeter.reader.dto.MeasurementResultDTO;
import com.smartmeter.reader.dto.TransactionDTO;
import com.smartmeter.reader.model.Measurement;
import com.smartmeter.reader.model.SmartMeter;
import com.smartmeter.reader.repository.SmartMeterRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.smartmeter.reader.mapping.MeasurementMapper.fromSmartMeterMeasurementDTO;
import static com.smartmeter.reader.mapping.SmartMeterMapper.fromTransactionDTO;

@Service
public class SmartMeterService {
    private final SmartMeterRepository smartMeterRepository;

    @Autowired
    public SmartMeterService(SmartMeterRepository smartMeterRepository) {
        this.smartMeterRepository = smartMeterRepository;
    }

    public Optional<SmartMeter> findById (String id) {
        return smartMeterRepository.findById(id);
    }

    /**
     * Saves a list of {@link SmartMeter} entities converted from a {@link TransactionDTO}.
     *
     * @param transactionDTO the DTO containing smart meter data to be saved
     * @return a list of saved {@link SmartMeter} entities
     */
    public List<SmartMeter> saveSmartMeter(TransactionDTO transactionDTO) {
        List<SmartMeter> smartMeters = fromTransactionDTO(transactionDTO);
        return smartMeterRepository.saveAll(smartMeters);
    }
}
