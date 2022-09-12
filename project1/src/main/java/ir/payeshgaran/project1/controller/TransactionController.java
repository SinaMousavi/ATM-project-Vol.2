package ir.payeshgaran.project1.controller;


import ir.payeshgaran.project1.dto.TransactionDTO;
import ir.payeshgaran.project1.model.Account;
import ir.payeshgaran.project1.model.Person;
import ir.payeshgaran.project1.service.implementation.AccountServiceImplementation;
import ir.payeshgaran.project1.service.implementation.PersonServiceImplementation;
import ir.payeshgaran.project1.service.implementation.TransactionServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    public TransactionServiceImplementation transactionService;

    @Autowired
    public AccountServiceImplementation accountService;

    @Autowired
    public PersonServiceImplementation personService;


    @PostMapping("/mainTransaction")
    public ResponseEntity<String> save(@RequestBody TransactionDTO transactionDTO, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = PersonController.getUsernameByToken(request, response);

        List persons = personService.getByUsername(username);
        Person person = (Person) persons.get(0);

        if (!checkIsMyAccount(person, transactionDTO.getDepositor()))
            return ResponseEntity.ok().body("This is not your account");

        String message = transactionService.addTransaction(transactionDTO, username, 10000000);

        return ResponseEntity.ok().body(message);
    }


    @PostMapping("/paya")
    public ResponseEntity<?> savePaya(@RequestBody TransactionDTO transactionDTO, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = PersonController.getUsernameByToken(request, response);

        List persons = personService.getByUsername(username);
        Person person = (Person) persons.get(0);

        if (!checkIsMyAccount(person, transactionDTO.getDepositor()))
            return ResponseEntity.ok().body("This is not your account");

        String message = transactionService.addTransaction(transactionDTO, username, 50000000);

        return ResponseEntity.ok().body(message);
    }

    @GetMapping("/lastTenTransactions")
    public ResponseEntity<?> getLastTenTransactions(@RequestBody String accountNumber, HttpServletRequest request, HttpServletResponse response) throws IOException {

        String username = PersonController.getUsernameByToken(request, response);
        List persons = personService.getByUsername(username);
        Person person = (Person) persons.get(0);

        if (!checkIsMyAccount(person, accountNumber))
            return ResponseEntity.ok().body("This is not your account");

        List accounts = accountService.findByAccountNumber(accountNumber);
        Long id = ((Account) accounts.get(0)).getId();
        List transactions = transactionService.lastTenTransactions(id);


        return ResponseEntity.ok().body(transactions);
    }


    public Boolean checkIsMyAccount(Person person, String depositorNumber) {
        boolean isMyAccount = false;

        for (Account account : person.getAccounts()) {
            if (account.getAccountNumber().equals(depositorNumber)) {
                isMyAccount = true;
                break;
            }
        }

        if (!isMyAccount)
            return false;
        return true;
    }

}
