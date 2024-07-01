package com.in28minutes.jpa.hibernate.section05.entity;

import java.math.BigDecimal;

import jakarta.persistence.Entity;

@Entity
public class FullTimeEmployee extends Employee{

	private BigDecimal salary;
	
	//JPA를 쓰려면 default constructor 있어야 한다
	public FullTimeEmployee(){}

	public FullTimeEmployee(String name, BigDecimal salary) {
		super(name);
		this.salary = salary;
	}
	
}
