package com.ferdi.microservices.service;

import java.util.Collection;

import com.ferdi.microservices.model.Country;

public interface CountryService {
	
	Country getCountry(String code);
	
	Collection<Country> searchCountries(String search);
	
	Country createCountry(String code, String name);
}
