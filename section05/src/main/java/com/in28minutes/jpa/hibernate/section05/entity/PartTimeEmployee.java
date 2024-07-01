package com.in28minutes.jpa.hibernate.section05.entity;

import java.math.BigDecimal;

import jakarta.persistence.Entity;

@Entity
public class PartTimeEmployee extends Employee{

	private BigDecimal hourlyWage;
	
	//JPA를 쓰려면 default constructor 있어야 한다
	public PartTimeEmployee(){}

	public PartTimeEmployee(String name, BigDecimal hourlyWage) {
		super(name);
		this.hourlyWage = hourlyWage;
	}
	
}
