package ir.payeshgaran.project1.service.implementation;

import ir.payeshgaran.project1.DAO.impls.AccountImpl;
import ir.payeshgaran.project1.DAO.impls.PersonImpl;
import ir.payeshgaran.project1.DAO.impls.TransactionImpl;
import ir.payeshgaran.project1.dto.TransactionDTO;
import ir.payeshgaran.project1.model.Account;
import ir.payeshgaran.project1.model.Transaction;
import ir.payeshgaran.project1.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class TransactionServiceImplementation implements TransactionService {

    @Autowired
    TransactionImpl transactionDAO;
    @Autowired
    AccountImpl accountDAO;
    @Autowired
    PersonImpl personDAO;


    @Override
    public String addTransaction(TransactionDTO transactionDTO, String username, double limit) {


        String depositorNumber = transactionDTO.getDepositor();
        String receiverNumber = transactionDTO.getReceiver();

        List depositors = accountDAO.findByAccountNumber(depositorNumber);


        if (depositors.size() == 0)
            return "Invalid Depositor";

        Account depositor = (Account) depositors.get(0);

        List receivers = accountDAO.findByAccountNumber(receiverNumber);
        if (receivers.size() == 0)
            return "Invalid Receiver";

        Account receiver = (Account) receivers.get(0);

        double sum = 0;


        if (depositor.getAccountBalance() <= transactionDTO.getAmount())
            return "You don't have enough money!";

        for (Transaction transaction : depositor.getTransactions()) {
            if (LocalDate.now().getDayOfMonth() == transaction.getCreatedAt().getDate())
                sum += transaction.getAmount();

        }

        sum += transactionDTO.getAmount();
        if (sum > limit)
            return "Daily transaction limit exceeded!";

        Transaction transaction = new Transaction(transactionDTO.getAmount(), depositor.getId(), receiver.getId(), transactionDTO.getType());

        if (depositor != null && receiver != null && depositor.getAccountBalance() > transaction.getAmount()) {
            depositor.setAccountBalance(depositor.getAccountBalance() - transaction.getAmount());
            receiver.setAccountBalance(receiver.getAccountBalance() + transaction.getAmount());
            depositor.getTransactions().add(transaction);
            transactionDAO.addTransaction(transaction);

        }
        return "Transaction done!";

    }

    @Override
    public List<?> lastTenTransactions(Long id) {
        return transactionDAO.lastTenTransactions(id);
    }
}

