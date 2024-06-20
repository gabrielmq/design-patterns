package io.github.gabrielmsouza.behavioral.command;

public class MakeTransfer {
    private final BankAccountRepository repository;

    public MakeTransfer(BankAccountRepository repository) {
        this.repository = repository;
    }

    public void execute(final Input input) {
        final var from = repository.findById(input.fromId);
        final var to = repository.findById(input.toId);
        new TransferCommand(from, to, input.amount).execute();
        repository.update(from);
        repository.update(to);
    }

    public record Input(String fromId, String toId, double amount) {}
}
