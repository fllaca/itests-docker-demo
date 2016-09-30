package com.ferdi.microservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableJpaRepositories
public class CountryApplication {

	public static void main(String[] args) {
		SpringApplication.run(CountryApplication.class, args);
	}
	
	@Bean
	public RestOperations restTemplate() {
	    return new RestTemplate();
	}
}
