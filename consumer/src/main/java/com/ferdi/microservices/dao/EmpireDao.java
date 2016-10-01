package com.ferdi.microservices.dao;

import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.ferdi.microservices.entities.Empire;

public interface EmpireDao extends CrudRepository<Empire, Long> {

    Set<Empire> findByNameIgnoreCaseContaining(String name);
    
    Page<Empire> findAll(Pageable pageable);

}
