package io.github.gabrielmsouza.behavioral.strategy;

import java.util.Optional;

public interface ParkingTicketRepository {
    Optional<ParkingTicket> findByPlate(String plate);
    void save(ParkingTicket parkingTicket);
    void update(ParkingTicket parkingTicket);
}
