package io.github.gabrielmsouza.structural.bridge;

public record PlainTextPassword(String value) implements Password {
    public static PlainTextPassword create(final String value) {
        return new PlainTextPassword(value);
    }

    @Override
    public boolean validate(final String password) {
        return value().equalsIgnoreCase(password);
    }
}
