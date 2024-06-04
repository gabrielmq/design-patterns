package io.github.gabrielmsouza.creational.abstractfactory.domain;

import java.util.UUID;

public class Installment {
    private String loanId;
    private double number;
    private double amount;
    private double amortization;
    private double interest;
    private double balance;

    private Installment(
            final String loanId,
            final double number,
            final double amount,
            final double amortization,
            final double interest,
            final double balance
    ) {
        this.loanId = loanId;
        this.number = number;
        this.amount = amount;
        this.amortization = amortization;
        this.interest = interest;
        this.balance = balance;
    }

    public static Installment create(
            final String loanId,
            final double number,
            final double amount,
            final double amortization,
            final double interest,
            final double balance
    ) {
        return new Installment(loanId, number, amount, amortization, interest, balance);
    }

    public String getLoanId() {
        return loanId;
    }

    public double getNumber() {
        return number;
    }

    public double getAmount() {
        return amount;
    }

    public double getAmortization() {
        return amortization;
    }

    public double getInterest() {
        return interest;
    }

    public double getBalance() {
        return balance;
    }
}
