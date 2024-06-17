package io.github.gabrielmsouza.behavioral.strategy;

import java.time.LocalDateTime;
import java.util.Objects;

public class Checkin {
    private final ParkingTicketRepository repository;

    public Checkin(ParkingTicketRepository repository) {
        this.repository = Objects.requireNonNull(repository);
    }

    public void execute(final Input input) {
        this.repository.findByPlate(input.plate)
            .ifPresent(ticket -> {
                throw new RuntimeException("Duplicated plate");
            });

        final var parkingTicket = ParkingTicket.create(input.plate, input.checkInDate, input.location);
        this.repository.save(parkingTicket);
    }

    public record Input(String plate, LocalDateTime checkInDate, Location location) {}
}
