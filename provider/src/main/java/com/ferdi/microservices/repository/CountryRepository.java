package com.ferdi.microservices.repository;

import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.ferdi.microservices.model.Country;

public interface CountryRepository  extends CrudRepository<Country, String> {

    Set<Country> findByNameIgnoreCaseContaining(String name);
    
    Set<Country> findByCode(String code);
    
    Page<Country> findAll(Pageable pageable);
}
