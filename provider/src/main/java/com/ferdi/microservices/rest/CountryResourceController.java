package com.ferdi.microservices.rest;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ferdi.microservices.bean.ErrorBean;
import com.ferdi.microservices.dao.CountryDao;
import com.ferdi.microservices.entities.Country;

@RestController
@RequestMapping("/api/${api.version}/country")
public class CountryResourceController {

	private static final int DEFAULT_NUM_RESULTS_IN_PAGE = 20;
	@Autowired
	private CountryDao countryDao;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Set<Country>> getCountries(@RequestParam(value = "count", required = false) Integer count,
			@RequestParam(value = "q", required = false) String query) {

		int numResults = count != null ? count : DEFAULT_NUM_RESULTS_IN_PAGE;

		Set<Country> findByName = countryDao.findByNameIgnoreCaseContaining(query)
				.stream().limit(numResults).collect(Collectors.toSet());

		return ResponseEntity.ok().body(findByName);
	}

	@RequestMapping(value = "/{code}", method = RequestMethod.GET)
	public ResponseEntity getCountry(@PathVariable String code) {
		Country country = countryDao.findOne(code);
		if (country != null) {
			return ResponseEntity.ok().body(country);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ErrorBean("Country Not Found", HttpStatus.NOT_FOUND.toString()));
		}

	}

}
