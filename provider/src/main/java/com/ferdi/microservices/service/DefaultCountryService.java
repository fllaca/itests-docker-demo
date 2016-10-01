package com.ferdi.microservices.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ferdi.microservices.model.Country;
import com.ferdi.microservices.repository.CountryRepository;

@Service
public class DefaultCountryService implements CountryService{

	private CountryRepository countryRepository;
	
	@Autowired
	public DefaultCountryService(CountryRepository countryRepository) {
		super();
		this.countryRepository = countryRepository;
	}

	@Override
	public Country getCountry(String code) {
		return countryRepository.findByCode(code);
	}

	@Override
	public Collection<Country> searchCountries(String search) {
		return countryRepository.findByNameIgnoreCaseContaining(search);
	}

	@Override
	public Country createCountry(String code, String name) {
		Country newCountry = new Country(name, code);
		
		return countryRepository.save(newCountry);
	}

}
