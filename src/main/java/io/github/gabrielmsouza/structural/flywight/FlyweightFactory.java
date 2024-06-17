package io.github.gabrielmsouza.structural.flywight;

import java.time.LocalDate;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

// Aplicando o padrão Flyweight para evitar a criação de muitos objetos, criando uma
// especie de cache para reduzir o consumo de memória e a quantidade de objetos criados no heap.
public class FlyweightFactory {
    private static final Map<String, DrawFlyweight> CACHE = new ConcurrentHashMap<>();

    public static DrawFlyweight getDraw(final String draw, final LocalDate date) {
        final var key = "%s;%s".formatted(draw, date);
        return CACHE.computeIfAbsent(key, v -> new DrawFlyweight(draw, date));
    }
}
