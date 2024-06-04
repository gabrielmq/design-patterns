package io.github.gabrielmsouza.creational.abstractfactory.domain.factories;

import io.github.gabrielmsouza.creational.abstractfactory.domain.*;

// implementação concreta da abstract factory para a criação da familia de objetos
// relacionados a Loan
public class CarLoanFactory implements LoanFactory {
    @Override
    public Loan createLoan(double amount, double income, int installments) {
        return CarLoan.create(amount, income, installments);
    }

    @Override
    public InstallmentCalculator createInstallmentCalculator() {
        return new PriceInstallmentCalculator();
    }
}
