package io.github.gabrielmsouza.creational.abstractfactory.domain;

import java.util.ArrayList;
import java.util.List;

public class PriceInstallmentCalculator implements InstallmentCalculator {
    @Override
    public List<Installment> calculate(final Loan loan) {
        double balance = loan.getAmount();
        double rate = loan.getRate() / 1200;
        double formula = Math.pow(1 + rate, loan.getInstallments());
        double amount = balance * rate * formula / (formula - 1);

        List<Installment> installments = new ArrayList<>();
        for (int installmentNumber = 1; balance > 2; installmentNumber++) {
            double interest = balance * rate;
            double amortization = amount - interest;
            balance -= amortization;
            installments.add(Installment.create(loan.getId(), installmentNumber, amount, interest, amortization, balance));
        }
        return installments;
    }
}
