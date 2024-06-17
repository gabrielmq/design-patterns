package io.github.gabrielmsouza.behavioral.strategy;

import java.time.LocalDateTime;
import java.util.Objects;

public class Checkout {
    private final ParkingTicketRepository repository;

    public Checkout(final ParkingTicketRepository repository) {
        this.repository = Objects.requireNonNull(repository);
    }

    public Output execute(final Input in) {
        final var parkingTicket = this.repository.findByPlate(in.plate)
            .orElseThrow(() -> new RuntimeException("Parking Ticket not found"));
        parkingTicket.checkout(in.checkOutDate);
        this.repository.update(parkingTicket);
        return new Output(parkingTicket.getPlate(), parkingTicket.getFare());
    }

    public record Input(String plate, LocalDateTime checkOutDate) {}

    public record Output(String plate, double fare) {}
}
