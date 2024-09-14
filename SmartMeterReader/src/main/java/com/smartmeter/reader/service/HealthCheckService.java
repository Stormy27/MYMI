package com.smartmeter.reader.service;

import org.springframework.stereotype.Service;

@Service
public class HealthCheckService {
    /**
     * This method always returns that the service is healthy.
     * For production, meaningful health checks should be implemented.
     *
     * @return true if the service is healthy, false otherwise
     */
    public boolean check() {
        return true;
    }
}
