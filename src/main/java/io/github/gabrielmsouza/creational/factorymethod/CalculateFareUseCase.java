package io.github.gabrielmsouza.creational.factorymethod;

import java.util.Objects;

public class CalculateFareUseCase {
    private final RideRepository rideRepository;
    private final SegmentRepository segmentRepository;

    public CalculateFareUseCase(final RideRepository rideRepository, final SegmentRepository segmentRepository) {
        this.rideRepository = Objects.requireNonNull(rideRepository);
        this.segmentRepository = Objects.requireNonNull(segmentRepository);
    }

    public Output execute(final String rideId) {
        final var ride = this.rideRepository.findById(rideId).orElseThrow();
        final var segments = this.segmentRepository.listByRideId(rideId);
        return new Output(ride.calculateFare(segments));
    }

    public record Output(double fare) {
    }
}
