package io.github.gabrielmsouza.creational.factorymethod;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public abstract class Ride {
    private String id;
    private double latitude;
    private double longitude;
    private LocalDateTime date;
    private Location lastLocation;

    Ride(final String id, final double latitude, final double longitude, LocalDateTime date) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.date = date;
        this.lastLocation = Location.create(latitude, longitude, date);
    }

    public void updateLocation(final Location newLocation) {
        this.lastLocation = newLocation;
    }

    public abstract double calculateFare(List<Segment> segments);

    // factory method definido para as subclasses criarem suas instâncias
    public abstract Segment createSegment(Location from, Location to);

    public String getId() {
        return id;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public Location getLastLocation() {
        return lastLocation;
    }
}
