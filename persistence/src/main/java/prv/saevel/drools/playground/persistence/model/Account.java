package prv.saevel.drools.playground.persistence.model;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue
    private long id;

    @Enumerated
    @NonNull
    private Currency currency;

    private double value;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "owner_id", referencedColumnName = "id", unique = true, nullable = false)
    @NonNull
    private User owner;
}
