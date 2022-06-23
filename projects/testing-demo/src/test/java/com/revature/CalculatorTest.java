package com.revature;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.revature.exceptions.Number13Exception;

public class CalculatorTest {

	private static Calculator sut;
	
	/*
	 * JUnit 5
	 * 	- @BeforeAll
	 * 	- @BeforeEach
	 * 	- @AfterAll
	 * 	- @AfterEach
	 * 
	 * 	- @Test
	 * 	- @Ignore
	 * 	- @Order
	 */
	
	@BeforeAll
	public static void setUp() {
		sut = new Calculator();
	}
	
	@Test
	public void addOneAndTwo() {
		
	}
	
	@Test
	public void addEightAndFive() {
		assertThrows(Number13Exception.class, () -> sut.add(8, 5));
	}
	
}
