package ir.payeshgaran.project1.controller;

import ir.payeshgaran.project1.model.Account;
import ir.payeshgaran.project1.model.Person;
import ir.payeshgaran.project1.service.implementation.AccountServiceImplementation;
import ir.payeshgaran.project1.service.implementation.PersonServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class LoginController {

    @Autowired
    PersonServiceImplementation personService;

    @Autowired
    AccountServiceImplementation accountService;

    @GetMapping("/all")
    public ResponseEntity<List<Person>> getAll() {
        return ResponseEntity.ok().body(personService.getAllPersons());
    }

    @GetMapping("/accounts")
    public ResponseEntity<List<Account>> getAllAcounts() {
        return ResponseEntity.ok().body(accountService.allAccounts());
    }


    @PostMapping("/save")
    public ResponseEntity<String> savePerson(@RequestBody Person person) {
        personService.addPerson(person);
        return ResponseEntity.ok("DONE!");
    }

    @GetMapping("/app/find/{username}")
    public ResponseEntity<?> find(@PathVariable("username") String username) {
        return ResponseEntity.ok().body(personService.getByUsername(username));
    }
}