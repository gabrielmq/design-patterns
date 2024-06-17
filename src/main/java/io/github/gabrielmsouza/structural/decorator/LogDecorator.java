package io.github.gabrielmsouza.structural.decorator;

public class LogDecorator<IN, OUT> implements UseCase<IN, OUT> {
    private final UseCase<IN, OUT> useCase;

    public LogDecorator(UseCase<IN, OUT> useCase) {
        this.useCase = useCase;
    }

    @Override
    public OUT execute(IN input) {
        System.out.println("LogDecorator: Executing use case");
        return this.useCase.execute(input);
    }
}
