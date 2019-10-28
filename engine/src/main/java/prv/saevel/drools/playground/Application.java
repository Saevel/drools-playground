package prv.saevel.drools.playground;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import prv.saevel.drools.playground.services.AccountService;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @Bean(destroyMethod = "dispose")
    public KieContainer kieContainer() {
        return KieServices.Factory.get().getKieClasspathContainer();
    }

    @Bean(destroyMethod = "dispose")
    public KieSession kieSession(@Autowired KieContainer kieContainer, @Autowired AccountService accountService){
        KieSession kieSession = kieContainer.newKieSession("rulesSession");
        kieSession.setGlobal("accountService", accountService);
        return kieSession;
    }
}

