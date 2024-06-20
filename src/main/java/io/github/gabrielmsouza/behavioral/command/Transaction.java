package io.github.gabrielmsouza.behavioral.command;

public class Transaction {
    public enum Type {
        CREDIT, DEBIT
    }

    private Type type;
    private double amount;

    public Transaction(Type type, double amount) {
        this.type = type;
        this.amount = amount;
    }

    public static Transaction debit(double amount) {
        return new Transaction(Transaction.Type.DEBIT, amount);
    }

    public static Transaction credit(double amount) {
        return new Transaction(Transaction.Type.CREDIT, amount);
    }

    public Type getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }
}
