package com.ferdi.microservices.bean;

public class CountryBean {
	
	private String name;
	
	private String code;
	
	public CountryBean(String name, String code) {
		super();
		this.name = name;
		this.code = code;
	}

	public CountryBean() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	

}
