package com.in28minutes.jpa.hibernate.section05.repository;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.in28minutes.jpa.hibernate.section05.entity.Employee;
import com.in28minutes.jpa.hibernate.section05.entity.FullTimeEmployee;
import com.in28minutes.jpa.hibernate.section05.entity.PartTimeEmployee;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class EmployeeRepository {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private EntityManager em;
	
	//insert an employee
	public void insert(Employee employee) {
		em.persist(employee);
	}
	
	//retrieve all employees
	//@MappedSuperclass를 사용하는 경우 Employee는 Entity가 아니게 되어 Employee table 조회 불가, 개별 table 조회 필요
	//public List<Employee> retrieveallEmployees(){
	//	return em.createQuery("select e from Employee e", Employee.class).getResultList();
	//}
	
	public List<FullTimeEmployee> retrieveAllFullTimeEmployees(){
		return em.createQuery("select e from FullTimeEmployee e", FullTimeEmployee.class).getResultList();
	}
	
	public List<PartTimeEmployee> retrieveAllPartTimeEmployees(){
		return em.createQuery("select e from PartTimeEmployee e", PartTimeEmployee.class).getResultList();
	}
}
