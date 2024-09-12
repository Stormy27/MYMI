package com.smartmeter.reader.repository;

import com.smartmeter.reader.model.SmartMeter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SmartMeterRepository extends JpaRepository<SmartMeter, String> {
}
