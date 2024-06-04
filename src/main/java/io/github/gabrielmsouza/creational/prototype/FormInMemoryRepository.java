package io.github.gabrielmsouza.creational.prototype;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class FormInMemoryRepository implements FormRepository {
    private final Map<String, Form> db;

    public FormInMemoryRepository() {
        this.db = new ConcurrentHashMap<>();
    }
    @Override
    public Optional<Form> getById(String id) {
        return Optional.ofNullable(this.db.get(id));
    }

    @Override
    public void save(Form form) {
        this.db.put(form.getId(), form);
    }
}
