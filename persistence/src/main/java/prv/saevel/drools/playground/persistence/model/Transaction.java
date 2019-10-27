package prv.saevel.drools.playground.persistence.model;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;
import java.util.Optional;

@Data
@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue
    private long id;

    @NonNull
    private Currency currency;

    private double value;

    @NonNull
    @Enumerated
    private TransactionType type;

    @ManyToOne(targetEntity = Account.class)
    @JoinColumn(name = "source_account_id")
    @NonNull
    private Optional<Account> sourceAccount;

    @ManyToOne(targetEntity = Account.class)
    @JoinColumn(name = "target_account_id")
    @NonNull
    private Optional<Account> targetAccount;
}
