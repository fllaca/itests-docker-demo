package com.ferdi.microservices.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ferdi.microservices.bean.ErrorBean;
import com.ferdi.microservices.dao.EmpireDao;
import com.ferdi.microservices.entities.Empire;

@RestController
@RequestMapping("/empire")
public class EmpireResourceController {
	
	private EmpireDao empireDao;

	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity getCharacter(@PathVariable("id") Long id){
		
		Empire empire = empireDao.findOne(id);
		if (empire != null) {
			return ResponseEntity.ok().body(empire);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ErrorBean("Empire Not Found", HttpStatus.NOT_FOUND.toString()));
		}
	}
	

}
