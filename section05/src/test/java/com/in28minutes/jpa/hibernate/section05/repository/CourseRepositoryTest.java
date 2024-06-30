package com.in28minutes.jpa.hibernate.section05.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.in28minutes.jpa.hibernate.section05.Section05Application;
import com.in28minutes.jpa.hibernate.section05.entity.Course;
import com.in28minutes.jpa.hibernate.section05.entity.Review;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@SpringBootTest(classes=Section05Application.class)
class CourseRepositoryTest {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	CourseRepository repository;
	
	@Autowired
	EntityManager em;
	
	@Test
	public void findById_basic() {
		Course course = repository.findById(10002L);
		assertEquals("Spring in 50 steps", course.getName());
	}
	
	@Test
	@DirtiesContext
	//data가 삭제되어서 context가 dirty해졌으므로 이를 다시 복구함
	//data가 복구되므로 동일한 data에 대해 다른 test 진행 가능
	public void deleteById_basic() {
		repository.deleteById(10002L);
		assertNull(repository.findById(10002L));
	}
	
	@Test
	@DirtiesContext
	public void save_basic() {
		
		//course 객체 존재 확인
		Course course = repository.findById(10001L);
		assertEquals("JPA in 50 steps", course.getName());

		//update 수행
		course.setName("JPA in 50 steps - Updated");
		repository.save(course);
		
		//update 제대로 수행됐는지 확인
		Course course1 = repository.findById(10001L);
		assertEquals("JPA in 50 steps - Updated", course1.getName());
	}
	
	@Test
	public void playWithEntityManager() {
		repository.playWithEntityManager();
	}
	
	@Test
	@Transactional
	public void retrieveReviewsForCourse() {
		Course course = repository.findById(10001l);
		logger.info("{}", course.getReviews());
	}

	@Test
	@Transactional
	public void retrieveCourseForReview() {
		Review review = em.find(Review.class, 50001l);
		logger.info("{}", review.getCourse());
	}
}
