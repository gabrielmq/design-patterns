package io.github.gabrielmsouza.behavioral.mediator;

import java.util.Optional;

public interface AverageRepository {
    void save(Average average);
    Optional<Average> findByStudentId(String studentId);
}
