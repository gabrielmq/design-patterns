package io.github.gabrielmsouza.poeaa.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserInMemoryRepositoryTest {

    private UserInMemoryRepository userInMemoryRepository;

    @BeforeEach
    void setup() {
        userInMemoryRepository = new UserInMemoryRepository();
    }

    @Test
    void deveSalvarUmNovoUsuario() {
        final var email = "john.doe@gmail.com";
        final var user = new User("John Doe", email, "abc123456");

        userInMemoryRepository.save(user);

        final var savedUser = userInMemoryRepository.findByEmail(email);
        assertEquals("John Doe", savedUser.name());
        assertEquals(email, savedUser.email());
        assertEquals("abc123456", savedUser.password());
        assertEquals(User.Status.ACTIVE, savedUser.status());

        userInMemoryRepository.delete(user);
    }

    @Test
    void deveAtualizarUmUsuario() {
        final var email = "john.doe@gmail.com";
        final var user = new User("John Doe", email, "abc123456");

        userInMemoryRepository.save(user);

        final var savedUser = userInMemoryRepository.findByEmail(email);
        savedUser.updatePassword("asd456789");

        userInMemoryRepository.update(savedUser);

        final var updatedUser = userInMemoryRepository.findByEmail(email);
        assertEquals("asd456789", updatedUser.password());

        userInMemoryRepository.delete(user);
    }

    @Test
    void deveListar3Usuarios() {
        userInMemoryRepository.save(new User("John Doe", "john.doe1@gmail.com", "abc123456"));
        userInMemoryRepository.save(new User("John Doe", "john.doe2@gmail.com", "abc123456"));
        userInMemoryRepository.save(new User("John Doe", "john.doe3@gmail.com", "abc123456"));

        final var users = userInMemoryRepository.list();
        assertEquals(3, users.size());

        userInMemoryRepository.delete(users.get(0));
        userInMemoryRepository.delete(users.get(1));
        userInMemoryRepository.delete(users.get(2));
    }
}