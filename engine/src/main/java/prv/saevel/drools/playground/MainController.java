package prv.saevel.drools.playground;

import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import prv.saevel.drools.playground.persistence.model.User;
import prv.saevel.drools.playground.services.AccountService;
import prv.saevel.drools.playground.services.UserService;
import reactor.core.publisher.Mono;

import java.util.Optional;

@RestController
public class MainController {

    @Autowired
    private KieSession kieSession;

    @Autowired
    private UserService userService;

    @Autowired
    private AccountService accountService;

    @GetMapping("hello")
    public Mono<String> hello(){
        return Mono.just("Hello, World!");
    }

    @PostMapping("users")
    public Mono<User> createUser(@RequestBody User user){
        User savedUser = userService.saveUser(user);
        kieSession.insert(savedUser);
        kieSession.fireAllRules();
        Optional<User> retrievedUser = userService.findById(savedUser.getId());
        return retrievedUser.map(Mono::just).orElse(Mono.empty());
    }
}
