package io.github.gabrielmsouza.behavioral.mediator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Mediator {
    private final List<Map<String, Handler>> handlers;

    public Mediator() {
        this.handlers = new ArrayList<>();
    }

    public void register(String event, Handler handler) {
        this.handlers.add(Map.of(event, handler));
    }

    public void notify(String eventName, Event event) {
        this.handlers.forEach(handler -> {
            if (handler.containsKey(eventName)) {
                handler.get(eventName).handle(event);
            }
        });
    }
}
