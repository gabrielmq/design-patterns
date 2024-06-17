package io.github.gabrielmsouza.structural.bridge;

public abstract class Account {
    private String name;
    private String email;
    private String document;
    private Password password;

    protected Account(String name, String email, String document, PasswordType passwordType, String password) {
        if (!name.matches("^[a-zA-Z]+ [a-zA-Z]+$")) throw new RuntimeException("Invalid name");
        if (!email.matches("^(.+)@(.+)$")) throw new RuntimeException("Invalid email");
        if (document.length() != 11) throw new RuntimeException("Invalid document");

        this.name = name;
        this.email = email;
        this.document = document;
        this.password = passwordType.create(password);
    }

    public boolean isValidPassword(final String password) {
        return this.password.validate(password);
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getDocument() {
        return document;
    }

    public Password getPassword() {
        return password;
    }
}
