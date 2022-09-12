package ir.payeshgaran.project1.DAO;

import ir.payeshgaran.project1.model.Transaction;

import java.util.List;

public interface TransactionDAO {

    void addTransaction(Transaction transaction);

    List<?> lastTenTransactions(Long id);
}