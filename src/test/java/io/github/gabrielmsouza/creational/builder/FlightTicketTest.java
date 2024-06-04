package io.github.gabrielmsouza.creational.builder;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FlightTicketTest {

    @Test
    void testFlightTicketBuilder() {
        final var flightTicket = FlightTicket.builder()
                .withFlight("Azul", "9876")
                .withTrip("FLN", "GRU")
                .withPassenger("John Doe", "john.doe@gmail.com", "111.111.111-11", "M")
                .withEmergencyContact("Bob Simpson", "551199999999")
                .withSeat("8A")
                .withCheckedBags(2)
                .withCheckInInformation(true, "1", "4A")
                .withPriority(5)
                .build();

        assertEquals("Azul", flightTicket.getAirline());
        assertEquals("9876", flightTicket.getFlightCode());
        assertEquals("FLN", flightTicket.getFromAirport());
        assertEquals("GRU", flightTicket.getToAirport());
        assertEquals("John Doe", flightTicket.getPassengerName());
    }
}