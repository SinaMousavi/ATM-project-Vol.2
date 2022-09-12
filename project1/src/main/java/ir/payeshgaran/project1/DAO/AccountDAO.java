package ir.payeshgaran.project1.DAO;

import ir.payeshgaran.project1.model.Account;
import java.util.List;


public interface AccountDAO {

    void addAccount(Account account);

    List<?> findByAccountNumber(String accountNumber);

    List<?> findById(Long id);

    List<Account> getAllAccounts();
}

