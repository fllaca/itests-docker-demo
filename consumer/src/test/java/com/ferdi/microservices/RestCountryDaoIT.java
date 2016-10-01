package com.ferdi.microservices;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ferdi.microservices.bean.CountryBean;
import com.ferdi.microservices.dao.impl.RestCountryDao;

@RunWith(SpringRunner.class)
@SpringBootTest
@Category(IntegrationTest.class)
public class RestCountryDaoIT {
	
	@Autowired
	private RestCountryDao dao;
	
	@Test
	public void testGet(){
		
		CountryBean country = dao.getCountry("ESP");
		
		assertEquals("Country name is not as expected", "Spain", country.getName());
		
	}
	
	@Test
	public void testNotFound(){
		
		CountryBean character = dao.getCountry("thiswontbefound");
		
		assertNull("Result must be null", character);
		
	}

}
