package io.github.gabrielmsouza.structural.flywight;

public class LotteryTicket {
    private DrawFlyweight draw;
    private String bet1;
    private String bet2;
    private String bet3;
    private String bet4;
    private String bet5;
    private String bet6;

    public LotteryTicket(DrawFlyweight draw, String bet1, String bet2, String bet3, String bet4, String bet5, String bet6) {
        this.draw = draw;
        this.bet1 = bet1;
        this.bet2 = bet2;
        this.bet3 = bet3;
        this.bet4 = bet4;
        this.bet5 = bet5;
        this.bet6 = bet6;
    }

    public DrawFlyweight getDraw() {
        return draw;
    }

    public String getBet1() {
        return bet1;
    }

    public String getBet2() {
        return bet2;
    }

    public String getBet3() {
        return bet3;
    }

    public String getBet4() {
        return bet4;
    }

    public String getBet5() {
        return bet5;
    }

    public String getBet6() {
        return bet6;
    }
}
