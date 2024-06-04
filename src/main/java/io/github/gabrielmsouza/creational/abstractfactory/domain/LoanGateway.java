package io.github.gabrielmsouza.creational.abstractfactory.domain;

import java.util.Optional;

public interface LoanGateway {
    Loan save(Loan loan);
    Optional<Loan> findById(String id);
}
