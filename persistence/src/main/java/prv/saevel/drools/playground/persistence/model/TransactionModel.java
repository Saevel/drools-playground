package prv.saevel.drools.playground.persistence.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Optional;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "transactions")
public class TransactionModel {
    @Id
    @GeneratedValue
    private long id;

    @NonNull
    private Currency currency;

    private double value;

    @NonNull
    @Enumerated
    private TransactionType type;

    @ManyToOne(targetEntity = AccountModel.class)
    @JoinColumn(name = "source_account_id")
    @NonNull
    private Optional<AccountModel> sourceAccount;

    @ManyToOne(targetEntity = AccountModel.class)
    @JoinColumn(name = "target_account_id")
    @NonNull
    private Optional<AccountModel> targetAccount;
}
