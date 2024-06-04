package io.github.gabrielmsouza.creational.abstractfactory.domain;

import java.util.UUID;

public class CarLoan extends Loan {

    private static final int RATE = 15;

    private CarLoan(
            final String id,
            final double amount,
            final double income,
            final int installments
    ) {
        super(id, amount, income, installments, "car");
    }

    @Override
    public double getRate() {
        return RATE;
    }

    public static Loan create(
            final double amount,
            final double income,
            final int installments
    ) {
        if (installments > 60)
            throw new RuntimeException("The maximum number of installments for car loan is 60");

        if ((income * 0.30) < amount/installments)
            throw new RuntimeException("The installment amount could not exceed 30% of monthly income");

        return new CarLoan(UUID.randomUUID().toString(), amount, income, installments);
    }
}
