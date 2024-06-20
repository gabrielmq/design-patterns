package io.github.gabrielmsouza.behavioral.chainofresponsability;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

public class Segment {
    private double distance;
    private LocalDateTime date;

    public Segment(double distance, LocalDateTime date) {
        if (distance < 0) {
            throw new RuntimeException("Invalid distance");
        }
        this.distance = distance;
        this.date = date;
    }

    public boolean isOvernight() {
        return this.date.getHour() >= 22 || this.date.getHour() <= 6;
    }

    public boolean isSunday() {
        return this.date.getDayOfWeek() == DayOfWeek.SUNDAY;
    }

    public double getDistance() {
        return distance;
    }

    public LocalDateTime getDate() {
        return date;
    }
}
