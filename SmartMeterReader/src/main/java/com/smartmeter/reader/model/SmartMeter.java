package com.smartmeter.reader.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class SmartMeter {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "transaction_id")
    private String transactionId;

    @Column(name = "ip_address")
    private String ipAddress;

    @OneToMany(mappedBy = "smartMeter", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Measurement> measurements;
}