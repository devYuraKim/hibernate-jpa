package com.in28minutes.jpa.hibernate.section05.entity;

import jakarta.persistence.Embeddable;

@Embeddable
public class Address {
	
	private String line1;
	private String line2;
	private String city;
	
	public Address() {}
	
	public Address(String line1, String line2, String city) {
		super();
		this.line1 = line1;
		this.line2 = line2;
		this.city = city;
	}

	@Override
	public String toString() {
		return "Address [line1=" + line1 + ", line2=" + line2 + ", city=" + city + "]";
	}
	
}
