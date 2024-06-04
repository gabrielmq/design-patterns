package io.github.gabrielmsouza.creational.factorymethod;

import java.time.LocalDateTime;
import java.util.List;

public class TimeRide extends Ride {
    TimeRide(final String id, final double latitude, final double longitude,final LocalDateTime date) {
        super(id, latitude, longitude, date);
    }

    @Override
    public double calculateFare(final List<Segment> segments) {
        double total = 0;
        for (Segment segment : segments) {
            total += ((TimeSegment) segment).getDiffInMinutes();
        }
        return total * 4;
    }

    // subclasse definindo a implementação do factory method
    @Override
    public Segment createSegment(final Location from, final Location to) {
        return TimeSegment.create(this.getId(), from, to);
    }
}
