package prv.saevel.drools.playground.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import prv.saevel.drools.playground.services.AccountService;
import prv.saevel.drools.playground.services.model.Account;
import reactor.core.publisher.Mono;

import java.util.Collection;

@RequestMapping("/accounts")
@RestController
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping
    public Mono<Collection<Account>> allAccounts() {
        return Mono.just(accountService.allAccounts());
    }
}
