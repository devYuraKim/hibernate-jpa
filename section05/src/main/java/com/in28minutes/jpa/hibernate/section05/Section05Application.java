 package com.in28minutes.jpa.hibernate.section05;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.in28minutes.jpa.hibernate.section05.entity.FullTimeEmployee;
import com.in28minutes.jpa.hibernate.section05.entity.PartTimeEmployee;
import com.in28minutes.jpa.hibernate.section05.repository.CourseRepository;
import com.in28minutes.jpa.hibernate.section05.repository.EmployeeRepository;
import com.in28minutes.jpa.hibernate.section05.repository.StudentRepository;


//CommandLineRunner: 스프링 애플리케이션 가독 직후 실행되는 코드를 run method에 정의함
@SpringBootApplication
public class Section05Application implements CommandLineRunner {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CourseRepository courseRepository;
	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private EmployeeRepository employeeRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(Section05Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//Course course = repository.findById(10001L);
		//logger.info("Course 10001 -> {}", course);
		
		//repository.save(new Course("Microservices in 100 steps"));
		//repository.playWithEntityManager3();
		
		//studentRepository.saveStudentWithPassport();
		
		//courseRepository.addHardCodedReviewsForCourse();
		
		//List<Review> reviews = new ArrayList<>();
		//reviews.add(new Review("5", "Great Hands-on stuff"));
		//reviews.add(new Review("5", "Hats-off"));
		//courseRepository.addReviewsForCourse(10003l, reviews);
		
		//studentRepository.insertHardcodedStudentAndCourse();
		
		employeeRepository.insert(new FullTimeEmployee("Jack", new BigDecimal("10000")));
		employeeRepository.insert(new PartTimeEmployee("Jill", new BigDecimal("50")));
		//logger.info("AllEmployees -> {}", employeeRepository.retrieveallEmployees());
		logger.info("FullTimeEmployees -> {}", employeeRepository.retrieveAllFullTimeEmployees());
		logger.info("FullTimeEmployees -> {}", employeeRepository.retrieveAllPartTimeEmployees());
	}
	
}
