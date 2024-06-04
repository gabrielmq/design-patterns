package io.github.gabrielmsouza.creational.abstractfactory.domain.factories;

import io.github.gabrielmsouza.creational.abstractfactory.domain.InstallmentCalculator;
import io.github.gabrielmsouza.creational.abstractfactory.domain.Loan;

// Definição do contrato de uma abstract factory para a criação
// da familia de objetos relacionados a Loan
public interface LoanFactory {
    Loan createLoan(double amount, double income, int installments);
    InstallmentCalculator createInstallmentCalculator();
}
