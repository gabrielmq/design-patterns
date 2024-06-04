package io.github.gabrielmsouza.creational.abstractfactory.domain.factories;

import io.github.gabrielmsouza.creational.abstractfactory.domain.InstallmentCalculator;
import io.github.gabrielmsouza.creational.abstractfactory.domain.Loan;
import io.github.gabrielmsouza.creational.abstractfactory.domain.MortgageLoan;
import io.github.gabrielmsouza.creational.abstractfactory.domain.SACInstallmentCalculator;

// implementação concreta da abstract factory para a criação da familia de objetos
// relacionados a Loan
public class MortgageLoanFactory implements LoanFactory {
    @Override
    public Loan createLoan(double amount, double income, int installments) {
        return MortgageLoan.create(amount, income, installments);
    }

    @Override
    public InstallmentCalculator createInstallmentCalculator() {
        return new SACInstallmentCalculator();
    }
}
