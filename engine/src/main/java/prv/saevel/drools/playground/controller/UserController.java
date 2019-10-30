package prv.saevel.drools.playground.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import prv.saevel.drools.playground.service.InitialAccountService;
import prv.saevel.drools.playground.services.CountryService;
import prv.saevel.drools.playground.services.UserService;
import prv.saevel.drools.playground.services.model.User;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final CountryService countryService;
    private final InitialAccountService initialAccountService;

    @PostMapping
    public Mono<User> createUser(@RequestBody User user) {
        // TODO handle validation by @Valid
        if (!countryService.isValidCountry(user.getCountry())) {
            throw new IllegalArgumentException("Not valid country. See valid countries at: GET /countries");
        }
        User savedUser = userService.saveUser(user);
        initialAccountService.createInitialAccountsForUser(savedUser);

        Optional<User> retrievedUser = userService.findById(savedUser.getId());
        return retrievedUser.map(Mono::just).orElse(Mono.empty());
    }

    @GetMapping
    public Mono<Collection<User>> allUsers() {
        return Mono.just(userService.allUsers());
    }
}
