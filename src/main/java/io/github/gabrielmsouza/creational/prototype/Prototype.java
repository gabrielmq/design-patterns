package io.github.gabrielmsouza.creational.prototype;

// Criando uma interface Prototype que define o método copy que será
// implementado pelas classes que desejam ser clonadas
public interface Prototype {
    Prototype copy();
}
