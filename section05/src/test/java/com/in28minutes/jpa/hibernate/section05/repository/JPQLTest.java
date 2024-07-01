package com.in28minutes.jpa.hibernate.section05.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.in28minutes.jpa.hibernate.section05.Section05Application;
import com.in28minutes.jpa.hibernate.section05.entity.Course;
import com.in28minutes.jpa.hibernate.section05.entity.Student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

@SpringBootTest(classes=Section05Application.class)
class JPQLTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	EntityManager em;
	
	@Test
	public void jpql_basic() {
		//Query query = em.createQuery("Select c From Course c");
		Query query = em.createNamedQuery("query_get_all_courses");
		List resultList = query.getResultList();
		logger.info("Select c from Course c -> {}", resultList);
	}
	
	@Test
	public void jpql_typed() {
		//TypedQuery<Course> query = em.createQuery("Select c From Course c", Course.class);
		TypedQuery<Course> query = em.createNamedQuery("query_get_all_courses", Course.class);
		List<Course> resultList = query.getResultList();
		logger.info("Select c from Course c -> {}", resultList);
	}
	
	@Test
	public void jpql_where() {
		//TypedQuery<Course> query = em.createQuery("Select c From Course c where name like '%100 steps'", Course.class);
		TypedQuery<Course> query = em.createNamedQuery("query_get_100_step_courses", Course.class);
		List<Course> resultList = query.getResultList();
		logger.info("Select c from Course c where name like '%100 steps' -> {}", resultList);
	}
	
	@Test
	public void jpql_courses_without_students() {
		TypedQuery<Course> query = em.createQuery("Select c from Course c where c.students is empty", Course.class);
		List<Course> resultList = query.getResultList();
		logger.info("Filtered Results =====> {}", resultList);
	}
	
	@Test
	public void jpql_courses_with_at_least_2_students() {
		TypedQuery<Course> query = em.createQuery("Select c from Course c where size(c.students) >= 2", Course.class);
		List<Course> resultList = query.getResultList();
		logger.info("Filtered Results =====> {}", resultList);
	}
	
	@Test
	public void jpql_courses_ordered_by_students() {
		TypedQuery<Course> query = em.createQuery("Select c from Course c order by size(c.students) desc", Course.class);
		List<Course> resultList = query.getResultList();
		logger.info("Ordered Results =====> {}", resultList);
	}
	
	@Test
	public void jpql_students_with_passports_in_a_certain_pattern() {
		TypedQuery<Student> query = em.createQuery("Select s from Student s where s.passport.number like '%1234%'", Student.class);
		List<Student> resultList = query.getResultList();
		logger.info("Filtered Results =====> {}", resultList);
	}
	
	//JOIN -> select c, s from Course c JOIN c.students s (테이블이 아닌 객체 중심이므로 Course instance의 students 필드를 찾음)
	//LEFT JOIN -> select c, s from Course c LEFT JOIN c.students s (Course는 다 나오고, student는 null 가능)
	//CROSS JOIN -> select c, s from Course c, Student s
	@Test
	public void join() {
		Query query = em.createQuery("select c, s from Course c JOIN c.students s ");
		List<Object[]> resultList = query.getResultList();
		logger.info("JOIN -> Total of {}", resultList.size());
		
		for(Object[] result : resultList) {
			logger.info("JOIN -> {}, {}", result[0], result[1]);
			//result[0]; -> course
			//result[1]; -> student
			//JPQL이 자동적으로 Object array로 결과를 배열함
		}
	}
	
	//LEFT JOIN
	@Test
	public void leftJoin() {
		Query query = em.createQuery("select c, s from Course c LEFT JOIN c.students s ");
		List<Object[]> resultList = query.getResultList();
		logger.info("LEFT JOIN -> Total of {}", resultList.size());
		
		for(Object[] result : resultList) {
			logger.info("LEFT JOIN -> {}, {}", result[0], result[1]);
		}
	}
	
	//CROSS JOIN
	@Test
	public void crossJoin() {
		Query query = em.createQuery("select c, s from Course c, Student s ");
		List<Object[]> resultList = query.getResultList();
		logger.info("CROSS JOIN -> Total of {}", resultList.size());
		
		for(Object[] result : resultList) {
			logger.info("CROSS JOIN -> {}, {}", result[0], result[1]);
		}
	}
	
}
