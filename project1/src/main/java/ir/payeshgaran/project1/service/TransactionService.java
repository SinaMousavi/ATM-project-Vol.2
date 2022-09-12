package ir.payeshgaran.project1.service;

import ir.payeshgaran.project1.dto.TransactionDTO;

import java.util.List;

public interface TransactionService {

    String addTransaction(TransactionDTO transactionDTO, String username, double limit);

    List<?> lastTenTransactions(Long id);
}
