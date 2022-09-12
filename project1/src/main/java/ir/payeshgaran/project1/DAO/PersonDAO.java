package ir.payeshgaran.project1.DAO;


import ir.payeshgaran.project1.model.Person;

import java.util.List;

public interface PersonDAO {

    void addPerson(Person person);

    List<Person> getAllPersons();

    List<?> getByUsername(String username);
}

