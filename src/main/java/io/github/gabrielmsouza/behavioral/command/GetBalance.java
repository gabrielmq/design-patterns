package io.github.gabrielmsouza.behavioral.command;

public class GetBalance {
    private final BankAccountRepository repository;

    public GetBalance(BankAccountRepository repository) {
        this.repository = repository;
    }

    public Output execute(final String bankAccountId) {
        final var bankAccount = repository.findById(bankAccountId);
        return new Output(bankAccount.getBalance());
    }

    public record Output(double balance) {}
}
