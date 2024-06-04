package io.github.gabrielmsouza.creational.factorymethod;

import java.time.LocalDateTime;

public record Location(Coord coord, LocalDateTime date) {
    public static Location create(double latitude, double longitude, LocalDateTime date) {
        return new Location(Coord.create(latitude, longitude), date);
    }
}
