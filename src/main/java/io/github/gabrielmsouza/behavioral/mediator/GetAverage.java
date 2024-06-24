package io.github.gabrielmsouza.behavioral.mediator;

public class GetAverage {
    private final AverageRepository averageRepository;

    public GetAverage(AverageRepository averageRepository) {
        this.averageRepository = averageRepository;
    }

    public Output execute(final String studentId) {
        return this.averageRepository.findByStudentId(studentId)
                .map(average -> new Output(average.value()))
                .orElseThrow();
    }

    public record Output(double value) {}
}
