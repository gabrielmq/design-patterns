package io.github.gabrielmsouza.creational.abstractfactory.repositories.factories;

import io.github.gabrielmsouza.creational.abstractfactory.domain.InstallmentGateway;
import io.github.gabrielmsouza.creational.abstractfactory.domain.LoanGateway;

// Definição do contrato de uma abstract factory para a criação de gateways relacionados
public interface GatewaysFactory {
    LoanGateway createLoanGateway();
    InstallmentGateway createInstallmentGateway();
}
