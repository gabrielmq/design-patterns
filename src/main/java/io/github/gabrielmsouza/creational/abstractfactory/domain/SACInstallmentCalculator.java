package io.github.gabrielmsouza.creational.abstractfactory.domain;

import java.util.ArrayList;
import java.util.List;

public class SACInstallmentCalculator implements InstallmentCalculator {
    @Override
    public List<Installment> calculate(final Loan loan) {
        double balance = loan.getAmount();
        double rate = loan.getRate() / 1200;
        double amortization = balance / loan.getInstallments();

        List<Installment> installments = new ArrayList<>();
        for (int installmentNumber = 1; balance > 0.10; installmentNumber++) {
            double interest = balance * rate;
            double amount = interest + amortization;
            balance -= amortization;
            installments.add(Installment.create(loan.getId(), installmentNumber, amount, interest, amortization, balance));
        }
        return installments;
    }
}
