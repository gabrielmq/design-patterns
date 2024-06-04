package io.github.gabrielmsouza.creational.singleton;

import java.util.UUID;

public class User {
    private String id;
    private String name;
    private String email;
    private String password;

    private User(String id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public static User create(String name, String email, String password) {
        return new User(UUID.randomUUID().toString(), name, email, password);
    }

    public boolean passwordMatches(String password) {
        return this.password.equalsIgnoreCase(password);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
