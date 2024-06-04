package io.github.gabrielmsouza.creational.abstractfactory.domain;

public abstract class Loan {
    private String id;
    private double amount;
    private double income;
    private int installments;
    private String type;

    Loan(final String id, final double amount, final double income, final int installments, final String type) {
        this.id = id;
        this.amount = amount;
        this.income = income;
        this.installments = installments;
        this.type = type;
    }

    public abstract double getRate();

    public String getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public double getIncome() {
        return income;
    }

    public int getInstallments() {
        return installments;
    }

    public String getType() {
        return type;
    }
}
