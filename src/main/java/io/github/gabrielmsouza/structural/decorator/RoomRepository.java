package io.github.gabrielmsouza.structural.decorator;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface RoomRepository {
    List<Room> getAvailableRoomsByPeriodAndCategory (LocalDateTime checkinDate, LocalDateTime checkoutDate, String category);
    Optional<Room> getById(String roomId);
}
