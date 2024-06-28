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

@Entity
public class Review {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String rating;
	
	private String description;
	
	//JPA 사용하려면 default constructor가 있어야 함
	protected Review() {
		
	}
	
	public Review(String rating, String description) {
		this.rating = rating;
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description=description;
	}
	
	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return String.format("Review[%s %s]", rating, description);
	}
	
}
