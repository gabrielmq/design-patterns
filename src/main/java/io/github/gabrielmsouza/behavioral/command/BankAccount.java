package io.github.gabrielmsouza.behavioral.command;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BankAccount {
    private String bankAccountId;
    private List<Transaction> transactions;

    public BankAccount(String bankAccountId) {
        this.bankAccountId = bankAccountId;
        this.transactions = new ArrayList<>();
    }

    public void debit(double amount) {
        transactions.add(Transaction.debit(amount));
    }

    public void credit(double amount) {
        transactions.add(Transaction.credit(amount));
    }

    public double getBalance() {
        double total = 0;
        for (Transaction transaction : this.transactions) {
            if (transaction.getType() == Transaction.Type.CREDIT) {
                total += transaction.getAmount();
            }
            if (transaction.getType() == Transaction.Type.DEBIT) {
                total -= transaction.getAmount();
            }
        }
        return total;
    }

    public String getBankAccountId() {
        return bankAccountId;
    }

    public List<Transaction> getTransactions() {
        return Collections.unmodifiableList(transactions);
    }
}
