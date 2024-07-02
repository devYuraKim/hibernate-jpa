package com.in28minutes.jpa.hibernate.section05.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.in28minutes.jpa.hibernate.section05.entity.Course;

//expose repository
@RepositoryRestResource(path="courses")
public interface CourseSpringDataRepository extends JpaRepository<Course, Long>{

	List<Course> findByName(String name);		
	List<Course> findByNameAndId(String name, Long id);
	List<Course> countByName(String name);		
	List<Course> findByNameOrderByIdDesc(String name);		
	List<Course> deleteByName(String name);	
	
	//Query using JPQL
	@Query("Select c from Course c where name like '%100 steps'")
	List<Course> courseWith100StepsInName_JPQL();
	
	//NativeQuery
	@Query(value="Select * from Course where name like '%100 steps'", nativeQuery=true)
	List<Course> courseWith100StepsInName_nativeQuery();
	
	//NamedQuery
	@Query(name = "query_get_100_step_courses")
	List<Course> courseWith100StepsInName_namedQuery();
}
