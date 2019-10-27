package prv.saevel.drools.playground.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import prv.saevel.drools.playground.persistence.model.Account;
import prv.saevel.drools.playground.persistence.model.Currency;
import prv.saevel.drools.playground.persistence.model.User;
import prv.saevel.drools.playground.persistence.repositories.AccountsRepository;
import prv.saevel.drools.playground.persistence.repositories.UserRepository;

@Service
public class AccountService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountsRepository accountsRepository;

    private Account createDefaultAccount(User user){
        if(userRepository.existsById(user.getId())){
            Account newAccount = new Account(Currency.PLN, user);
            return accountsRepository.save(newAccount);
        } else {
            return null;
        }
    }
}
