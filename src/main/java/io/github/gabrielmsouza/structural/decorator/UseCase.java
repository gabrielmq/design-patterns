package io.github.gabrielmsouza.structural.decorator;

import java.util.List;

public interface UseCase<IN, OUT> {
    OUT execute(IN input);
}
