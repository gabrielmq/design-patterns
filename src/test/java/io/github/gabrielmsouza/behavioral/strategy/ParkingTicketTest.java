package io.github.gabrielmsouza.behavioral.strategy;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ParkingTicketTest {

    @Test
    void deveCalcularTarifaAeroporto() {
        final var plate = "ABC-1234";
        final var checkInDate = LocalDateTime.parse("2023-03-01T10:00:00");
        final var location = Location.AIRPORT;
        final var checkoutDate = LocalDateTime.parse("2023-03-01T12:00:00");

        final var ticket = ParkingTicket.create(plate, checkInDate, location);

        ticket.checkout(checkoutDate);

        assertEquals(20.0, ticket.getFare());
    }

    @Test
    void deveCalcularTarifaPraia() {
        final var plate = "ABC-1234";
        final var checkInDate = LocalDateTime.parse("2023-03-01T10:00:00");
        final var location = Location.BEACH;
        final var checkoutDate = LocalDateTime.parse("2023-03-01T12:00:00");

        final var ticket = ParkingTicket.create(plate, checkInDate, location);

        ticket.checkout(checkoutDate);

        assertEquals(10.0, ticket.getFare());
    }

    @Test
    void deveCalcularTarifaShopping() {
        final var plate = "ABC-1234";
        final var checkInDate = LocalDateTime.parse("2023-03-01T10:00:00");
        final var location = Location.SHOPPING;
        final var checkoutDate = LocalDateTime.parse("2023-03-01T12:00:00");

        final var ticket = ParkingTicket.create(plate, checkInDate, location);

        ticket.checkout(checkoutDate);

        assertEquals(10.0, ticket.getFare());
    }

    @Test
    void deveCalcularTarifaPublico() {
        final var plate = "ABC-1234";
        final var checkInDate = LocalDateTime.parse("2023-03-01T10:00:00");
        final var location = Location.PUBLIC;
        final var checkoutDate = LocalDateTime.parse("2023-03-01T12:00:00");

        final var ticket = ParkingTicket.create(plate, checkInDate, location);

        ticket.checkout(checkoutDate);

        assertEquals(0, ticket.getFare());
    }

    @Test
    void deveCalcularTarifaShoppingComMaisDeTresHoras() {
        final var plate = "ABC-1234";
        final var checkInDate = LocalDateTime.parse("2023-03-01T10:00:00");
        final var location = Location.SHOPPING;
        final var checkoutDate = LocalDateTime.parse("2023-03-01T15:00:00");

        final var ticket = ParkingTicket.create(plate, checkInDate, location);

        ticket.checkout(checkoutDate);

        assertEquals(30.0, ticket.getFare());
    }

    @Test
    void deveCalcularTarifaShoppingComMenosDeTresHoras() {
        final var plate = "ABC-1234";
        final var checkInDate = LocalDateTime.parse("2023-03-01T10:00:00");
        final var location = Location.SHOPPING;
        final var checkoutDate = LocalDateTime.parse("2023-03-01T11:00:00");

        final var ticket = ParkingTicket.create(plate, checkInDate, location);

        ticket.checkout(checkoutDate);

        assertEquals(10.0, ticket.getFare());
    }
}