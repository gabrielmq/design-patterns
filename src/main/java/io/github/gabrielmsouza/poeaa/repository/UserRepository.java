package io.github.gabrielmsouza.poeaa.repository;

import java.util.List;

// faz a mediação entre os obj de dominio e o mecanismo de persistencia
public interface UserRepository {
    void save(User user);
    void update(User user);
    void delete(User user);
    List<User> list();
    User findByEmail(String email);
}
