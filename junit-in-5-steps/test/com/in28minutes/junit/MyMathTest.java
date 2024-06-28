package com.in28minutes.junit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MyMathTest {

	MyMath math = new MyMath();

	@Test
	void calculateSum_ThreeMemberArray() {
		//Absence of failure is success
		//Test Conditions = Asserts
		
		//int numbers[] = {1, 2, 3};
		//int result = math.calculateSum(numbers);
		//(1)numbers[] 우클릭 > Refactor > Inline
		
		//int result = math.calculateSum(new int[] {1, 2, 3});
		//int expectedResult = 6;
		//(2)result 우클릭 > Refactor > Inline
		
		//int expectedResult = 6;
		//assertEquals(expectedResult, math.calculateSum(new int[] {1, 2, 3}));
		//(3)expectedResult 우클릭 > Refactor > Inline
		
		assertEquals(6, math.calculateSum(new int[] {1, 2, 3}));
	}
	
	// array가 빈 경우 {} 결과가 0인지 확인하는 테스트
	@Test
	void calculateSum_ZeroLengthArray() {		
		assertEquals(0, math.calculateSum(new int[] {}));
	}

}
