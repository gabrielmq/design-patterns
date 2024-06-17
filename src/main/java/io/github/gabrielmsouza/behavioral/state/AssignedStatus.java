package io.github.gabrielmsouza.behavioral.state;

public record AssignedStatus(Ticket ticket, Status status) implements TicketStatus {

    public AssignedStatus(final Ticket ticket) {
        this(ticket, Status.ASSIGNED);
    }

    @Override
    public void assign() {
        throw new RuntimeException("Could not assign ticket");
    }

    @Override
    public void start() {
        this.ticket.changeStatus(new InProgressStatus(this.ticket));
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
