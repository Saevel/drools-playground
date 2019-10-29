package prv.saevel.drools.playground.drools;

import lombok.RequiredArgsConstructor;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Component;
import prv.saevel.drools.playground.persistence.model.AccountType;
import prv.saevel.drools.playground.services.AccountService;

@Component
@RequiredArgsConstructor
public class KieSessionFactory {

    private final KieContainer kieContainer;
    private final AccountService accountService;

    public KieSession createKieSession() {
        KieSession kieSession = kieContainer.newKieSession("rulesSession");
        kieSession.setGlobal("accountService", accountService);
        kieSession.setGlobal("defaultAccountType", AccountType.BASIC);
        return kieSession;
    }
}
