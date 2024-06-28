package com.in28minutes.jpa.hibernate.section05.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.in28minutes.jpa.hibernate.section05.entity.Course;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Repository
@Transactional //delete의 경우, Transactional이 없이는 제대로 구동되지 않음
public class CourseRepository {
	
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
	
}
