package prv.saevel.drools.playground.services.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.LinkedList;
import java.util.List;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class User {

    private long id;

    @NonNull
    private String username;

    @NonNull
    private String password;

    @NonNull
    private Integer age;

    @NonNull
    private String country;

    private List<Account> accounts = new LinkedList<>();

    public void addAccount(Account account) {
        accounts.add(account);
    }
}
