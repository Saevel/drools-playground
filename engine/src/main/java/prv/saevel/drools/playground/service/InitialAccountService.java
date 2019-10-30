package prv.saevel.drools.playground.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;
import prv.saevel.drools.playground.drools.KieSessionFactory;
import prv.saevel.drools.playground.services.UserService;
import prv.saevel.drools.playground.services.model.Account;
import prv.saevel.drools.playground.services.model.User;

import java.util.Collection;

@Slf4j
@Service
@RequiredArgsConstructor
public class InitialAccountService {

    private final KieSessionFactory kieSessionFactory;
    private final UserService userService;

    public Collection<Account> createInitialAccountsForUser(User savedUser) {
        final KieSession kieSession = kieSessionFactory.createKieSession();
        kieSession.insert(savedUser);
        final int firedRules = kieSession.fireAllRules();
        userService.saveUser(savedUser);
        log.info("Fired {} rules for: {}", firedRules, savedUser);

        kieSession.dispose();
        return savedUser.getAccounts();
    }
}
