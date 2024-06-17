package io.github.gabrielmsouza.structural.decorator;

import java.util.Optional;

public interface BookingRepository {
    void save (Booking booking);
    void update (Booking booking);
    Optional<Booking> getBookingByCode (String code);
}
