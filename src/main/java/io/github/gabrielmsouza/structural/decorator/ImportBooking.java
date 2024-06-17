package io.github.gabrielmsouza.structural.decorator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Decorator é uma classe wrapper que incorpora e adiciona comportamento a um objeto
public class ImportBooking implements UseCase<ImportBooking.Input, ImportBooking.Output> {
    final UseCase<BookRoom.Input, BookRoom.Output> useCase;

    public ImportBooking(UseCase<BookRoom.Input, BookRoom.Output> useCase) {
        this.useCase = useCase;
    }

    @Override
    public Output execute(Input input) {
        final var codes = new ArrayList<String>();
        final var lines = Arrays.stream(input.file().split("\n")).skip(1);
        lines.forEach(line -> {
            final var fields = line.split(";");
            final var email = fields[0];
            final var checkin = LocalDateTime.parse(fields[1]);
            final var checkout = LocalDateTime.parse(fields[2]);
            final var category = fields[3];
            final var in = new BookRoom.Input(email, checkin, checkout, category);
            final var out = useCase.execute(in);
            codes.add(out.code());
        });
        return new Output(codes);
    }

    public record Input(String file) {}

    public record Output(List<String> codes) {}
}
