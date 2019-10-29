package prv.saevel.drools.playground.services.converters;

import lombok.RequiredArgsConstructor;
import prv.saevel.drools.playground.persistence.model.AccountModel;
import prv.saevel.drools.playground.persistence.model.UserModel;
import prv.saevel.drools.playground.services.model.Account;

@RequiredArgsConstructor
public class AccountConverter implements Converter<AccountModel, Account> {

    private final UserModel userModel;

    @Override
    public Account convert(AccountModel model) {
        Account account = new Account(model.getCurrency(), model.getAccountType());
        account.setId(model.getId());
        account.setValue(model.getValue());
        return account;
    }

    @Override
    public AccountModel reverseConvert(Account account) {
        AccountModel model = new AccountModel(account.getCurrency(), account.getAccountType(), userModel);
        model.setId(account.getId());
        model.setValue(account.getValue());
        return model;
    }
}
