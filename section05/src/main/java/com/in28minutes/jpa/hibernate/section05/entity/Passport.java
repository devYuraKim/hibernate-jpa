package com.in28minutes.jpa.hibernate.section05.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToOne;

@Entity
public class Passport {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable=false)
	private String number;
	
	@OneToOne(fetch =FetchType.LAZY, mappedBy = "passport")
	private Student student;
	
	//JPA 사용하려면 default constructor가 있어야 함
	protected Passport() {
		
	}
	
	public Passport(String number) {
		this.number=number;
	}
	
	public String getNumber() {
		return number;
	}
	
	public void setNumber(String number) {
		this.number=number;
	}
	
	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return String.format("Passport[%s]", number);
	}
	
	//Student 관련 정보
	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
}
