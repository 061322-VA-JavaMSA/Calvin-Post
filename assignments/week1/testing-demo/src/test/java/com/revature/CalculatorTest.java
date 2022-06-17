package com.revature;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.revature.exceptions.DivideByZeroException;
import com.revature.exceptions.NumberThirteenException;

public class CalculatorTest {

	private static Calculator sut;

	@BeforeAll
	public static void setUp() {
		sut = new Calculator();
		System.out.println("Begin testing.");
	}

	@AfterAll
	public static void tearDown() {
		System.out.println("End of testing.");
	}

	@BeforeEach
	public void before() {
		System.out.println("- Running next test...");
	}

	@Test
	public void addFourFive() {
		int expected = 9;
		int actual = sut.add(4, 5);
		assertEquals(expected, actual);
	}

	@Test
	public void subtractFourFive() {
		int expected = -1;
		int actual = sut.subtract(4, 5);
		assertEquals(expected, actual);
	}

	@Test
	public void multiplyFourFive() {
		int expected = 20;
		int actual = sut.multiply(4, 5);
		assertEquals(expected, actual);
	}

	@Test
	public void divideSixTwo() {
		int expected = 3;
		int actual = sut.divide(6, 2);
		assertEquals(expected, actual);
	}

	@Test
	public void addSixSeven() {
		assertThrows(NumberThirteenException.class, () -> sut.add(6, 7));
	}

	@Test
	public void divideFiveByZero() {
		assertThrows(DivideByZeroException.class, () -> sut.divide(5, 0));
	}

	@Test
	public void sumFourFiveSix() {
		int[] arr = {4, 5, 6};
		int expected = 15;
		int actual = sut.sum(arr);
		assertEquals(expected, actual);
	}
}
