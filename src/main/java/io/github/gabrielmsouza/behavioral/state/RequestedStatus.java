package io.github.gabrielmsouza.behavioral.state;

public record RequestedStatus(Ticket ticket, TicketStatus.Status status) implements TicketStatus {

    public RequestedStatus(final Ticket ticket) {
        this(ticket, TicketStatus.Status.REQUESTED);
    }

    @Override
    public void assign() {
        this.ticket.changeStatus(new AssignedStatus(this.ticket));
    }

    @Override
    public void start() {
        throw new RuntimeException("Could not start ticket");
    }

    @Override
    public void close() {
        throw new RuntimeException("Could not close ticket");
    }

    @Override
    public Status getStatus() {
        return this.status;
    }
}
