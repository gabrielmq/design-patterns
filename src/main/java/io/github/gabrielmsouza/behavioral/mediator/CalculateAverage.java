package io.github.gabrielmsouza.behavioral.mediator;

public class CalculateAverage {
    private final AverageRepository averageRepository;
    private final GradeRepository gradeRepository;

    public CalculateAverage(AverageRepository averageRepository, GradeRepository gradeRepository) {
        this.averageRepository = averageRepository;
        this.gradeRepository = gradeRepository;
    }

    public void execute(String studentId) {
        final var grades = this.gradeRepository.listByStudentId(studentId);
        double total = 0;
        for (var grade : grades) {
            total += grade.value();
        }
        final var value = total / grades.size();
        this.averageRepository.save(new Average(studentId, value));
    }
}
