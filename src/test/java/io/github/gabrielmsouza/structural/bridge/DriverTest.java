package io.github.gabrielmsouza.structural.bridge;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DriverTest {

    @Test
    void givenAValidParams_whenCallsNewDriver_thenShouldInstantiateADriverAccount() {
        // given
        final var expectedName = "John Doe";
        final var expectedEmail = "john@email.com";
        final var expectedDocument = "12345678900";
        final var expectedPassword = "123";
        final var expectedCarPlate = "AAA9999";

        // when
        final var actualAccount = Driver.create(
                expectedName,
                expectedEmail,
                expectedDocument,
                expectedCarPlate,
                expectedPassword
        );

        // then
        assertEquals(expectedName, actualAccount.getName());
        assertEquals(expectedEmail, actualAccount.getEmail());
        assertEquals(expectedDocument, actualAccount.getDocument());
        assertTrue(actualAccount.isValidPassword(expectedPassword));
        assertEquals(expectedCarPlate, actualAccount.getCarPlate());
    }

    @Test
    void givenAInvalidName_whenCallsNewDriver_thenShouldThrowAnException() {
        // given
        final var expectedName = "";
        final var expectedEmail = "john@email.com";
        final var expectedDocument = "12345678900";
        final var expectedPassword = "123";
        final var expectedCarPlate = "AAA9999";

        final var expectedErrorMessage = "Invalid name";

        // when
        final var actualException = assertThrows(RuntimeException.class, () -> Driver.create(
                expectedName,
                expectedEmail,
                expectedDocument,
                expectedCarPlate,
                expectedPassword
        ));

        // then
        assertEquals(expectedErrorMessage, actualException.getMessage());
    }

    @Test
    void givenAInvalidEmail_whenCallsNewDriver_thenShouldThrowAnException() {
        // given
        final var expectedName = "John Doe";
        final var expectedEmail = "johnemail.com";
        final var expectedDocument = "12345678900";
        final var expectedPassword = "123";
        final var expectedCarPlate = "AAA9999";

        final var expectedErrorMessage = "Invalid email";

        // when
        final var actualException = assertThrows(RuntimeException.class, () -> Driver.create(
                expectedName,
                expectedEmail,
                expectedDocument,
                expectedCarPlate,
                expectedPassword
        ));

        // then
        assertEquals(expectedErrorMessage, actualException.getMessage());
    }

    @Test
    void givenAInvalidDocument_whenCallsNewDriver_thenShouldThrowAnException() {
        // given
        final var expectedName = "John Doe";
        final var expectedEmail = "john@email.com";
        final var expectedDocument = "1234567890";
        final var expectedPassword = "123";
        final var expectedCarPlate = "AAA9999";

        final var expectedErrorMessage = "Invalid document";

        // when
        final var actualException = assertThrows(RuntimeException.class, () -> Driver.create(
                expectedName,
                expectedEmail,
                expectedDocument,
                expectedCarPlate,
                expectedPassword
        ));

        // then
        assertEquals(expectedErrorMessage, actualException.getMessage());
    }

    @Test
    void givenAInvalidCarPlate_whenCallsNewDriver_thenShouldThrowAnException() {
        // given
        final var expectedName = "John Doe";
        final var expectedEmail = "john@email.com";
        final var expectedDocument = "12345678900";
        final var expectedPassword = "123";
        final var expectedCarPlate = "AAA9999x";

        final var expectedErrorMessage = "Invalid car plate";

        // when
        final var actualException = assertThrows(RuntimeException.class, () -> Driver.create(
                expectedName,
                expectedEmail,
                expectedDocument,
                expectedCarPlate,
                expectedPassword
        ));

        // then
        assertEquals(expectedErrorMessage, actualException.getMessage());
    }
}