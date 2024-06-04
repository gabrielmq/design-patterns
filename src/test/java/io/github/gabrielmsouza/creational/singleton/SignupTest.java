package io.github.gabrielmsouza.creational.singleton;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SignupTest {
    private UserRepository userRepository;
    private Signup useCase;

    @BeforeEach
    void setUp() {
        this.userRepository = UserInMemoryRepository.getInstance();
        this.useCase = new Signup();
    }

    @Test
    void givenAValidInput_whenCallsSignup_thenShouldBeOk() {
        // given
        final var expectedName = "test";
        final var expectedEmail = "test@test.com";
        final var expectedPassword = "123";

        final var input = new Signup.Input(expectedName, expectedEmail, expectedPassword);

        // when
        this.useCase.execute(input);

        // then
        final var persistedUser = this.userRepository.findByEmail(expectedEmail).get();
        assertNotNull(persistedUser.getId());
        assertEquals(expectedName, persistedUser.getName());
        assertEquals(expectedEmail, persistedUser.getEmail());
        assertEquals(expectedPassword, persistedUser.getPassword());
    }
}