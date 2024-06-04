package io.github.gabrielmsouza.creational.factorymethod;

import java.time.temporal.ChronoUnit;

public class TimeSegment extends Segment {
    private TimeSegment(String rideId, Location from, Location to) {
        super(rideId, from, to);
    }

    public static TimeSegment create(String rideId, Location from, Location to) {
        return new TimeSegment(rideId, from, to);
    }

    public double getDiffInMinutes() {
        return ChronoUnit.MINUTES.between(this.to.date(), this.from.date());
    }
}
