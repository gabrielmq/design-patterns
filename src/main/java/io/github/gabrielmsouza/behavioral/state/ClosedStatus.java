package io.github.gabrielmsouza.behavioral.state;

public record ClosedStatus(Ticket ticket, Status status) implements TicketStatus {

    public ClosedStatus(final Ticket ticket) {
        this(ticket, Status.CLOSED);
    }

    @Override
    public void assign() {
        throw new RuntimeException("Could not assign ticket");
    }

    @Override
    public void start() {
        throw new RuntimeException("Could not start ticket");
    }

    @Override
    public void close() {
        throw new RuntimeException("Could not closed ticket");
    }

    @Override
    public Status getStatus() {
        return this.status;
    }
}
