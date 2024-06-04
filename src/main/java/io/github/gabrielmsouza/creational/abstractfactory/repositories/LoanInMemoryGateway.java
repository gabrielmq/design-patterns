package io.github.gabrielmsouza.creational.abstractfactory.repositories;

import io.github.gabrielmsouza.creational.abstractfactory.domain.Loan;
import io.github.gabrielmsouza.creational.abstractfactory.domain.LoanGateway;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class LoanInMemoryGateway implements LoanGateway {
    private static LoanInMemoryGateway INSTANCE;
    private final Map<String, Loan> db;

    private LoanInMemoryGateway() {
        this.db = new HashMap<>();
    }

    public static LoanInMemoryGateway getInstance() {
        if (Objects.isNull(INSTANCE)) {
            INSTANCE = new LoanInMemoryGateway();
        }
        return INSTANCE;
    }

    @Override
    public Loan save(final Loan loan) {
        db.put(loan.getId(), loan);
        return loan;
    }

    @Override
    public Optional<Loan> findById(final String id) {
        return Optional.ofNullable(db.get(id));
    }
}
