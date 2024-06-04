package io.github.gabrielmsouza.creational.abstractfactory.repositories;

import io.github.gabrielmsouza.creational.abstractfactory.domain.Installment;
import io.github.gabrielmsouza.creational.abstractfactory.domain.InstallmentGateway;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class InstallmentInMemoryGateway implements InstallmentGateway {
    private static InstallmentInMemoryGateway INSTANCE;

    private final Map<String, Installment> db;

    private InstallmentInMemoryGateway() {
        this.db = new HashMap<>();
    }

    public static InstallmentInMemoryGateway getInstance() {
        if (Objects.isNull(INSTANCE)) {
            INSTANCE = new InstallmentInMemoryGateway();
        }
        return INSTANCE;
    }

    @Override
    public Installment save(final Installment installment) {
        this.db.put(installment.getLoanId(), installment);
        return installment;
    }

    @Override
    public List<Installment> getByLoanId(final String loanId) {
        return this.db.values().stream().filter(it -> it.getLoanId().equalsIgnoreCase(loanId)).toList();
    }
}
