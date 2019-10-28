package prv.saevel.drools.playground.persistence.model;

import lombok.*;

import javax.persistence.*;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "accounts")
public class AccountModel {
    @Id
    @GeneratedValue
    private long id;

    @Enumerated
    @NonNull
    private Currency currency;

    private double value;

    @ManyToOne(targetEntity = UserModel.class)
    @JoinColumn(name = "owner_id", referencedColumnName = "id", unique = true, nullable = false)
    @NonNull
    private UserModel owner;
}
