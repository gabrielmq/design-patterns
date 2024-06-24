package io.github.gabrielmsouza.behavioral.mediator;

public class SaveGradeMediator {
    private final GradeRepository gradeRepository;
    private final Mediator mediator;

    public SaveGradeMediator(GradeRepository gradeRepository, Mediator mediator) {
        this.gradeRepository = gradeRepository;
        this.mediator = mediator;
    }

    public void execute(final Input in) {
        final var grade = new Grade(in.studentId(), in.exam(), in.value());
        this.gradeRepository.save(grade);
        this.mediator.notify("gradeSaved", new GradeSaved(in.studentId()));
    }

    public record Input(String studentId, String exam, double value) {}
}
