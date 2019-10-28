package prv.saevel.drools.playground.services.converters;

import org.springframework.stereotype.Component;
import prv.saevel.drools.playground.persistence.model.AccountModel;
import prv.saevel.drools.playground.services.model.Account;

@Component
public class AccountConverter implements Converter<AccountModel, Account> {
    @Override
    public Account convert(AccountModel model) {
        Account account = new Account(model.getCurrency());
        account.setId(model.getId());
        account.setValue(model.getValue());
        return account;
    }

    @Override
    public AccountModel reverseConvert(Account account) {
        AccountModel model = new AccountModel(account.getCurrency(), null);
        model.setId(account.getId());
        model.setValue(account.getValue());
        return model;
    }
}
