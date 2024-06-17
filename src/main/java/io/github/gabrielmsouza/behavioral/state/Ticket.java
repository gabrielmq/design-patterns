package io.github.gabrielmsouza.behavioral.state;

import java.time.LocalDateTime;

public class Ticket {
    private TicketStatus status;
    private String employeeId;
    private String customerId;
    private LocalDateTime requestedAt;
    private LocalDateTime assignedAt;
    private LocalDateTime startedAt;
    private LocalDateTime closedAt;

    private Ticket(String customerId, LocalDateTime requestedAt) {
        this.customerId = customerId;
        this.requestedAt = requestedAt;
        this.status = new RequestedStatus(this);
    }

    public static Ticket create(String customerId, LocalDateTime requestedAt) {
        return new Ticket(customerId, requestedAt);
    }

    public void changeStatus(final TicketStatus status) {
        this.status = status;
    }

    public void assign(String employeeId, LocalDateTime assignedAt) {
        this.employeeId = employeeId;
        this.assignedAt = assignedAt;
        this.status.assign();
    }

    public void start(LocalDateTime startedAt) {
        this.startedAt = startedAt;
        this.status.start();
    }

    public void close(LocalDateTime closedAt) {
        this.closedAt = closedAt;
        this.status.close();
    }

    public TicketStatus.Status getStatus() {
        return status.getStatus();
    }
}
