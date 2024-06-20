package io.github.gabrielmsouza.behavioral.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    @Test
    void deveFazerUmaTransferencia_Entre2Contas() {
        var from = new BankAccount("1");
        var to = new BankAccount("2");

        assertEquals(0, from.getBalance());
        assertEquals(0, to.getBalance());

        from.debit(100);
        to.credit(100);

        assertEquals(-100, from.getBalance());
        assertEquals(100, to.getBalance());
    }

    @Test
    void deveFazerUmaTransferencia_Entre2Contas_UsandoCommand() {
        var from = new BankAccount("1");
        var to = new BankAccount("2");

        assertEquals(0, from.getBalance());
        assertEquals(0, to.getBalance());

        var transfer = new TransferCommand(from, to, 100);
        transfer.execute();

        assertEquals(-100, from.getBalance());
        assertEquals(100, to.getBalance());
    }
}