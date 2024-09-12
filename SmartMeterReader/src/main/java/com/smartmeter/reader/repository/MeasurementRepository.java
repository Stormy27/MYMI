package com.smartmeter.reader.repository;

import com.smartmeter.reader.model.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeasurementRepository extends JpaRepository<Measurement, String> {
}
