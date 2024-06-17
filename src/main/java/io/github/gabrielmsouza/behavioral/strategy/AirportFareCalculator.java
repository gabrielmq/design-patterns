package io.github.gabrielmsouza.behavioral.strategy;

import java.time.Duration;
import java.time.LocalDateTime;

public class AirportFareCalculator implements FareCalculator {
    @Override
    public double calculate(LocalDateTime checkInDate, LocalDateTime checkOutDate) {
        final var diff = Duration.between(checkInDate, checkOutDate).toHours();
        return diff * 10.0;
    }
}
