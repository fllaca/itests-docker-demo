package com.ferdi.microservices;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ferdi.microservices.model.Country;
import com.ferdi.microservices.service.CountryService;

@RunWith(SpringRunner.class)
@SpringBootTest
@Category(IntegrationTest.class)
public class CountryServiceIT {
	
	private static final Country SPAIN = new Country("Spain","ESP");
	@Autowired
	private CountryService service;
	
	@Before
	public void setUp(){
		//dao.save(SPAIN);
	}
	
	@Test
	public void test(){
		
		Country country = service.getCountry("ESP");
		
		assertTrue("The country must exists", country != null);
		
		assertEquals("The code must be ESP", "ESP", country.getCode());
		
	}
	
	@After
	public void tearDown(){
		//dao.delete(SPAIN);
	}

}
