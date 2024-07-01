package com.in28minutes.jpa.hibernate.section05.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.in28minutes.jpa.hibernate.section05.entity.Course;

public interface CourseSpringDataRepository extends JpaRepository<Course, Long>{

			
}
