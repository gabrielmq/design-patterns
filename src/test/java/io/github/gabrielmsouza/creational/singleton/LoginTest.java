package io.github.gabrielmsouza.creational.singleton;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginTest {

    private UserRepository userRepository;
    private Login useCase;

    @BeforeEach
    void setUp() {
        this.userRepository = UserInMemoryRepository.getInstance();
        this.useCase = new Login();
    }

    @Test
    void givenAValidEmailAndPassword_whenCallsLogin_thenReturnsSuccess() {
        // given
        final var expectedName = "test";
        final var expectedEmail = "test@test.com";
        final var expectedPassword = "123";

        this.userRepository.save(User.create(expectedName, expectedEmail, expectedPassword));

        final var input = new Login.Input(expectedEmail, expectedPassword);

        // when
        final var output = this.useCase.execute(input);

        // then
        assertTrue(output.success());
    }

    @Test
    void givenAnInvalidEmail_whenCallsLogin_thenReturnsFailure() {
        // given
        final var expectedName = "test";
        final var expectedEmail = "test@test.com";
        final var expectedPassword = "123";

        this.userRepository.save(User.create(expectedName, "xpto@xpto.com", expectedPassword));

        final var input = new Login.Input(expectedEmail, expectedPassword);

        // when
        final var output = this.useCase.execute(input);

        // then
        assertFalse(output.success());
    }

    @Test
    void givenAnInvalidPassword_whenCallsLogin_thenReturnsFailure() {
        // given
        final var expectedName = "test";
        final var expectedEmail = "test@test.com";
        final var expectedPassword = "123";

        this.userRepository.save(User.create(expectedName, expectedName, "321"));

        final var input = new Login.Input(expectedEmail, expectedPassword);

        // when
        final var output = this.useCase.execute(input);

        // then
        assertFalse(output.success());
    }
}