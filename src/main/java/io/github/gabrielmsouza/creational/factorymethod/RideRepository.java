package io.github.gabrielmsouza.creational.factorymethod;

import java.util.Optional;

public interface RideRepository {
    Ride save(Ride ride);
    Optional<Ride> findById(String id);
    Ride update(Ride ride);
}
