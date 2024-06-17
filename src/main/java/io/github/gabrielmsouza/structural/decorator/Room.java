package io.github.gabrielmsouza.structural.decorator;

import java.util.UUID;

public class Room {
    private String id;
    private String category;
    private double price;
    private String status;

    private Room(String id, String category, double price, String status) {
        this.id = id;
        this.category = category;
        this.price = price;
        this.status = status;
    }

    public static Room create(String category, double price, String status) {
        return new Room(UUID.randomUUID().toString(), category, price, status);
    }

    public String getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public String getStatus() {
        return status;
    }
}
