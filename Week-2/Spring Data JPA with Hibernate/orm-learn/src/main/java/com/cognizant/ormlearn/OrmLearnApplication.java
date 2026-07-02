package com.cognizant.ormlearn;

import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OrmLearnApplication implements CommandLineRunner {

    @Autowired
    private CountryService countryService;

    public static void main(String[] args) {
        SpringApplication.run(OrmLearnApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Finding country by code:");
        Country country = countryService.findCountryByCode("IN");
        System.out.println(country.getCode());
        System.out.println(country.getName());

        System.out.println("\nSearch country containing 'Ind'");
        System.out.println(countryService.searchCountry("Ind"));

        System.out.println("\nCountries starting with 'I':");
        System.out.println(countryService.findCountriesStartingWith("I"));

        System.out.println("\nCountries sorted by name:");
        System.out.println(countryService.getAllCountriesSorted());

        System.out.println("HQL Query Result:");
        System.out.println(countryService.getAllCountriesHQL());

        System.out.println();

        System.out.println("Native Query Result:");
        System.out.println(countryService.getAllCountriesNative());
    }
}