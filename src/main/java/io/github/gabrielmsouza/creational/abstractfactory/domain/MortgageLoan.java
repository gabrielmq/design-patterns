package io.github.gabrielmsouza.creational.abstractfactory.domain;

import java.util.UUID;

public class MortgageLoan extends Loan {
    private static final double RATE = 10.0;
    private static final int MAX_INSTALLMENTS = 420;
    private static final double INCOME_PERCENTAGE = 0.25;

    private MortgageLoan(
            final String id,
            final double amount,
            final double income,
            final int installments
    ) {
        super(id, amount, income, installments, "mortgage");
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
        if (installments > MAX_INSTALLMENTS)
            throw new RuntimeException("The maximum number of installments for mortgage loan is 420");

        if ((income * INCOME_PERCENTAGE) < amount/installments)
            throw new RuntimeException("The installment amount could not exceed 25% of monthly income");

        return new MortgageLoan(UUID.randomUUID().toString(), amount, income, installments);
    }
}
