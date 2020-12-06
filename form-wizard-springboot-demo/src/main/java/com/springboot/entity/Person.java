package com.springboot.entity;

import java.io.Serializable;

public class Person implements Serializable {
	private String name;
	private String email;
	private boolean registrationComplete;

	public Person() {
		super();
	}

	public Person(String name, String email, boolean registrationComplete) {
		super();
		this.name = name;
		this.email = email;
		this.registrationComplete = registrationComplete;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isRegistrationComplete() {
		return registrationComplete;
	}

	public void setRegistrationComplete(boolean registrationComplete) {
		this.registrationComplete = registrationComplete;
	}

}
