package com.ferdi.microservices.dao;

import java.util.Collection;

import com.ferdi.microservices.bean.CountryBean;

public interface CountryDao {
	
	public CountryBean getCountry(String code);
	
	public Collection<CountryBean> searchCountries(String search);

}
