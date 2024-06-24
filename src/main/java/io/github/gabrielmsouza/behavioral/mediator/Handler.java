package io.github.gabrielmsouza.behavioral.mediator;

@FunctionalInterface
public interface Handler {
    void handle(Event event);
}
