package com.smartmeter.reader.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class Measurement {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "timestamp")
    private Timestamp timestamp;

    @Column(name = "voltage")
    private double voltage;

    @Column(name = "current")
    private double current;

    @ManyToOne
    @JoinColumn(name = "smart_meter_id")
    private SmartMeter smartMeter;
}