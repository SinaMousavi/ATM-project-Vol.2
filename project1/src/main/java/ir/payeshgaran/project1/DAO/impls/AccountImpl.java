package ir.payeshgaran.project1.DAO.impls;

import ir.payeshgaran.project1.DAO.AccountDAO;
import ir.payeshgaran.project1.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public class AccountImpl implements AccountDAO {

    @Autowired
    private HibernateTemplate hibernateTemplate;

    public void addAccount(Account account) {
        hibernateTemplate.save(account);
    }

    public List<?> findByAccountNumber(String accountNumber) {
        return hibernateTemplate.find("from Account a where a.accountNumber = '" + accountNumber + "'");
    }

    public List<?> findById(Long id) {
        return hibernateTemplate.find("from Account a where a.id = '" + id + "'");
    }

    public List<Account> getAllAccounts() {
        return hibernateTemplate.loadAll(Account.class);
    }


}