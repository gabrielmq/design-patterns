package io.github.gabrielmsouza.creational.factorymethod;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class RideInMemoryRepository implements RideRepository {
    private final Map<String, Ride> db;

    public RideInMemoryRepository() {
        this.db = new ConcurrentHashMap<>();
    }

    @Override
    public Ride save(final Ride ride) {
        this.db.put(ride.getId(), ride);
        return ride;
    }

    @Override
    public Optional<Ride> findById(final String id) {
        return Optional.ofNullable(this.db.get(id));
    }

    @Override
    public Ride update(final Ride ride) {
        this.db.replace(ride.getId(), this.db.get(ride.getId()), ride);
        return ride;
    }
}
