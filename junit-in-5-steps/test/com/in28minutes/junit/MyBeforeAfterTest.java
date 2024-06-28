package com.in28minutes.junit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MyBeforeAfterTest {

	//BeforeAll, AfterAll은 static이어야 한다
	//특히 BeforeAll은 프로그램이 실행되기 이전이기 때문에 static이어야 한다
	@BeforeAll
	static void beforeAll() {
		System.out.println("BeforeAll");
	}
	
	@AfterAll
	static void afterAll() {
		System.out.println("AfterAll");
	}

	
	//각 테스트 이전에 시행
	@BeforeEach
	void beforeEach() {
		System.out.println("BeforeEach");
	}
	
	//각 테스트 이후에 시행
	@AfterEach
	void afterEach() {
		System.out.println("AfterEach");
	}
	
	@Test
	void test1() {
		System.out.println("test1");
	}
	
	@Test
	void test2() {
		System.out.println("test2");
	}

	@Test
	void test3() {
		System.out.println("test3");
	}

	

}
