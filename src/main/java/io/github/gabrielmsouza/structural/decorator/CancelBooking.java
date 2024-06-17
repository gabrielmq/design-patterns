package io.github.gabrielmsouza.structural.decorator;

public class CancelBooking {
    private final BookingRepository bookingRepository;

    public CancelBooking(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public void execute(final String code) {
        this.bookingRepository
                .getBookingByCode(code)
                .ifPresent(booking -> {
                    booking.cancel();
                    this.bookingRepository.update(booking);
                });
    }
}
