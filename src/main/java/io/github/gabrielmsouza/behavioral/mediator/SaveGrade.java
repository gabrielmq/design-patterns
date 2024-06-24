package io.github.gabrielmsouza.behavioral.mediator;

public class SaveGrade {
    private final GradeRepository gradeRepository;
    private final  CalculateAverage calculateAverage;

    public SaveGrade(GradeRepository gradeRepository, CalculateAverage calculateAverage) {
        this.gradeRepository = gradeRepository;
        this.calculateAverage = calculateAverage;
    }

    public void execute(final Input in) {
        final var grade = new Grade(in.studentId(), in.exam(), in.value());
        this.gradeRepository.save(grade);
        this.calculateAverage.execute(in.studentId());
    }

    public record Input(String studentId, String exam, double value) {}
}
