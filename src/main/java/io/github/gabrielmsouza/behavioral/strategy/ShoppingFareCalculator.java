package io.github.gabrielmsouza.behavioral.strategy;

import java.time.Duration;
import java.time.LocalDateTime;

public class ShoppingFareCalculator implements FareCalculator {
    @Override
    public double calculate(LocalDateTime checkInDate, LocalDateTime checkOutDate) {
        final var diff = Duration.between(checkInDate, checkOutDate).toHours();
        var fare = 10.0;
        final var remainingHours = diff - 3;
        if (remainingHours > 0) {
            fare += remainingHours * 10.0;
        }
        return fare;
    }
}
