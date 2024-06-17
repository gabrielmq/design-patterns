package io.github.gabrielmsouza.structural.decorator;

import java.util.Optional;

public class BookingInMemoryRepository implements BookingRepository {
    @Override
    public void save(Booking booking) {
        System.out.println("Booking saved");
    }

    @Override
    public void update(Booking booking) {
        System.out.println("Booking updated");
    }

    @Override
    public Optional<Booking> getBookingByCode(String code) {
        return Optional.empty();
    }
}
