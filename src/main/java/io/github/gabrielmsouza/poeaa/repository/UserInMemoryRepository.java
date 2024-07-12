package io.github.gabrielmsouza.poeaa.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UserInMemoryRepository implements UserRepository {
    private Map<String, User> db;

    public UserInMemoryRepository() {
        this.db = new ConcurrentHashMap<>();
    }

    @Override
    public void save(User user) {
        this.db.put(user.email(), user);
    }

    @Override
    public void update(User user) {
        this.db.replace(user.email(), this.db.get(user.email()),  user);
    }

    @Override
    public void delete(User user) {
        this.db.remove(user.email());
    }

    @Override
    public List<User> list() {
        return new ArrayList<>(this.db.values());
    }

    @Override
    public User findByEmail(String email) {
        return this.db.get(email);
    }
}
