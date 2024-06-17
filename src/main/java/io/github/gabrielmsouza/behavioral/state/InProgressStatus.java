package io.github.gabrielmsouza.behavioral.state;

public record InProgressStatus(Ticket ticket, Status status) implements TicketStatus {

    public InProgressStatus(final Ticket ticket) {
        this(ticket, Status.IN_PROGRESS);
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
        this.ticket.changeStatus(new ClosedStatus(this.ticket));
    }

    @Override
    public Status getStatus() {
        return this.status;
    }
}
