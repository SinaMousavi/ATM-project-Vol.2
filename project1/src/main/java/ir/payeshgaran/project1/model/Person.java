package ir.payeshgaran.project1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "person")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Person {
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "sequence")
    @SequenceGenerator(
            name = "sequence",
            sequenceName = "sequence",
            allocationSize = 1
    )
    private Long id;
    @Column(name = "firstname")
    private String firstname;
    @Column(name = "lastname")
    private String lastname;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn
    private List<Account> accounts = new ArrayList<>();

    public Person(String firstname, String lastname, String username, String password, List<Account> accounts) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.accounts = accounts;
    }
}
