package ir.payeshgaran.project1.service;

import ir.payeshgaran.project1.model.Account;

import java.util.List;
import java.util.List;

public interface AccountService {

    void addAccount(Account account);

    List<Account> allAccounts();

    List<?> findByAccountNumber(String accountNumber);

}