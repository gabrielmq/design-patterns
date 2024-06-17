package io.github.gabrielmsouza.structural.bridge;

import com.google.common.hash.Hashing;

import static java.nio.charset.StandardCharsets.UTF_8;

public record SHA1Password(String value) implements Password {
    public static SHA1Password create(final String value) {
        return new SHA1Password(hash(value));
    }

    @Override
    public boolean validate(final String password) {
        return value().equalsIgnoreCase(hash(password));
    }

    private static String hash(final String value) {
        return Hashing.sha1().hashString(value, UTF_8).toString();
    }
}
