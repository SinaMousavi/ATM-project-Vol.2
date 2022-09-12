package ir.payeshgaran.project1;

import ir.payeshgaran.project1.model.Account;
import ir.payeshgaran.project1.model.Person;
import ir.payeshgaran.project1.service.implementation.AccountServiceImplementation;
import ir.payeshgaran.project1.service.implementation.PersonServiceImplementation;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class Project1Application {

    public static void main(String[] args) {
        SpringApplication.run(Project1Application.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner commandLineRunner(PersonServiceImplementation personService, AccountServiceImplementation accountService) {
        return args -> {


            Person sina = new Person("sina", "mousavi", "sina", "1234", new ArrayList<>());
            Person javad = new Person("javad", "nasrol", "javad", "1234", new ArrayList<>());
            personService.addPerson(sina);
            personService.addPerson(javad);

            accountService.addAccount(new Account("s1", 10000));
            accountService.addAccount(new Account("s2"));
            accountService.addAccount(new Account("s3"));

            accountService.addAccount(new Account("j1", 20000));
            accountService.addAccount(new Account("j2"));

            personService.addAccountToPerson("sina", "s1");
            personService.addAccountToPerson("sina", "s2");
            personService.addAccountToPerson("sina", "s3");


            personService.addAccountToPerson("javad", "j1");
            personService.addAccountToPerson("javad", "j2");


        };
    }

}
