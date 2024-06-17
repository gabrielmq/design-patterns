package io.github.gabrielmsouza.behavioral.strategy;

import java.time.LocalDateTime;

public class BeachFareCalculator implements FareCalculator {
    @Override
    public double calculate(LocalDateTime checkInDate, LocalDateTime checkOutDate) {
        return 10;
    }
}
