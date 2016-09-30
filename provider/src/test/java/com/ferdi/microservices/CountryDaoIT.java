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

import com.ferdi.microservices.dao.CountryDao;
import com.ferdi.microservices.entities.Country;

@RunWith(SpringRunner.class)
@SpringBootTest
@Category(IntegrationTest.class)
public class CountryDaoIT {
	
	private static final Country SPAIN = new Country("Spain","ESP");
	@Autowired
	private CountryDao dao;
	
	@Before
	public void setUp(){
		dao.save(SPAIN);
	}
	
	@Test
	public void test(){
		
		Set<Country> countries = dao.findByNameIgnoreCaseContaining("Spain");
		
		assertTrue("At least one country must exist", !countries.isEmpty());
		
		assertEquals("The code must be ESP", "ESP", countries.iterator().next().getCode());
		
	}
	
	@After
	public void tearDown(){
		dao.delete(SPAIN);
	}

}
