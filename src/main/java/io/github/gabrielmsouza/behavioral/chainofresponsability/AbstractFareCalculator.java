package io.github.gabrielmsouza.behavioral.chainofresponsability;

import java.util.Objects;

public abstract class AbstractFareCalculator implements FareCalculator {
    protected FareCalculator next;

    public AbstractFareCalculator(final FareCalculator next) {
        this.next = next;
    }

    public boolean hasNext() {
        return Objects.nonNull(next);
    }
}
