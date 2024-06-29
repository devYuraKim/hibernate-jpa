package com.in28minutes.jpa.hibernate.section05.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToOne;

@Entity
public class Student {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable=false)
	private String name;
	
	@OneToOne
	private Passport passport;
	
	//JPA 사용하려면 default constructor가 있어야 함
	protected Student() {
		
	}
	
	public Student(String name) {
		this.name=name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name=name;
	}
	
	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return String.format("Student[%s]", name);
	}
	
	//Passport 정보
	public Passport getPassport() {
		return passport;
	}

	public void setPassport(Passport passport) {
		this.passport = passport;
	}
}
