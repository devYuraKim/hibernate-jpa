package com.in28minutes.jpa.hibernate.section05.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.in28minutes.jpa.hibernate.section05.Section05Application;
import com.in28minutes.jpa.hibernate.section05.entity.Course;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@SpringBootTest(classes=Section05Application.class)
class JPQLTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	EntityManager em;
	
	@Test
	public void jpql_basic() {
		List resultList = em.createQuery("Select c From Course c").getResultList();
		logger.info("Select c from Course c -> {}", resultList);
	}
	
	@Test
	public void jpql_typed() {
		TypedQuery<Course> query = em.createQuery("Select c From Course c", Course.class);
		List<Course> resultList = query.getResultList();
		logger.info("Select c from Course c -> {}", resultList);
	}
	
	@Test
	public void jpql_where() {
		TypedQuery<Course> query = em.createQuery("Select c From Course c where name like '%100 steps'", Course.class);
		List<Course> resultList = query.getResultList();
		logger.info("Select c from Course c where name like '%100 steps' -> {}", resultList);
	}
}
