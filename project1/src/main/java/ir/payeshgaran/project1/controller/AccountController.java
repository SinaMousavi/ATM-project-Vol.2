package ir.payeshgaran.project1.controller;


import ir.payeshgaran.project1.model.Account;
import ir.payeshgaran.project1.model.Person;
import ir.payeshgaran.project1.service.implementation.PersonServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private PersonServiceImplementation personService;


    @GetMapping("/accountBalance")
    public ResponseEntity<?> getAccountBalance(HttpServletRequest request, HttpServletResponse response, @RequestBody String accountNumber) throws IOException {
        String username = PersonController.getUsernameByToken(request, response);

        List persons = personService.getByUsername(username);
        Person person = (Person) persons.get(0);

        double amount = 0;


        boolean isMyAccount = false;
        for (Account account : person.getAccounts()) {
            if (account.getAccountNumber().equals(accountNumber)) {
                isMyAccount = true;
                amount = account.getAccountBalance();
                break;
            }
        }

        if (!isMyAccount) {
            return ResponseEntity.ok("This is not your account number");
        } else {
            return ResponseEntity.ok().body(amount);
        }
    }
}