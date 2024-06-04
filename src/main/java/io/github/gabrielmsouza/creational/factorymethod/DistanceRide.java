package io.github.gabrielmsouza.creational.factorymethod;

import java.time.LocalDateTime;
import java.util.List;

public class DistanceRide extends Ride {
    DistanceRide(final String id, final double latitude, final double longitude, final LocalDateTime date) {
        super(id, latitude, longitude, date);
    }

    @Override
    public double calculateFare(final List<Segment> segments) {
        double total = 0;
        for (Segment segment : segments) {
            total += ((DistanceSegment) segment).getDistance();
        }
        return total * 4;
    }

    @Override
    public Segment createSegment(final Location from, final Location to) {
        return DistanceSegment.create(this.getId(), from, to);
    }
}
