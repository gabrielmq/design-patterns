package io.github.gabrielmsouza.creational.factorymethod;

public class Segment {
    private String rideId;
    protected Location from;
    protected Location to;

    Segment(String rideId, Location from, Location to) {
        this.rideId = rideId;
        this.from = from;
        this.to = to;
    }

    public String getRideId() {
        return rideId;
    }
}
