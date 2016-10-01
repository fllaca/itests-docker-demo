package com.ferdi.microservices;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.mockito.AdditionalMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestOperations;

import com.ferdi.microservices.bean.CountryBean;
import com.ferdi.microservices.dao.impl.RestCountryDao;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@Category(IntegrationTest.class)
@Ignore
public class RestCountryDaoWithMocksIT {
	
	@Mock RestOperations restOperations;
	
	@InjectMocks
	private RestCountryDao dao;
	
	@Before
	public void setUp() {
		
		ReflectionTestUtils.setField(dao, "apiBaseUrl", "http://testurl");
		
		ResponseEntity<CountryBean> responseOk = mock(ResponseEntity.class);
		when(responseOk.getStatusCode()).thenReturn(HttpStatus.OK);
		when(responseOk.getBody()).thenReturn(new CountryBean("Spain", "ESP"));
		
		ResponseEntity<CountryBean> responseNotFound = mock(ResponseEntity.class);
		when(responseNotFound.getStatusCode()).thenReturn(HttpStatus.NOT_FOUND);
		
		when(restOperations.exchange(
				eq("http://testurl/api/1/country/ESP"), 
				eq(HttpMethod.GET), 
				any(), 
				eq(CountryBean.class))).thenReturn(responseOk);
		when(restOperations.exchange(
				AdditionalMatchers.not(eq("http://testurl/api/1/country/ESP")), 
				eq(HttpMethod.GET), 
				any(), 
				eq(CountryBean.class))).thenReturn(responseNotFound);
	}
	
	@Test
	public void testGet(){
		
		CountryBean Country = dao.getCountry("ESP");
		
		assertEquals("Country name is not as expected", "Spain", Country.getName());
		
	}
	
	@Test
	public void testNotFound(){
		
		CountryBean Country = dao.getCountry("thiswontbefound");
		
		assertNull("Result must be null", Country);
		
	}

}
