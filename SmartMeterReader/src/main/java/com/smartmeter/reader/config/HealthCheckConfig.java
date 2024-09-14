package com.smartmeter.reader.config;

import com.smartmeter.reader.service.HealthCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HealthCheckConfig {
    private final HealthCheckService healthCheckService;

    @Autowired
    public HealthCheckConfig(HealthCheckService healthCheckService) {
        this.healthCheckService = healthCheckService;
    }

    @Bean
    public HealthIndicator customHealthIndicator() {
        return () -> {
            boolean healthy = healthCheckService.check();
            if (healthy) {
                return Health.up().withDetail("CustomCheck", "Service is healthy").build();
            } else {
                return Health.down().withDetail("CustomCheck", "Service is down").build();
            }
        };
    }
}
