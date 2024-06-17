package io.github.gabrielmsouza.structural.decorator;

public class GetBookingByCode implements UseCase<String, GetBookingByCode.Output>{
    private final BookingRepository bookingRepository;
    private final RoomRepository roomRepository;

    public GetBookingByCode(BookingRepository bookingRepository, RoomRepository roomRepository) {
        this.bookingRepository = bookingRepository;
        this.roomRepository = roomRepository;
    }

    public Output execute(final String code) {
       final var booking = this.bookingRepository.getBookingByCode(code).orElseThrow(() -> new RuntimeException("Booking not found"));
       final var room = this.roomRepository.getById(booking.getRoomId()).orElseThrow(() -> new RuntimeException("Room not found"));
       return new Output(booking.getCode(), room.getCategory(), booking.getDuration(), room.getPrice());
    }

    public record Output(String code, String category, int duration, double price) {}
}
