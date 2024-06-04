package io.github.gabrielmsouza.creational.factorymethod;

public record Coord(double lat, double lng) {
    public Coord {
        if (lat < -90 || lat > 90) throw new RuntimeException("Invalid latitude");
        if (lng < -180 || lng > 180) throw new RuntimeException("Invalid longitude");
    }

    public static Coord create(double latitude, double longitude) {
        return new Coord(latitude, longitude);
    }
}
