package io.github.gabrielmsouza.creational.abstractfactory.domain;

import java.util.List;
import java.util.Optional;

public interface InstallmentGateway {
    Installment save(Installment installment);
    List<Installment> getByLoanId(String loanId);
}
