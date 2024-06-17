package io.github.gabrielmsouza.structural.decorator;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

public class Booking {
    private String roomId;
    private String code;
    private String email;
    private LocalDateTime checkinDate;
    private LocalDateTime checkoutDate;
    private int duration;
    private double price;
    public String status;

    private Booking(String roomId, String code, String email, LocalDateTime checkinDate, LocalDateTime checkoutDate, int duration, double price, String status) {
        this.roomId = roomId;
        this.code = code;
        this.email = email;
        this.checkinDate = checkinDate;
        this.checkoutDate = checkoutDate;
        this.duration = duration;
        this.price = price;
        this.status = status;
    }

    public static Booking create(
            Room room,
            String email,
            LocalDateTime checkinDate,
            LocalDateTime checkoutDate
    ) {
        final var code = UUID.randomUUID().toString();
        final var duration = (int) Duration.between(checkinDate, checkoutDate).toDays();
        final var price = duration * room.getPrice();
        return new Booking(
                room.getId(),
                code,
                email,
                checkinDate,
                checkoutDate,
                duration,
                price,
                "confirmed"
        );
    }

    public void cancel() {
        this.status = "canceled";
    }

    public String getRoomId() {
        return roomId;
    }

    public String getCode() {
        return code;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getCheckinDate() {
        return checkinDate;
    }

    public LocalDateTime getCheckoutDate() {
        return checkoutDate;
    }

    public int getDuration() {
        return duration;
    }

    public double getPrice() {
        return price;
    }

    public String getStatus() {
        return status;
    }
}
