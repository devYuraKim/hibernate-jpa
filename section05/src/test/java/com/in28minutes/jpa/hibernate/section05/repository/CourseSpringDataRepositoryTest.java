package com.in28minutes.jpa.hibernate.section05.repository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.in28minutes.jpa.hibernate.section05.Section05Application;
import com.in28minutes.jpa.hibernate.section05.entity.Course;

import jakarta.persistence.EntityManager;

@SpringBootTest(classes=Section05Application.class)
class CourseSpringDataRepositoryTest {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	CourseSpringDataRepository repository;
	
	@Autowired
	EntityManager em;
	
	@Test
	public void findById_coursePresent() {
		Optional<Course> courseOptional = repository.findById(10001l);
		assertTrue(courseOptional.isPresent());
	}
	
	@Test
	public void findById_courseNotPresent() {
		Optional<Course> courseOptional = repository.findById(20001l);
		assertFalse(courseOptional.isPresent());
	}
	
	@Test
	public void playingAroundWithJpaRepository() {
		Course course = new Course("Microservices in 100 steps");
		repository.save(course);
		course.setName("Microservices in 100 steps - updated");
		repository.save(course);
		logger.info("Courses -> {}", repository.findAll());
	}
}
