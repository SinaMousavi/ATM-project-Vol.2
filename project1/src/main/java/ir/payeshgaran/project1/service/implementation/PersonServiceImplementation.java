package ir.payeshgaran.project1.service.implementation;


import ir.payeshgaran.project1.DAO.impls.AccountImpl;
import ir.payeshgaran.project1.DAO.impls.PersonImpl;
import ir.payeshgaran.project1.model.Account;
import ir.payeshgaran.project1.model.Person;
import ir.payeshgaran.project1.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Service
@Transactional
public class PersonServiceImplementation implements PersonService, UserDetailsService {

    @Autowired
    PersonImpl personDAO;

    @Autowired
    AccountImpl accountDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List persons = personDAO.getByUsername(username);
        if (persons == null)
            throw new UsernameNotFoundException("user not found");

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("USER"));


        Person person = (Person) persons.get(0);


        return new User(person.getUsername(), person.getPassword(), authorities);
    }

    @Override
    public void addPerson(Person person) {
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        personDAO.addPerson(person);
    }

    @Override
    public List<Person> getAllPersons() {
        return personDAO.getAllPersons();
    }

    @Override
    public List getByUsername(String username) {
        return personDAO.getByUsername(username);
    }

    @Override
    public void addAccountToPerson(String username, String accountNumber) {
        List<?> accounts = accountDAO.findByAccountNumber(accountNumber);
        Account account = (Account) accounts.get(0);

        Person person = (Person) getByUsername(username).get(0);
        person.getAccounts().add(account);

    }

    @Override
    public Double getAllAccountBalance(Person person) {
        double sum = 0;
        for (Account account : person.getAccounts()) {
            sum += account.getAccountBalance();
        }

        return sum;
    }


}
