package com.smartmeter.reader.service;

import com.smartmeter.reader.dto.TransactionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/smartmeters")
public class SmartMeterController {
    private final SmartMeterService smartMeterService;

    @Autowired
    public SmartMeterController(SmartMeterService smartMeterService) {
        this.smartMeterService = smartMeterService;
    }

    /**
     * Registers smart meters from the given {@link TransactionDTO}.
     *
     * @param transactionDTO the DTO containing smart meter registration data
     * @return {@link ResponseEntity} with a status 200 if registration is successful, or 500 if an error occurs
     */
    @PostMapping("/register")
    public ResponseEntity<String> registerSmartMeter(@RequestBody TransactionDTO transactionDTO) {
        try {
            smartMeterService.saveSmartMeters(transactionDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An unexpected error occurred: " + e.getMessage());
        }

        return ResponseEntity.ok("Smart meters were registered.");
    }
}
