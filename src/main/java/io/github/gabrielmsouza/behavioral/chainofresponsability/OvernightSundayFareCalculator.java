package io.github.gabrielmsouza.behavioral.chainofresponsability;

public class OvernightSundayFareCalculator extends AbstractFareCalculator {
    private static final double FARE = 5;

    public OvernightSundayFareCalculator(final FareCalculator next) {
        super(next);
    }

    @Override
    public double calculate(final Segment segment) {
        if (segment.isOvernight() && segment.isSunday()) {
            return segment.getDistance() * FARE;
        }
        if (!hasNext())
            throw new RuntimeException();
        return this.next.calculate(segment);
    }
}
