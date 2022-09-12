package ir.payeshgaran.project1.service;

import ir.payeshgaran.project1.model.Person;


import java.util.List;

public interface PersonService {

    void addPerson(Person person);

    List<Person> getAllPersons();

    List getByUsername(String username);

    void addAccountToPerson(String username, String accountNumber);

    Double getAllAccountBalance(Person person);
}

