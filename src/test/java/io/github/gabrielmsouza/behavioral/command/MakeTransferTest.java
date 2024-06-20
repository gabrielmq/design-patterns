package io.github.gabrielmsouza.behavioral.command;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MakeTransferTest {

    @Mock
    private BankAccountRepository repository;

    @InjectMocks
    private MakeTransfer makeTransfer;

    @Test
    void deveFazerUmaTransferencia_Entre2Contas() {
        var from = new BankAccount("1");
        var to = new BankAccount("2");

        when(repository.findById("1")).thenReturn(from);
        when(repository.findById("2")).thenReturn(to);

        doNothing().when(repository).update(from);
        doNothing().when(repository).update(to);

        final var input = new MakeTransfer.Input(from.getBankAccountId(), to.getBankAccountId(), 100);

        makeTransfer.execute(input);

        assertEquals(-100, from.getBalance());
        assertEquals(100, to.getBalance());
    }
}