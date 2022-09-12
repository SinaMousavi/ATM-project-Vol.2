package ir.payeshgaran.project1.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "transaction")
@Data
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "sequence")
    @SequenceGenerator(
            name = "sequence",
            sequenceName = "sequence",
            allocationSize = 1
    )
    private Long id;
    @Column(name = "amount")
    private double amount;
    @Column(name = "depositorId")
    private Long depositorId;
    @Column(name = "receiverId")
    private Long receiverId;
    @Column(name = "type")
    private String type;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "createdAt")
    private Date createdAt;


    public Transaction(double amount, Long depositorId, Long receiverId, String type) {
        this.amount = amount;
        this.depositorId = depositorId;
        this.receiverId = receiverId;
        this.type = type;
    }
}
