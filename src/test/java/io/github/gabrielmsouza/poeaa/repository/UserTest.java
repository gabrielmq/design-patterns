package io.github.gabrielmsouza.poeaa.repository;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void deveCriarUmNovoUsuario() {
        final var user = new User("john", "john@email", "123456");
        assertEquals("john", user.name());
        assertEquals("john@email", user.email());
        assertEquals("123456", user.password());
    }

    @Test
    void deveModificarASenhaDeUsuario() {
        final var user = new User("john", "john@email", "123456");
        user.updatePassword("654321");
        assertEquals("654321", user.password());
    }

    @Test
    void naoDeveModificarSenhaComMenosDe8Caracteres() {
        final var user = new User("john", "john@email", "123456");
        assertThrows(RuntimeException.class, () -> user.updatePassword("123"));
    }

    @Test
    void naoDeveModificarEmailComEmailInvalido() {
        final var user = new User("john", "john@email", "123456");
        assertThrows(RuntimeException.class, () -> user.updateEmail("john"));
    }

    @Test
    void deveBloquearUsuario() {
        final var user = new User("john", "john@email", "123456");
        user.block();
        assertEquals(User.Status.BLOCKED, user.status());
    }
}