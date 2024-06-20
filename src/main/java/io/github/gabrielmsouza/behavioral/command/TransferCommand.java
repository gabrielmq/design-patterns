package io.github.gabrielmsouza.behavioral.command;

// Aplicando o padrão Command para transferência de valores entre contas bancárias
public class TransferCommand implements Command {
    private final BankAccount from;
    private final BankAccount to;
    private final double amount;

    // O command trafega o que deve acontecer dentro dele, por isso passamos as informações
    // necessárias para a execução do comando no construtor
    public TransferCommand(BankAccount from, BankAccount to, double amount) {
        this.from = from;
        this.to = to;
        this.amount = amount;
    }


    @Override
    public void execute() {
        this.from.debit(this.amount);
        this.to.credit(this.amount);
    }
}
