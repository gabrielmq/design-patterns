package io.github.gabrielmsouza.creational.factorymethod;

import java.util.List;

public interface SegmentRepository {
    Segment save(Segment segment);

    List<Segment> listByRideId(String rideId);
}
