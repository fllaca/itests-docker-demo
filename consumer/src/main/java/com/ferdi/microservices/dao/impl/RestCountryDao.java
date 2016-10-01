package com.ferdi.microservices.dao.impl;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestOperations;

import com.ferdi.microservices.bean.CountryBean;
import com.ferdi.microservices.dao.CountryDao;

@Repository
public class RestCountryDao implements CountryDao{

	@Autowired
	private RestOperations restTemplate;
	
	@Value("${countryapi.url}")
	private String apiBaseUrl;
	
	@Override
	public CountryBean getCountry(String code) {
		HttpHeaders headers = new HttpHeaders();
       
		headers.add("content-type", "application/json");

        HttpEntity payload = new HttpEntity(headers);

        String requestUrl = new StringBuilder(apiBaseUrl)
        		.append("/api/1/country/")
        		.append(code)
        		.toString();
        
        ResponseEntity<CountryBean> response = restTemplate.exchange(requestUrl, HttpMethod.GET, payload, CountryBean.class);
        
        if (response.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
        	return null;
        }
        
        return response.getBody();
	}

	@Override
	public Collection<CountryBean> searchCountries(String search) {
		HttpHeaders headers = new HttpHeaders();
	       
		headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON.toString());

        HttpEntity payload = new HttpEntity(headers);

        String requestUrl = new StringBuilder(apiBaseUrl)
        		.append("/api/1/country/?q=")
        		.append(search)
        		.toString();
        
		ResponseEntity<CountryBean[]> response = restTemplate.exchange(requestUrl, HttpMethod.GET, payload, CountryBean[].class);
        
        if (response.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
        	return Collections.emptyList();
        }
        
        return Arrays.asList(response.getBody());
	}
	
	

}
