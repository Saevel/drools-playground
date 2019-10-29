package prv.saevel.drools.playground.persistence.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "accounts")
public class AccountModel {
    @Id
    @GeneratedValue
    private long id;

    @Enumerated(EnumType.STRING)
    @NonNull
    private Currency currency;

    @NonNull
    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    private double value;

    @ManyToOne(targetEntity = UserModel.class)
    @JoinColumn(name = "owner_id", referencedColumnName = "id", nullable = false)
    @NonNull
    private UserModel owner;
}
