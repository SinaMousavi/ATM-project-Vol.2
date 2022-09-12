package ir.payeshgaran.project1.DAO.impls;

import ir.payeshgaran.project1.DAO.TransactionDAO;
import ir.payeshgaran.project1.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository

public class TransactionImpl implements TransactionDAO {

    @Autowired
    private HibernateTemplate hibernateTemplate;

    public void addTransaction(Transaction transaction) {
        hibernateTemplate.save(transaction);
    }


    public List<?> lastTenTransactions(Long id) {
        hibernateTemplate.setMaxResults(10);
        return hibernateTemplate.find("from Transaction t where t.depositorId='" + id + "' or t.receiverId='" + id + "'order by t.id  desc ");
    }


}