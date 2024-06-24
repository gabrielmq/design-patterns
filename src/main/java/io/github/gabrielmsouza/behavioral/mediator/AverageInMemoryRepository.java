package io.github.gabrielmsouza.behavioral.mediator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class AverageInMemoryRepository implements AverageRepository {
    private Map<String, List<Average>> db;
    public AverageInMemoryRepository() {
        this.db = new ConcurrentHashMap<>();
    }

    @Override
    public void save(Average average) {
        this.db.remove(average.studentId());
        this.db.computeIfAbsent(average.studentId(), k -> new ArrayList<>()).add(average);
    }

    @Override
    public Optional<Average> findByStudentId(String studentId) {
        return this.db.get(studentId).stream().reduce((a, b) -> new Average(studentId, (a.value() + b.value()) / 2));
    }
}
