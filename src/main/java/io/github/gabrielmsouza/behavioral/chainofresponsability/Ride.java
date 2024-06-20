package io.github.gabrielmsouza.behavioral.chainofresponsability;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Ride {
    private List<Segment> segments;
    private double fare;
    private FareCalculator fareCalculator;

    public Ride(final FareCalculator fareCalculator) {
        this.segments = new ArrayList<>();
        this.fareCalculator = fareCalculator;
    }

    public void addSegment(final double distance, final LocalDateTime date) {
        this.segments.add(new Segment(distance, date));
    }

    public void calculateFare() {
        this.fare = 0;
        for (Segment segment : segments) {
            this.fare += fareCalculator.calculate(segment);
        }
        this.fare = this.fare < 10 ? 10 : this.fare;
    }

    public double getFare() {
        return this.fare;
    }
}
