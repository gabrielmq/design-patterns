package io.github.gabrielmsouza.behavioral.chainofresponsability;

public class NormalFareCalculator extends AbstractFareCalculator {
    private static final double FARE = 2.1;

    public NormalFareCalculator(final FareCalculator next) {
        super(next);
    }

    @Override
    public double calculate(final Segment segment) {
        if (!segment.isOvernight() && !segment.isSunday()) {
            return segment.getDistance() * FARE;
        }
        if (!hasNext())
            throw new RuntimeException();
        return this.next.calculate(segment);
    }


}
