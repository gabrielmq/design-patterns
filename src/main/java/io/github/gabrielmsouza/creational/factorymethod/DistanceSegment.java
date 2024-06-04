package io.github.gabrielmsouza.creational.factorymethod;

public class DistanceSegment extends Segment {

    private DistanceSegment(String rideId, Location from, Location to) {
        super(rideId, from, to);
    }

    public static DistanceSegment create(String rideId, Location from, Location to) {
        return new DistanceSegment(rideId, from, to);
    }

    public double getDistance() {
        final var earthRadius = 6371;
        final var degreesToRadians = Math.PI / 180;
        final var deltaLat = (this.to.coord().lat() - this.from.coord().lat()) * degreesToRadians;
        final var deltaLon = (this.to.coord().lng() - this.from.coord().lng()) * degreesToRadians;
        final var a =
                Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2) +
                        Math.cos(this.from.coord().lat() * degreesToRadians) *
                                Math.cos(this.to.coord().lat() * degreesToRadians) *
                                Math.sin(deltaLon / 2) *
                                Math.sin(deltaLon / 2);
        final var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return Math.round(earthRadius * c);
    }
}
