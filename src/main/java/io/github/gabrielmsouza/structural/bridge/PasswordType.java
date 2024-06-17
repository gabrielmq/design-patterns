package io.github.gabrielmsouza.structural.bridge;

import java.util.Objects;
import java.util.function.Function;

public enum PasswordType {
    PLAIN(PlainTextPassword::create),
    SHA1(SHA1Password::create);

    private final Function<String, Password> createFn;

    PasswordType(final Function<String, Password> createFn) {
        this.createFn = Objects.requireNonNull(createFn);
    }

    public Password create(final String value) {
        return createFn.apply(value);
    }
}
