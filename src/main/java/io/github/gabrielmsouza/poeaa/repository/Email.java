package io.github.gabrielmsouza.poeaa.repository;

public record Email(String value) {
    public Email {
        if (!value.matches("^(.+)@(.+)$"))
            throw new RuntimeException("Invalid email");
    }
}
