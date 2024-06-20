package io.github.gabrielmsouza.behavioral.chainofresponsability;

public class SundayFareCalculator extends AbstractFareCalculator {
    private static final double FARE = 2.9;

    public SundayFareCalculator(final FareCalculator next) {
        super(next);
    }

    @Override
    public double calculate(final Segment segment) {
        if (!segment.isOvernight() && segment.isSunday()) {
            return segment.getDistance() * FARE;
        }
        if (!hasNext())
            throw new RuntimeException();
        return this.next.calculate(segment);
    }
}
