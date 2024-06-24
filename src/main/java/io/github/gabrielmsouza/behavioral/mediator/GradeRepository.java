package io.github.gabrielmsouza.behavioral.mediator;

import java.util.List;
import java.util.Optional;

public interface GradeRepository {
    void save(Grade grade);
    List<Grade> listByStudentId(String studentId);
}
