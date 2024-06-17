package io.github.gabrielmsouza.behavioral.strategy;

import java.time.LocalDateTime;

public class ParkingTicket {
    private final String plate;
    private final LocalDateTime checkInDate;
    private LocalDateTime checkOutDate;
    private final Location location;
    private double fare;

    private ParkingTicket(String plate, LocalDateTime checkInDate, Location location) {
        this.plate = plate;
        this.checkInDate = checkInDate;
        this.location = location;
    }

    public static ParkingTicket create(String plate, LocalDateTime checkInDate, Location location) {
        return new ParkingTicket(plate, checkInDate, location);
    }

    public void checkout(final LocalDateTime checkOutDate) {
        this.checkOutDate = checkOutDate;
        // criando um ponto de extensão para calcular a tarifa
        // quando novas tarifas forem necessárias, basta criar uma nova classe que implementa FareCalculator
        // e adicionar uma nova localização no enum Location
        // sem a necessidade de alterar esse método e sem violar o princípio Open/Closed
        this.fare = this.location.calculate(this.checkInDate, this.checkOutDate);
    }

    public String getPlate() {
        return plate;
    }

    public LocalDateTime getCheckInDate() {
        return checkInDate;
    }

    public LocalDateTime getCheckOutDate() {
        return checkOutDate;
    }

    public Location getLocation() {
        return location;
    }

    public double getFare() {
        return fare;
    }
}
