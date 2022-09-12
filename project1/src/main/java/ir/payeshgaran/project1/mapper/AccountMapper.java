package ir.payeshgaran.project1.mapper;


import ir.payeshgaran.project1.dto.AccountDTO;
import ir.payeshgaran.project1.model.Account;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class AccountMapper {


    private Account account;
    private AccountDTO accountDTO;

    public AccountMapper() {

    }


    /*public User toEntity(AccountDTO accountDTO) {
        User user = new User();
        user.setAccountNumber(accountDTO.getAccountNumber());
        user.setAccountBalance(accountDTO.getBalance());
        user.setPassword(accountDTO.getPassword());
        return user;
    }*/

    public AccountDTO toDTO(Account account) {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setAccountNumber(account.getAccountNumber());
        accountDTO.setBalance(account.getAccountBalance());
        return accountDTO;
    }

/*    public List<User> toEntities(List<AccountDTO> accountDTOS) {
        List<User> users = new ArrayList<>();
        for (AccountDTO dto : accountDTOS) {
            User user = new User();
            user.setAccountNumber(dto.getAccountNumber());
            user.setPassword(dto.getPassword());
            user.setAccountBalance(dto.getBalance());
            users.add(user);

        }
        return users;
    }*/


    public List<AccountDTO> toDTOs(List<Account> accounts) {
        List<AccountDTO> accountDTOS = new ArrayList<>();
        for (Account account : accounts) {
            AccountDTO accountDTO = new AccountDTO();
            accountDTO.setAccountNumber(account.getAccountNumber());
            accountDTO.setBalance(account.getAccountBalance());
            accountDTOS.add(accountDTO);
        }

        return accountDTOS;
    }
}
