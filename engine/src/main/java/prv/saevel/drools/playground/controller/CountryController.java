package prv.saevel.drools.playground.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import prv.saevel.drools.playground.services.CountryService;
import reactor.core.publisher.Mono;

import java.util.Collection;

@RestController
@RequestMapping("/countries")
@RequiredArgsConstructor
public class CountryController {

    private final CountryService countryService;

    @GetMapping
    public Mono<Collection<String>> allCountries() {
        return Mono.just(countryService.allCountries());
    }

    @GetMapping("/EU")
    public Mono<Collection<String>> euCountries() {
        return Mono.just(countryService.getEUCountries());
    }

    @GetMapping("/EU/euro")
    public Mono<Collection<String>> euroAreaMemberCountries() {
        return Mono.just(countryService.getEuroAreaMemberCountries());
    }

    @GetMapping("/EU/non-euro")
    public Mono<Collection<String>> nonEuroAreaMemberCountries() {
        return Mono.just(countryService.getNonEuroAreaMemberCountries());
    }
}
