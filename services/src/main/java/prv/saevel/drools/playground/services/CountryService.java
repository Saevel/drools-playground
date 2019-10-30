package prv.saevel.drools.playground.services;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class CountryService {

    private final Collection<String> allCountries = Collections.unmodifiableList(Arrays.stream(Locale.getISOCountries())
            .map(iso -> new Locale("", iso).getDisplayCountry(Locale.ENGLISH)).collect(Collectors.toList()));
    private final Collection<String> euroAreaMemberCountries = Collections.unmodifiableList(Arrays.asList("Austria", "Belgium", "Cyprus", "Estonia", "Finland", "France", "Germany", "Greece", "Ireland", "Italy", "Latvia", "Lithuania", "Luxembourg", "Malta", "Netherlands", "Portugal", "Slovakia", "Slovenia", "Spain"));
    private final Collection<String> nonEuroAreaMemberCountries = Collections.unmodifiableList(Arrays.asList("Bulgaria", "Croatia", "Czech Republic", "Hungary", "Poland", "Romania", "Sweden"));

    private final Collection<String> euCountries;

    {
        final List<String> euCountriesList = new ArrayList<>();
        euCountriesList.addAll(euroAreaMemberCountries);
        euCountriesList.addAll(nonEuroAreaMemberCountries);
        euCountriesList.sort(String::compareTo);
        this.euCountries = Collections.unmodifiableList(euCountriesList);
    }

    public Collection<String> allCountries() {
        return allCountries;
    }

    public Collection<String> getEuroAreaMemberCountries() {
        return euroAreaMemberCountries;
    }

    public Collection<String> getNonEuroAreaMemberCountries() {
        return nonEuroAreaMemberCountries;
    }

    public Collection<String> getEUCountries() {
        return euCountries;
    }

    public boolean isValidCountry(String country) {
        return allCountries.contains(country);
    }

    public boolean isEUCountry(String country) {
        return euCountries.contains(country);
    }

}
