package io.github.gabrielmsouza.structural.bridge;

public interface Password {
    String value();
    boolean validate(String password);
}
