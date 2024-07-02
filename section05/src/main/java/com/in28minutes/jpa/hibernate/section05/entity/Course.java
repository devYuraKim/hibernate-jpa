package com.in28minutes.jpa.hibernate.section05.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Cacheable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;

@Entity
//@Table(name="CourseDetails")
//@NamedQuery(name="query_get_all_courses", query="Select c From Course c")
@NamedQueries(
	value = {
		@NamedQuery(name="query_get_all_courses", query="Select c From Course c"),
		@NamedQuery(name="query_get_100_step_courses", query="Select c From Course c where name like '%100 steps'")
	}
)
@Cacheable
public class Course {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable=false)
	private String name;
	
	@UpdateTimestamp
	private LocalDateTime lastUpdatedDate;
	
	@CreationTimestamp
	private LocalDateTime createdDate;

	@OneToMany(mappedBy = "course")
	private List<Review> reviews = new ArrayList<>();
	
	@ManyToMany(mappedBy = "courses")
	@JsonIgnore
	private List<Student> students = new ArrayList<>();
	
	//JPA 사용하려면 default constructor가 있어야 함
	protected Course() {
		
	}
	
	public Course(String name) {
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
		return "Course [id=" + id + ", name=" + name + "]";
	}

	//Review 관련
	public List<Review> getReviews() {
		return reviews;
	}

	public void addReview(Review review) {
		this.reviews.add(review);
	}
	
	public void removeReview(Review review) {
		this.reviews.remove(review);
	}

	//Student 관련
	public List<Student> getStudents() {
		return students;
	}

	public void addStudent(Student student) {
		this.students.add(student);
	}
	
	
}
