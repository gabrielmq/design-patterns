package io.github.gabrielmsouza.creational.abstractfactory.usecases;

import io.github.gabrielmsouza.creational.abstractfactory.domain.InstallmentGateway;
import io.github.gabrielmsouza.creational.abstractfactory.domain.LoanGateway;
import io.github.gabrielmsouza.creational.abstractfactory.domain.MortgageLoan;
import io.github.gabrielmsouza.creational.abstractfactory.domain.SACInstallmentCalculator;
import io.github.gabrielmsouza.creational.abstractfactory.domain.factories.LoanFactory;
import io.github.gabrielmsouza.creational.abstractfactory.repositories.factories.GatewaysFactory;

import java.util.Objects;

public class ApplyForLoanUseCase {
    private final LoanGateway loanGateway;
    private final InstallmentGateway installmentGateway;
    private final LoanFactory loanFactory;

    public ApplyForLoanUseCase(final GatewaysFactory gatewaysFactory, final LoanFactory loanFactory) {
        this.loanGateway = Objects.requireNonNull(gatewaysFactory).createLoanGateway();
        this.installmentGateway = Objects.requireNonNull(gatewaysFactory).createInstallmentGateway();
        this.loanFactory = Objects.requireNonNull(loanFactory);
    }

    public Output execute(final Input input) {
        final var loan = this.loanFactory.createLoan(input.amount, input.income, input.installments);
        final var installments = this.loanFactory.createInstallmentCalculator().calculate(loan);
        this.loanGateway.save(loan);
        installments.forEach(this.installmentGateway::save);
        return new Output(loan.getId());
    }

    public record Input(double amount, double income, int installments, String type) {}

    public record Output(String id) {}
}
