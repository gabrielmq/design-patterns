package io.github.gabrielmsouza.structural.decorator;

import java.time.LocalDateTime;

public class BookRoom implements UseCase<BookRoom.Input, BookRoom.Output> {
    private final BookingRepository bookingRepository;
    private final RoomRepository roomRepository;

    public BookRoom(BookingRepository bookingRepository, RoomRepository roomRepository) {
        this.bookingRepository = bookingRepository;
        this.roomRepository = roomRepository;
    }

    public Output execute(final Input input) {
        final var availableRoom = this.roomRepository.getAvailableRoomsByPeriodAndCategory(input.checkinDate(), input.checkoutDate(), input.category());
        if (availableRoom.isEmpty()) {
            throw new RuntimeException("No rooms available");
        }
        final var booking = Booking.create(availableRoom.get(0), input.email(), input.checkinDate(), input.checkoutDate());
        this.bookingRepository.save(booking);
        return new Output(booking.getCode());
    }

    public record Input(String email, LocalDateTime checkinDate, LocalDateTime checkoutDate, String category) {}

    public record Output(String code) {}
}
