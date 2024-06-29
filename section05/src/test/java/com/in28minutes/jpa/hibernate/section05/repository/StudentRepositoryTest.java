package com.in28minutes.jpa.hibernate.section05.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.in28minutes.jpa.hibernate.section05.Section05Application;
import com.in28minutes.jpa.hibernate.section05.entity.Course;
import com.in28minutes.jpa.hibernate.section05.entity.Passport;
import com.in28minutes.jpa.hibernate.section05.entity.Student;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@SpringBootTest(classes=Section05Application.class)
class StudentRepositoryTest {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	StudentRepository repository;
	
	@Autowired
	EntityManager em;
	
	@Test
//	@Transactional
	public void someTest() {
		repository.someOperationToUnderstandPersistenceContext();
	}
	
	@Test
	@Transactional
	//Transactional: wraps the method within a single transaction context
	public void retrieveStudentAndPassport() {
		Student student = em.find(Student.class, 20001l);
		logger.info("student -> {}", student);
		logger.info("passport -> {}", student.getPassport());
	}
	
	
	
}
