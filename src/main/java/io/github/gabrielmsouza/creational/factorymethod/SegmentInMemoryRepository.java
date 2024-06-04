package io.github.gabrielmsouza.creational.factorymethod;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SegmentInMemoryRepository implements SegmentRepository {
    private final Map<String, Segment> db;

    public SegmentInMemoryRepository() {
        this.db = new ConcurrentHashMap<>();
    }
    @Override
    public Segment save(final Segment segment) {
        this.db.put(segment.getRideId(), segment);
        return segment;
    }

    @Override
    public List<Segment> listByRideId(final String rideId) {
        return this.db.values().stream()
                .filter(segment -> segment.getRideId().equals(rideId))
                .toList();
    }
}
