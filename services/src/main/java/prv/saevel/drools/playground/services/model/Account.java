package prv.saevel.drools.playground.services.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import prv.saevel.drools.playground.persistence.model.AccountType;
import prv.saevel.drools.playground.persistence.model.Currency;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class Account {

    private long id;

    @NonNull
    private Currency currency;

    @NonNull
    private AccountType accountType;

    private double value;
}
