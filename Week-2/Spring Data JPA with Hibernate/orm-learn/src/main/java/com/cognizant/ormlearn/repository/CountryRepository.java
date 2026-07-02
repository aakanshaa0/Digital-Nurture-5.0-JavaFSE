package com.cognizant.ormlearn.repository;

import com.cognizant.ormlearn.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<Country, String> {

    List<Country> findByNameContaining(String text);

    List<Country> findByNameStartingWith(String text);

    List<Country> findAllByOrderByNameAsc();

    @Query("FROM Country")
    List<Country> getAllCountriesHQL();

    @Query(value = "SELECT * FROM country", nativeQuery = true)
    List<Country> getAllCountriesNative();
}