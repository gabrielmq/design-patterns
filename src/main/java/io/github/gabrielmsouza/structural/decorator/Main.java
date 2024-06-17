package io.github.gabrielmsouza.structural.decorator;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        // adicionando comportamento dinamicamente antes
        // de executar de fato o usecase
        final var useCase = new LogDecorator<>(
                new SecurityDecorator<>(
                        new BookRoom(
                                new BookingInMemoryRepository(),
                                new RoomInMemoryRepository()
                        )
                )
        );

        final var input = new BookRoom.Input(
                "john@email.com",
                LocalDateTime.now(),
                LocalDateTime.now(),
                "suite"
        );

        useCase.execute(input);
    }
}
