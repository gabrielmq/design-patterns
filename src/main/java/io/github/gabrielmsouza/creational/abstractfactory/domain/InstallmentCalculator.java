package io.github.gabrielmsouza.creational.abstractfactory.domain;

import java.util.List;

// Pode ser considerado um Domain Service, pois possui regras de negócio mas não é uma
// entidade de domínio por não ter identidade. Sua responsabilidade é apenas realizar um calculo
public interface InstallmentCalculator {
    List<Installment> calculate(Loan loan);
}
