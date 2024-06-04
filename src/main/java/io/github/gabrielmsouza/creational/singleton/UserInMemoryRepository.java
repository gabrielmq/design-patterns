package io.github.gabrielmsouza.creational.singleton;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class UserInMemoryRepository implements UserRepository {
    private static UserInMemoryRepository INSTANCE;

    private final Map<String, User> db;
    private final Map<String, User> emailIndex;

    public UserInMemoryRepository() {
        this.db = new ConcurrentHashMap<>();
        this.emailIndex = new ConcurrentHashMap<>();
    }

    // aplicando o padrão Singleton para garantir que apenas uma instância da classe seja criada
    public static UserInMemoryRepository getInstance() {
        if (Objects.isNull(INSTANCE))  {
            INSTANCE = new UserInMemoryRepository();
        }
        return INSTANCE;
    }

    @Override
    public User save(User user) {
        this.db.put(user.getId(), user);
        this.emailIndex.put(user.getEmail(), user);
        return user;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.ofNullable(this.emailIndex.get(email));
    }
}
