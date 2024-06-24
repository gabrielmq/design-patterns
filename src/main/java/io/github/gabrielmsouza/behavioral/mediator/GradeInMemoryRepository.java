package io.github.gabrielmsouza.behavioral.mediator;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class GradeInMemoryRepository implements GradeRepository {
    private Map<String, List<Grade>> db;
    public GradeInMemoryRepository() {
        this.db = new ConcurrentHashMap<>();
    }

    @Override
    public void save(Grade grade) {
        this.db.computeIfAbsent(grade.studentId(), k -> new CopyOnWriteArrayList<>()).add(grade);
    }

    @Override
    public List<Grade> listByStudentId(String studentId) {
        return this.db.get(studentId);
    }
}
