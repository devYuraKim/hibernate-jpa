package com.in28minutes.jpa.hibernate.section05.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.in28minutes.jpa.hibernate.section05.Section05Application;
import com.in28minutes.jpa.hibernate.section05.entity.Course;

import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Subgraph;
import jakarta.transaction.Transactional;

@SpringBootTest(classes=Section05Application.class)
class PerformanceTuningTest {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	EntityManager em;
	
//	@Test
//	@Transactional
//	public void creatingNPlusOneProblem() {
//		//여기에서 query 한 번 실행
//		List<Course> courses = em.createNamedQuery("query_get_all_courses", Course.class).getResultList();
//		//for loop 한 번마다 query 실행하면서 getStudents() 정보 확인
//		for(Course course: courses) {
//			logger.info("Course -> {}, Students -> {}", course, course.getStudents());
//		}
//	}
	
	/** N+1 problem 해결하기 **/
	//1.EntityGraph이용
	@Test
	@Transactional
	public void solvingNPlusOneProblem_EntityGraph() {

		EntityGraph<Course> entityGraph = em.createEntityGraph(Course.class);
		entityGraph.addSubgraph("students");
		
		List<Course> courses = em.createNamedQuery("query_get_all_courses", Course.class)
				.setHint("jakarta.persistence.loadgraph", entityGraph)
				.getResultList();
		for(Course course: courses) {
			logger.info("HEY Course -> {}, Students -> {}", course, course.getStudents());
		}
	}
	
	//2.JoinFetch이용
	@Test
	@Transactional
	public void solvingNPlusOneProblem_JoinFetch() {

		EntityGraph<Course> entityGraph = em.createEntityGraph(Course.class);
		entityGraph.addSubgraph("students");
		
		List<Course> courses = em.createNamedQuery("query_get_all_courses", Course.class)
				.setHint("jakarta.persistence.loadgraph", entityGraph)
				.getResultList();
		for(Course course: courses) {
			logger.info("HEY Course -> {}, Students -> {}", course, course.getStudents());
		}
	}
	
}
