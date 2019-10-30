package prv.saevel.drools.playground.service;

import org.assertj.core.api.Condition;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import prv.saevel.drools.playground.persistence.model.AccountModel;
import prv.saevel.drools.playground.persistence.model.AccountType;
import prv.saevel.drools.playground.persistence.model.Currency;
import prv.saevel.drools.playground.persistence.model.UserModel;
import prv.saevel.drools.playground.persistence.repositories.AccountsRepository;
import prv.saevel.drools.playground.persistence.repositories.UserRepository;
import prv.saevel.drools.playground.services.model.Account;
import prv.saevel.drools.playground.services.model.User;

import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InitialAccountServiceTest {

    private final AtomicLong counter = new AtomicLong();

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private AccountsRepository accountsRepository;

    @Autowired
    private InitialAccountService initialAccountService;

    @Before
    public void setUp() {
        when(userRepository.existsById(Mockito.anyLong())).thenReturn(true);

        when(userRepository.save(any(UserModel.class))).thenAnswer((Answer<UserModel>) invocation -> {
            final UserModel userModel = invocation.getArgument(0);
            userModel.setId(counter.incrementAndGet());
            return userModel;
        });

        when(accountsRepository.save(any(AccountModel.class))).thenAnswer((Answer<AccountModel>) invocation -> {
            final AccountModel accountModel = invocation.getArgument(0);
            accountModel.setId(counter.incrementAndGet());
            return accountModel;
        });
    }

    private User user(String country, int age) {
        String username = "user_" + UUID.randomUUID().toString();
        String password = UUID.randomUUID().toString();
        final User user = new User(username, password, age, country);
        user.setId(1L);
        return user;
    }

    @Test
    public void createInitialAccountsForUserFromEUNotPolandAgeUnderOrEqual26() {
        User user = user("Lithuania", 26);

        final Collection<Account> accounts = initialAccountService.createInitialAccountsForUser(user);
        assertThat(accounts).hasSize(1).have(accountWith(Currency.EUR, AccountType.YOUTH));
    }

    @Test
    public void createInitialAccountsForUserFromEUNotPolandAgeOver26() {
        User user = user("Lithuania", 27);

        final Collection<Account> accounts = initialAccountService.createInitialAccountsForUser(user);
        assertThat(accounts).hasSize(1).have(accountWith(Currency.EUR, AccountType.BASIC));
    }

    @Test
    public void createInitialAccountsForUserFromPolandAgeUnderOrEqual26() {
        User user = user("Poland", 26);

        final Collection<Account> accounts = initialAccountService.createInitialAccountsForUser(user);
        assertThat(accounts).hasSize(1).have(accountWith(Currency.PLN, AccountType.YOUTH));
    }

    @Test
    public void createInitialAccountsForUserFromPolandAgeOver26() {
        User user = user("Poland", 27);

        final Collection<Account> accounts = initialAccountService.createInitialAccountsForUser(user);
        assertThat(accounts).hasSize(4)
                .haveExactly(1, accountWith(Currency.PLN, AccountType.BASIC))
                .haveExactly(1, accountWith(Currency.EUR, AccountType.BASIC))
                .haveExactly(1, accountWith(Currency.USD, AccountType.BASIC))
                .haveExactly(1, accountWith(Currency.SGD, AccountType.BASIC));
    }

    @Test
    public void createInitialAccountsForUserFromZimbabwe() {
        User user = user("Zimbabwe", 27);

        final Collection<Account> accounts = initialAccountService.createInitialAccountsForUser(user);
        assertThat(accounts).hasSize(1).have(accountWith(Currency.USD, AccountType.BASIC));
    }

    @Test
    public void createInitialAccountsForUserFromSingapore() {
        User user = user("Singapore", 27);

        final Collection<Account> accounts = initialAccountService.createInitialAccountsForUser(user);
        assertThat(accounts).hasSize(1).have(accountWith(Currency.SGD, AccountType.BASIC));
    }

    private Condition<Account> accountWith(Currency expectedCurrency, AccountType expectedAccountType) {
        return new Condition<Account>() {
            @Override
            public boolean matches(Account value) {
                return expectedCurrency == value.getCurrency() && expectedAccountType == value.getAccountType();
            }
        };
    }
}