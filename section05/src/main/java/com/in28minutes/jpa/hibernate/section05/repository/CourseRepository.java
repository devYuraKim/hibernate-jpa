package com.in28minutes.jpa.hibernate.section05.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.in28minutes.jpa.hibernate.section05.entity.Course;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Repository
@Transactional //delete의 경우, Transactional이 없이는 제대로 구동되지 않음
public class CourseRepository {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	EntityManager em;
	
	//findById(Long id)
	public Course findById(Long id) {
		return em.find(Course.class, id);
	}
	
	//save or update
	//save(Course course)
	public Course save(Course course) {
		if(course.getId()==null) {
			em.persist(course);
		}else {
			em.merge(course);
		}
		return course;
	}
	
	//deleteById(Long id)
	public void deleteById(Long id) {
		Course course = findById(id);
		em.remove(course);
	}
	
	public void playWithEntityManager() {
		//logger.info("===playWithEntityManager - Start");
		Course course1 = new Course("Web Services in 100 steps");
		em.persist(course1);
		course1.setName("Web Services in 100 steps - Updated");
		
		Course course2 = new Course("Angular JS in 100 steps");
		em.persist(course2);
		//detach(객체)하기 전에 flush()해서 db에 변경 사항을 반영해줘야 한다
		//detach(객체)하면 더이상 EntityManager가 객체를 관리하지 않는다
		em.flush();
		em.detach(course2);
		course2.setName("Angular JS in 100 steps - Updated");
		
		//detach(특정객체) 혹은 clear()로 전체객체
	}
	
	public void playWithEntityManager2() {
		Course course1 = new Course("Web Services in 100 steps");
		em.persist(course1);
		Course course2 = new Course("Angular JS in 100 steps");
		em.persist(course2);
		em.flush();
		
		course1.setName("Web Services in 100 steps - Updated");
		course2.setName("Angular JS in 100 steps - Updated");

		em.refresh(course1);
		em.flush();
	}

	public void playWithEntityManager3() {
		Course course1 = new Course("Web Services in 100 steps");
		em.persist(course1);
		
		Course course2 = findById(10001L);
		course2.setName("JPA in 50 steps - updated");
		
	}
	
}
