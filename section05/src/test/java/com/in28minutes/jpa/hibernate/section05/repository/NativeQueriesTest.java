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
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@SpringBootTest(classes=Section05Application.class)
class NativeQueriesTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	EntityManager em;
	
	@Test
	public void native_queries_basic() {
		Query query = em.createNativeQuery("SELECT * FROM course", Course.class);
		List<Course> resultList = query.getResultList();
		logger.info("SELECT * FROM course -> {}", resultList);
	}
	
	@Test
	public void native_queries_with_parameter() {
		Query query = em.createNativeQuery("SELECT * FROM course WHERE id=?", Course.class);
		query.setParameter(1, 10001L);
		List<Course> resultList = query.getResultList();
		logger.info("SELECT * FROM course WHERE id=?-> {}", resultList);
	}
	
	@Test
	public void native_queries_with_named_parameter() {
		Query query = em.createNativeQuery("SELECT * FROM course WHERE id= :id", Course.class);
		query.setParameter("id", 10001l);
		List<Course> resultList = query.getResultList();
		logger.info("SELECT * FROM course -> {}", resultList);
	}
	
	//JPA로 모든 행을 updqte하려면 하나씩 row를 get 해서 update 해야 한다 = mass update 불가
	@Test
	@Transactional
	public void native_queries_to_update() {
		Query query = em.createNativeQuery("UPDATE course SET last_updated_date=current_timestamp", Course.class);
		int noOfRowsUpdated = query.executeUpdate();
		logger.info("noOfRowsUpdated -> {}", noOfRowsUpdated);
	}
	
	
	
}
