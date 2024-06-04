package io.github.gabrielmsouza.creational.factorymethod;

import java.time.LocalDateTime;
import java.util.Objects;

public class UpdateLocationUseCase {
    private final RideRepository rideRepository;
    private final SegmentRepository segmentRepository;

    public UpdateLocationUseCase(final RideRepository rideRepository, final SegmentRepository segmentRepository) {
        this.rideRepository = Objects.requireNonNull(rideRepository);
        this.segmentRepository = Objects.requireNonNull(segmentRepository);
    }

    public void execute(final Input input) {
        final var ride = this.rideRepository.findById(input.rideId).orElseThrow();
        final var newLocation = Location.create(input.latitude, input.longitude, input.date);

        // criando um segment fabricado por alguma subclasse de ride que implementa o factory method
        final var segment = ride.createSegment(ride.getLastLocation(), newLocation);

        ride.updateLocation(newLocation);
        this.rideRepository.update(ride);
        this.segmentRepository.save(segment);
    }

    public record Input(String rideId, double latitude, double longitude, LocalDateTime date) {}
}
