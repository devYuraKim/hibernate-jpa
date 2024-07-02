package com.in28minutes.jpa.hibernate.section05.repository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.QPageRequest;

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
		logger.info("Count -> {}", repository.count());
	}
	
	@Test
	public void sort() {
		Sort sort = Sort.by(Sort.Direction.DESC, "name");
		logger.info("Sorted Courses -> {}", repository.findAll(sort));
	}
	
	@Test
	public void pagination() {
		PageRequest pageRequest = PageRequest.of(0, 3);
		Page<Course> firstPage = repository.findAll(pageRequest);
		logger.info("First page -> {}", firstPage.getContent());
		
		Pageable secondPageable = firstPage.nextPageable();
		Page<Course> secondPage = repository.findAll(secondPageable);
		logger.info("Second Page -> {}", secondPage.getContent());
	}
	
	@Test
	public void findUsingName() {
		logger.info("FindByName -> {}", repository.findByName("JPA in 50 steps"));
	}
}
