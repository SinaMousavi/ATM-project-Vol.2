package ir.payeshgaran.project1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "accounts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "sequence")
    @SequenceGenerator(
            name = "sequence",
            sequenceName = "sequence",
            allocationSize = 1
    )
    private Long id;
    @Column(name = "accountNumber")
    private String accountNumber;
    @Column(name = "accountBalance")
    private double accountBalance = 10000;

    @JoinColumn
    @OneToMany(fetch = FetchType.EAGER)
    private List<Transaction> transactions = new ArrayList<>();

    public Account(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Account(String accountNumber, double accountBalance) {
        this.accountNumber = accountNumber;
        this.accountBalance = accountBalance;
    }
}