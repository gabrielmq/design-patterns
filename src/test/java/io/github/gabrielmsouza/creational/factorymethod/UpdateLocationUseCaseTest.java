package io.github.gabrielmsouza.creational.factorymethod;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static io.github.gabrielmsouza.creational.factorymethod.UpdateLocationUseCase.Input;

class UpdateLocationUseCaseTest {

    @Test
    @DisplayName("Deve atualizar a localização de uma corrida por distância")
    void shouldUpdateLocationByDistance() {
        // given
        final var expectedLatitude = -27.496887588317275;
        final var expectedLongitude = -48.522234807851476;
        final var expectedDate = LocalDateTime.now();

//        final var ride = new Ride(expectedLatitude, expectedLongitude, expectedDate);
//        final var expectedRideId = ride.getId();
//
//        final var input = new Input(expectedRideId, expectedLatitude, expectedLongitude, expectedDate);

        // when
//        new UpdateLocationUseCase().execute(input);

        // then
    }
}