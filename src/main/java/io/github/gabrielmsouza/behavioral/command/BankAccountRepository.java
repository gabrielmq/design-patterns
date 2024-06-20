package io.github.gabrielmsouza.behavioral.command;

public interface BankAccountRepository {
    void save(BankAccount bankAccount);
    BankAccount findById(String id);
    void update(BankAccount bankAccount);
}
