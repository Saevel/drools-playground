package prv.saevel.drools.playground.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import prv.saevel.drools.playground.persistence.model.AccountModel;
import prv.saevel.drools.playground.persistence.model.AccountType;
import prv.saevel.drools.playground.persistence.model.Currency;
import prv.saevel.drools.playground.persistence.model.UserModel;
import prv.saevel.drools.playground.persistence.repositories.AccountsRepository;
import prv.saevel.drools.playground.persistence.repositories.UserRepository;
import prv.saevel.drools.playground.services.converters.AccountConverter;
import prv.saevel.drools.playground.services.converters.Converter;
import prv.saevel.drools.playground.services.model.Account;
import prv.saevel.drools.playground.services.model.User;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountService {

    private final Converter<UserModel, User> userConverter;
    private final UserRepository userRepository;
    private final AccountsRepository accountsRepository;

    public Account createDefaultAccount(User user, AccountType accountType) {
        return createAccount(user, accountType, Currency.PLN);
    }

    public Account createAccount(User user, AccountType accountType, Currency currency) {
        if (userRepository.existsById(user.getId())) {
            final UserModel userModel = userConverter.reverseConvert(user);
            final Converter<AccountModel, Account> accountConverter = new AccountConverter(userModel);
            final AccountModel newAccount = new AccountModel(currency, accountType, userModel);
            return accountConverter.convert(accountsRepository.save(newAccount));
        } else {
            return null;
        }
    }

    public Collection<Account> allAccounts() {
        return accountsRepository.findAll().stream().map(accountModel -> new AccountConverter(accountModel.getOwner()).convert(accountModel)).collect(Collectors.toList());
    }
}
