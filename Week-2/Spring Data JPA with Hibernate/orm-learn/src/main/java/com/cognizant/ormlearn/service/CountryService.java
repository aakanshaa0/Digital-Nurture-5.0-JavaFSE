package com.cognizant.ormlearn.service;

import com.cognizant.ormlearn.exception.CountryNotFoundException;
import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;

    @Transactional
    public Country findCountryByCode(String code)
            throws CountryNotFoundException {

        Optional<Country> result =
                countryRepository.findById(code);

        if (!result.isPresent()) {
            throw new CountryNotFoundException(
                    "Country not found");
        }

        return result.get();
    }

    @Transactional
    public void addCountry(Country country) {
        countryRepository.save(country);
    }

    @Transactional
    public void updateCountry(String code, String name)
            throws CountryNotFoundException {

        Country country =
                findCountryByCode(code);

        country.setName(name);

        countryRepository.save(country);
    }

    @Transactional
    public void deleteCountry(String code) {
        countryRepository.deleteById(code);
    }

    public List<Country> searchCountry(String text) {
        return countryRepository.findByNameContaining(text);
    }

    public List<Country> findCountriesStartingWith(String text) {
        return countryRepository.findByNameStartingWith(text);
    }

    public List<Country> getAllCountriesSorted() {
        return countryRepository.findAllByOrderByNameAsc();
    }
    public List<Country> getAllCountriesHQL() {
        return countryRepository.getAllCountriesHQL();
    }

    public List<Country> getAllCountriesNative() {
        return countryRepository.getAllCountriesNative();
    }
}