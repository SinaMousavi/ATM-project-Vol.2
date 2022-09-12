package ir.payeshgaran.project1.service.implementation;

import ir.payeshgaran.project1.DAO.impls.AccountImpl;
import ir.payeshgaran.project1.model.Account;
import ir.payeshgaran.project1.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AccountServiceImplementation implements AccountService {

    @Autowired
    AccountImpl accountDAO;

    @Override
    public void addAccount(Account account) {
        accountDAO.addAccount(account);
    }

    @Override
    public List<Account> allAccounts() {
        return accountDAO.getAllAccounts();
    }

    @Override
    public List<?> findByAccountNumber(String accountNumber) {
        return accountDAO.findByAccountNumber(accountNumber);
    }
}
