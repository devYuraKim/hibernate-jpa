package com.in28minutes.jpa.hibernate.section05.repository;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.in28minutes.jpa.hibernate.section05.entity.Employee;
import com.in28minutes.jpa.hibernate.section05.entity.FullTimeEmployee;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class EmployeeRepository {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	EntityManager em;
	
	//insert an employee
	public void insert(Employee employee) {
		em.persist(employee);
	}
	
	//retrieve all employees
	public List<Employee> retrieveallEmployees(){
		return em.createQuery("select e from Employee e", Employee.class).getResultList();
	}
}
