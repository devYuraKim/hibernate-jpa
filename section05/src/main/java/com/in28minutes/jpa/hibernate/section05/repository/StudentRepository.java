package com.in28minutes.jpa.hibernate.section05.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.in28minutes.jpa.hibernate.section05.entity.Passport;
import com.in28minutes.jpa.hibernate.section05.entity.Student;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Repository
@Transactional //delete의 경우, Transactional이 없이는 제대로 구동되지 않음
public class StudentRepository {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	EntityManager em;
	
	public Student findById(Long id) {
		return em.find(Student.class, id);
	}
	
	public Student save(Student student) {
		if(student.getId()==null) {
			em.persist(student);
		}else {
			em.merge(student);
		}
		return student;
	}
	
	public void deleteById(Long id) {
		Student student = findById(id);
		em.remove(student);
	}
	
	public void saveStudentWithPassport() {
		Passport passport = new Passport("Z123456");
		em.persist(passport); //passport 정보가 DB에 있어야 Student에서 처리가 가능함
		Student student = new Student("Mike");
		student.setPassport(passport);
		em.persist(student);		
	}
	
	public void someOperationToUnderstandPersistenceContext() {
		//Database Operation 1 - retrieve 
		Student student = em.find(Student.class, 20001l); // EntityManager: persistence context {student}
		
		//Database Operation 2 - retrieve
		Passport passport = student.getPassport(); 
		// w/o @Transactional: no persistence context 왜냐면 Entity Manager가 없으니까!
		// w/ @Transactional: persistence context {student, passport}
		//아래 코드도 모두 마찬가지
		//하지만 class에 @Transactional 적용되어 있어서 method에 @가 적용된 것과 동일한 효과
		
		//Database Operation 3 - update
		passport.setNumber("updated"); //persistence context {student, passport'}
		
		//Database Operation 4 - update
		student.setName("updated"); //persistence context {student', passport'}
	}
	
	
}
