package io.github.gabrielmsouza.structural.decorator;

public class SecurityDecorator<IN, OUT> implements UseCase<IN, OUT> {
    private final UseCase<IN, OUT> useCase;

    public SecurityDecorator(UseCase<IN, OUT> useCase) {
        this.useCase = useCase;
    }

    @Override
    public OUT execute(IN input) {
        System.out.println("SecurityDecorator: Checking permissions");
        return this.useCase.execute(input);
    }
}
