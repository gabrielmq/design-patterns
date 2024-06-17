package io.github.gabrielmsouza.behavioral.strategy;

import java.time.LocalDateTime;

// aplicando o padrão Strategy para calcular a tarifa de acordo com a localização
// cada localização tem uma regra de cálculo diferente para a tarifa
public enum Location {
    AIRPORT(new AirportFareCalculator()),
    BEACH(new BeachFareCalculator()),
    SHOPPING(new ShoppingFareCalculator()),
    PUBLIC(new PublicFareCalculator());

    private final FareCalculator fareCalculator;

    Location(final FareCalculator fareCalculator) {
        this.fareCalculator = fareCalculator;
    }

    public double calculate(final LocalDateTime checkInDate, final LocalDateTime checkOutDate) {
        return fareCalculator.calculate(checkInDate, checkOutDate);
    }
}
