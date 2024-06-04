package io.github.gabrielmsouza.creational.abstractfactory.repositories.factories;

import io.github.gabrielmsouza.creational.abstractfactory.domain.InstallmentGateway;
import io.github.gabrielmsouza.creational.abstractfactory.domain.LoanGateway;
import io.github.gabrielmsouza.creational.abstractfactory.repositories.InstallmentInMemoryGateway;
import io.github.gabrielmsouza.creational.abstractfactory.repositories.LoanInMemoryGateway;
import io.github.gabrielmsouza.creational.abstractfactory.repositories.factories.GatewaysFactory;

// implementação concreta da abstract factory para a criação de gateways em memória
public class GatewayInMemoryFactory implements GatewaysFactory {
    @Override
    public LoanGateway createLoanGateway() {
        return LoanInMemoryGateway.getInstance();
    }

    @Override
    public InstallmentGateway createInstallmentGateway() {
        return InstallmentInMemoryGateway.getInstance();
    }
}
