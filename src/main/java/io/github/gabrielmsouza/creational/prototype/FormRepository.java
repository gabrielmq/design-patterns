package io.github.gabrielmsouza.creational.prototype;

import java.util.Optional;

public interface FormRepository {
    Optional<Form> getById(String id);
    void save(Form form);
}
