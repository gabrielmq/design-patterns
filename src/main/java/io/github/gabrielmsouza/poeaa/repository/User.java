package io.github.gabrielmsouza.poeaa.repository;

public class User {
    public enum Status {
        ACTIVE, BLOCKED
    }

    private String name;
    private Password password;
    private Email email;
    private Status status;

    public User(String name, String email, String password) {
        this.name = name;
        this.email = new Email(email);
        this.password = new Password(password);
        this.status = Status.ACTIVE;
    }

    public void updatePassword(final String password) {
        this.password = new Password(password);
    }

    public void updateEmail(final String email) {
        this.email = new Email(email);
    }

    public void block() {
        if (this.status == Status.BLOCKED) {
            throw new RuntimeException("User is already blocked");
        }
        this.status = Status.BLOCKED;
    }

    public String name() {
        return name;
    }

    public String password() {
        return password.value();
    }

    public String email() {
        return email.value();
    }

    public Status status() {
        return status;
    }
}
