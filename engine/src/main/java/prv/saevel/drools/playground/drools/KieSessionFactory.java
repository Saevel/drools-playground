package prv.saevel.drools.playground.drools;

import lombok.RequiredArgsConstructor;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Component;
import prv.saevel.drools.playground.services.AccountService;
import prv.saevel.drools.playground.services.CountryService;

@Component
@RequiredArgsConstructor
public class KieSessionFactory {

    private final KieContainer kieContainer;
    private final AccountService accountService;
    private final CountryService countryService;

    public KieSession createKieSession() {
        KieSession kieSession = kieContainer.newKieSession("rulesSession");
        kieSession.setGlobal("accountService", accountService);
        kieSession.setGlobal("countryService", countryService);
        return kieSession;
    }
}
