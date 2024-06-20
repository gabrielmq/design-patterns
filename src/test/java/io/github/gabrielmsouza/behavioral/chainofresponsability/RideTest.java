package io.github.gabrielmsouza.behavioral.chainofresponsability;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class RideTest {

    private Ride ride;

    @BeforeEach
    void setUp() {
        final var overnightSundayFareCalculator = new OvernightSundayFareCalculator(null); // 4
        final var sundayFareCalculator = new SundayFareCalculator(overnightSundayFareCalculator); // 3
	    final var overnightFareCalculator = new OvernightFareCalculator(sundayFareCalculator); // 2
	    final var normalFareCalculator = new NormalFareCalculator(overnightFareCalculator); // 1
        ride = new Ride(normalFareCalculator);
    }

    @Test
    void deveCalcularOValor_DaCorrida_HorarioNormal() {
        ride.addSegment(10, LocalDateTime.parse("2021-03-01T10:00:00"));
        ride.calculateFare();
        assertEquals(21, ride.getFare());
    }

    @Test
    void deveCalcularOValor_DaCorrida_HorarioNoturno() {
        ride.addSegment(10, LocalDateTime.parse("2021-03-01T23:00:00"));
        ride.calculateFare();
        assertEquals(39, ride.getFare());
    }

    @Test
    void deveCalcularOValor_DaCorrida_Domingo() {
        ride.addSegment(10, LocalDateTime.parse("2021-03-07T10:00:00"));
        ride.calculateFare();
        assertEquals(29, ride.getFare());
    }

    @Test
    void deveCalcularOValor_DaCorrida_DomingoANoite() {
        ride.addSegment(10, LocalDateTime.parse("2021-03-07T23:00:00"));
        ride.calculateFare();
        assertEquals(50, ride.getFare());
    }

    @Test
    void naoDeveCalcularOValor_DaCorrida_ComDistanciaInvalid() {
        assertThrows(RuntimeException.class,
                () -> ride.addSegment(-10, LocalDateTime.parse("2021-03-07T23:00:00")));
    }
}