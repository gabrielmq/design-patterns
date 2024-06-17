package io.github.gabrielmsouza.structural.decorator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ImportBookingTest {

    private UseCase<ImportBooking.Input, ImportBooking.Output> useCase;

    @Mock
    private BookingInMemoryRepository bookingInMemoryRepository;

    @Mock
    private RoomInMemoryRepository roomInMemoryRepository;

    @BeforeEach
    void setUp() {
        useCase = new SecurityDecorator<>(
                new LogDecorator<>(
                        new ImportBooking(
                                new LogDecorator<>(
                                        new BookRoom(bookingInMemoryRepository, roomInMemoryRepository)
                                )
                        )
                )
        );
    }

    @Test
    void deveImportarReservas() {
        final var file = """
                email;checkin_date;checkout_date;category;
                john.doe1@gmail.com;2021-03-01T10:00:00;2021-03-03T10:00:00;suite;
                john.doe2@gmail.com;2021-03-06T10:00:00;2021-03-08T10:00:00;suite;
                john.doe3@gmail.com;2021-03-20T10:00:00;2021-03-22T10:00:00;suite;
           """;

        when(this.roomInMemoryRepository.getAvailableRoomsByPeriodAndCategory(any(), any(), any()))
                .thenReturn(List.of(
                        Room.create("suite", 100, "available"),
                        Room.create("suite", 200, "available"),
                        Room.create("suite", 300, "available")
                ));

        doNothing().when(this.bookingInMemoryRepository).save(any());

        final var input = new ImportBooking.Input(file);

        final var output = useCase.execute(input);

        assertFalse(output.codes().isEmpty());
        assertEquals(3, output.codes().size());
    }
}