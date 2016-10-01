package com.ferdi.microservices.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Empire {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;

	private String emperor;

	protected Empire() {
	}

	public Empire(String name, String emperor) {
		this.name = name;
		this.emperor = emperor;
	}

	@Override
	public String toString() {
		return name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmperor() {
		return emperor;
	}

	public void setEmperor(String emperor) {
		this.emperor = emperor;
	}

	public Long getId() {
		return id;
	}

}
