package io.github.gabrielmsouza.behavioral.state;

public interface TicketStatus {
    void assign();
    void start();
    void close();
    Status getStatus();

    enum Status {
        REQUESTED,
        ASSIGNED,
        IN_PROGRESS,
        CLOSED;
    }
}
