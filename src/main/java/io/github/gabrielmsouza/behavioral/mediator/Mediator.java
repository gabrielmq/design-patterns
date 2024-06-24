package io.github.gabrielmsouza.behavioral.mediator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Mediator {
    private final Map<String, List<Handler>> handlers;

    public Mediator() {
        this.handlers = new HashMap<>();
    }

    public void register(String eventName, Handler handler) {
        this.handlers.computeIfAbsent(eventName, k -> new ArrayList<>()).add(handler);
    }

    public void register(String eventName, Handler ...handler) {
        this.handlers.computeIfAbsent(eventName, k -> new ArrayList<>()).addAll(List.of(handler));
    }

    public void notify(String eventName, Event event) {
        if (this.handlers.containsKey(eventName)) {
            final var handlers = this.handlers.get(eventName);
            handlers.forEach(handler -> handler.handle(event));
        }
    }
}
