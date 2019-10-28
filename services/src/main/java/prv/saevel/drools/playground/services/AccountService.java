package prv.saevel.drools.playground.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import prv.saevel.drools.playground.persistence.model.AccountModel;
import prv.saevel.drools.playground.persistence.model.Currency;
import prv.saevel.drools.playground.persistence.model.UserModel;
import prv.saevel.drools.playground.persistence.repositories.AccountsRepository;
import prv.saevel.drools.playground.persistence.repositories.UserRepository;
import prv.saevel.drools.playground.services.converters.Converter;
import prv.saevel.drools.playground.services.model.Account;
import prv.saevel.drools.playground.services.model.User;

import javax.transaction.Transactional;

@Service
@Transactional
public class AccountService {

    @Autowired
    private Converter<AccountModel, Account> accountConverter;

    @Autowired
    private Converter<UserModel, User> userConverter;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountsRepository accountsRepository;

    public Account createDefaultAccount(User user){
        if(userRepository.existsById(user.getId())){
            AccountModel newAccount = new AccountModel(Currency.PLN, userConverter.reverseConvert(user));
            return accountConverter.convert(accountsRepository.save(newAccount));
        } else {
            return null;
        }
    }
}
