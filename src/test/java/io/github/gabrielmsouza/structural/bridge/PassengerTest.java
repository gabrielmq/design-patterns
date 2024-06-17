package io.github.gabrielmsouza.structural.bridge;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PassengerTest {

    @Test
    void givenAValidParams_whenCallsNewPassenger_thenShouldInstantiateAPassengerAccount() {
        // given
        final var expectedName = "John Doe";
        final var expectedEmail = "john@email.com";
        final var expectedDocument = "12345678900";
        final var expectedPassword = "123";
        final var expectedCardHolder = "John Doe";
        final var expectedCardNumber = "1111111111111111";
        final var expectedCardExpiration = "12/2022";
        final var expectedCardCvv = "123";

        // when
        final var actualAccount= Passenger.create(
                expectedName,
                expectedEmail,
                expectedDocument,
                expectedCardHolder,
                expectedCardNumber,
                expectedCardExpiration,
                expectedCardCvv,
                expectedPassword
        );

        // then
        assertEquals(expectedName, actualAccount.getName());
        assertEquals(expectedEmail, actualAccount.getEmail());
        assertEquals(expectedDocument, actualAccount.getDocument());
        assertTrue(actualAccount.isValidPassword(expectedPassword));
        assertEquals(expectedCardHolder, actualAccount.getCardHolder());
        assertEquals(expectedCardNumber, actualAccount.getCardNumber());
        assertEquals(expectedCardExpiration, actualAccount.getExpirationDate());
        assertEquals(expectedCardCvv, actualAccount.getCvv());
    }

    @Test
    void givenAInvalidName_whenCallsNewPassenger_thenShouldThrowAnException() {
        // given
        final var expectedName = "";
        final var expectedEmail = "john@email.com";
        final var expectedDocument = "12345678900";
        final var expectedPassword = "123";
        final var expectedCardHolder = "John Doe";
        final var expectedCardNumber = "1111111111111111";
        final var expectedCardExpiration = "12/2022";
        final var expectedCardCvv = "123";

        final var expectedErrorMessage = "Invalid name";

        // when
        final var actualException = assertThrows(RuntimeException.class, () -> Passenger.create(
                expectedName,
                expectedEmail,
                expectedDocument,
                expectedCardHolder,
                expectedCardNumber,
                expectedCardExpiration,
                expectedCardCvv,
                expectedPassword
        ));

        // then
        assertEquals(expectedErrorMessage, actualException.getMessage());
    }

    @Test
    void givenAInvalidEmail_whenCallsNewPassenger_thenShouldThrowAnException() {
        // given
        final var expectedName = "John Doe";
        final var expectedEmail = "johnemail.com";
        final var expectedDocument = "12345678900";
        final var expectedPassword = "123";
        final var expectedCardHolder = "John Doe";
        final var expectedCardNumber = "1111111111111111";
        final var expectedCardExpiration = "12/2022";
        final var expectedCardCvv = "123";

        final var expectedErrorMessage = "Invalid email";

        // when
        final var actualException = assertThrows(RuntimeException.class, () -> Passenger.create(
                expectedName,
                expectedEmail,
                expectedDocument,
                expectedCardHolder,
                expectedCardNumber,
                expectedCardExpiration,
                expectedCardCvv,
                expectedPassword
        ));

        // then
        assertEquals(expectedErrorMessage, actualException.getMessage());
    }

    @Test
    void givenAInvalidDocument_whenCallsNewPassenger_thenShouldThrowAnException() {
        // given
        final var expectedName = "John Doe";
        final var expectedEmail = "john@email.com";
        final var expectedDocument = "1234567890";
        final var expectedPassword = "123";
        final var expectedCardHolder = "John Doe";
        final var expectedCardNumber = "1111111111111111";
        final var expectedCardExpiration = "12/2022";
        final var expectedCardCvv = "123";

        final var expectedErrorMessage = "Invalid document";

        // when
        final var actualException = assertThrows(RuntimeException.class, () -> Passenger.create(
                expectedName,
                expectedEmail,
                expectedDocument,
                expectedCardHolder,
                expectedCardNumber,
                expectedCardExpiration,
                expectedCardCvv,
                expectedPassword
        ));

        // then
        assertEquals(expectedErrorMessage, actualException.getMessage());
    }

    @Test
    void givenAInvalidCvv_whenCallsNewPassenger_thenShouldThrowAnException() {
        // given
        final var expectedName = "John Doe";
        final var expectedEmail = "john@email.com";
        final var expectedDocument = "12345678900";
        final var expectedPassword = "123";
        final var expectedCardHolder = "John Doe";
        final var expectedCardNumber = "1111111111111111";
        final var expectedCardExpiration = "12/2022";
        final var expectedCardCvv = "12123";

        final var expectedErrorMessage = "Invalid cvv";

        // when
        final var actualException = assertThrows(RuntimeException.class, () -> Passenger.create(
                expectedName,
                expectedEmail,
                expectedDocument,
                expectedCardHolder,
                expectedCardNumber,
                expectedCardExpiration,
                expectedCardCvv,
                expectedPassword
        ));

        // then
        assertEquals(expectedErrorMessage, actualException.getMessage());
    }
}