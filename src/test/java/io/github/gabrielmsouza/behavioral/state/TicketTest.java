package io.github.gabrielmsouza.behavioral.state;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TicketTest {

    @Test
    void deveRealizarAsTransicoes_DeEstadoDeUmChamado() {
        final var customerId = "123";
        final var ticket = Ticket.create(customerId, LocalDateTime.parse("2021-03-01T08:00:00"));
        assertEquals(TicketStatus.Status.REQUESTED, ticket.getStatus());

        final var employeeId = "12";
        ticket.assign(employeeId, LocalDateTime.parse("2021-03-01T08:30:00"));
        assertEquals(TicketStatus.Status.ASSIGNED, ticket.getStatus());

        ticket.start(LocalDateTime.parse("2021-03-01T09:00:00"));
        assertEquals(TicketStatus.Status.IN_PROGRESS, ticket.getStatus());

        ticket.close(LocalDateTime.parse("2021-03-01T10:00:00"));
        assertEquals(TicketStatus.Status.CLOSED, ticket.getStatus());
    }
}