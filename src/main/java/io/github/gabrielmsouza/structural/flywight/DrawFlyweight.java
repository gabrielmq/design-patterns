package io.github.gabrielmsouza.structural.flywight;

import java.time.LocalDate;

// Extraindo o que era comum e repetivel na classe LotteryTicket para
// adicionar ao cache
public class DrawFlyweight {
    private String draw;
    private LocalDate date;

    public DrawFlyweight(String draw, LocalDate date) {
        this.draw = draw;
        this.date = date;
    }

    public String getDraw() {
        return draw;
    }

    public LocalDate getDate() {
        return date;
    }
}
