package io.github.gabrielmsouza.structural.decorator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class RoomInMemoryRepository implements RoomRepository {
    public RoomInMemoryRepository() {
    }

    @Override
    public List<Room> getAvailableRoomsByPeriodAndCategory(LocalDateTime checkinDate, LocalDateTime checkoutDate, String category) {
        return new ArrayList<>();
    }

    @Override
    public Optional<Room> getById(String roomId) {
        return Optional.empty();
    }
}
