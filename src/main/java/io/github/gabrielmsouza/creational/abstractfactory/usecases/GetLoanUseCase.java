package io.github.gabrielmsouza.creational.abstractfactory.usecases;

import io.github.gabrielmsouza.creational.abstractfactory.domain.Installment;
import io.github.gabrielmsouza.creational.abstractfactory.domain.InstallmentGateway;
import io.github.gabrielmsouza.creational.abstractfactory.domain.LoanGateway;
import io.github.gabrielmsouza.creational.abstractfactory.repositories.factories.GatewaysFactory;

import java.util.List;
import java.util.Objects;

public class GetLoanUseCase {
    private final LoanGateway loanGateway;
    private final InstallmentGateway installmentGateway;

    public GetLoanUseCase(final GatewaysFactory gatewaysFactory) {
        this.loanGateway = Objects.requireNonNull(gatewaysFactory).createLoanGateway();
        this.installmentGateway = Objects.requireNonNull(gatewaysFactory).createInstallmentGateway();
    }

    public Output execute(final String id) {
        final var loan = this.loanGateway.findById(id).orElseThrow();
        final var installments = this.installmentGateway.getByLoanId(id);
        return new Output(loan.getAmount(), loan.getIncome(), installments);
    }

    public record Output(double amount, double income, List<Installment> installments) {}
}
