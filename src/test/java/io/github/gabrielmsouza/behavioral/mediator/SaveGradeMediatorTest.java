package io.github.gabrielmsouza.behavioral.mediator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SaveGradeMediatorTest {

    @Test
    void deveSalvarNota_E_CalcularMedia() {
        final var studentId = "1";
        final var gradeRepository = new GradeInMemoryRepository();
        final var averageRepository = new AverageInMemoryRepository();

        final var calculateAverage = new CalculateAverage(averageRepository, gradeRepository);

        final var mediator = new Mediator();
        mediator.register(
            "gradeSaved",
            event -> {
                System.out.println("Calculating average");
                calculateAverage.execute(((GradeSaved) event).studentId());
            }
        );

        mediator.register(
            "gradeSaved",
            event -> System.out.println("Grade saved " + event)
        );

        mediator.register(
            "otherEvent",
            event -> System.out.println("Outro evento sem nenhum listener " + event)
        );

        final var saveGradeMediator = new SaveGradeMediator(gradeRepository, mediator);

        final var input1 = new SaveGradeMediator.Input(studentId, "P1", 10);
        saveGradeMediator.execute(input1);

        final var input2 = new SaveGradeMediator.Input(studentId, "P2", 9);
        saveGradeMediator.execute(input2);

        final var input3 = new SaveGradeMediator.Input(studentId, "P3", 8);
        saveGradeMediator.execute(input3);

        final var average = averageRepository.findByStudentId(studentId);
        assertEquals(9, average.get().value());
    }
}