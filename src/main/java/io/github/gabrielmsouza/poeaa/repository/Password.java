package io.github.gabrielmsouza.poeaa.repository;

public record Password(String value) {
    public Password {
        if (value.length() < 8)
            throw new RuntimeException("Minimum length is 8");
    }
}
