package io.github.gabrielmsouza.behavioral.strategy;

import java.time.LocalDateTime;

public interface FareCalculator {
    double calculate(LocalDateTime checkInDate, LocalDateTime checkOutDate);
}
