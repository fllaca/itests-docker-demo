package com.ferdi.microservices.bean;

public class ErrorBean {
	
	private String  message;
	
	private String code;

	public ErrorBean(String message, String code) {
		super();
		this.message = message;
		this.code = code;
	}

	public ErrorBean() {
		super();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	

}
