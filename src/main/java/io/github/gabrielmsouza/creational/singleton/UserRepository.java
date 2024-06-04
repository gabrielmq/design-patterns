package io.github.gabrielmsouza.creational.singleton;

import java.util.Optional;

public interface UserRepository {
    User save(User user);
    Optional<User> findByEmail(String email);
}
