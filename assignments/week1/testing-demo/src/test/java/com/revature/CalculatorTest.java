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
	public void add() {
		System.out.println("Testing add");
		for (int i = 0; i < 5; i++) {
			int a = (int) (Math.random() * 51);
			int b = (int) (Math.random() * 51);
			int expected = a + b;
			int actual = sut.add(a, b);
			System.out.println("input: (" + a + ", " + b + ") | expected: " + expected + " | actual: " + actual);			assertEquals(expected, actual);
		}
	}

	@Test
	public void subtract() {
		System.out.println("Testing subtract");
		for (int i = 0; i < 5; i++) {
			int a = (int) (Math.random() * 51);
			int b = (int) (Math.random() * 51);
			int expected = a - b;
			int actual = sut.subtract(a, b);
			System.out.println("input: (" + a + ", " + b + ") | expected: " + expected + " | actual: " + actual);			assertEquals(expected, actual);
		}
	}

	@Test
	public void multiply() {
		System.out.println("Testing multiply");
		for (int i = 0; i < 5; i++) {
			int a = (int) (Math.random() * 51);
			int b = (int) (Math.random() * 51);
			int expected = a * b;
			int actual = sut.multiply(a, b);
			System.out.println("input: (" + a + ", " + b + ") | expected: " + expected + " | actual: " + actual);
			assertEquals(expected, actual);
		}
	}

	@Test
	public void divide() {
		System.out.println("Testing divide");
		for (int i = 0; i < 5; i++) {
			int a = (int) (Math.random() * 50 + 1);
			int b = (int) (Math.random() * 50 + 1);
			int expected = a / b;
			int actual = sut.divide(a, b);
			System.out.println("input: (" + a + ", " + b + ") | expected: " + expected + " | actual: " + actual);
			assertEquals(expected, actual);
		}
	}

	@Test
	public void addEquals13() {
		System.out.println("Testing when add equals 13");
		for (int i = 0; i <= 13; i++) {
			for (int j = 0; j <= 13; j++) {
				if (i + j == 13) {
					int a = i, b = j;
					System.out.println("input: (" + a + ", " + b + ")");
					assertThrows(NumberThirteenException.class, () -> sut.add(a, b));
				}
			}
		}
	}

	@Test
	public void divideByZero() {
		System.out.println("Testing dividing by 0");
		for (int i = 0; i < 5; i++) {
			int a = (int) (Math.random() * 51);
			int b = 0;
			System.out.println("input: (" + a + ", " + b + ")");
			assertThrows(DivideByZeroException.class, () -> sut.divide(a, b));
		}
	}

	@Test
	public void sum() {
		System.out.println("Testing sum");
		for (int i = 0; i < 5; i++) {
			int[] arr = new int[(int) (Math.random() * 10 + 1)];
			int expected = 0;
			for (int j = 0; j < arr.length; j++) {
				int a = (int) (Math.random() * 51);
				arr[j] = a;
				expected += a;
			}
			int actual = sut.sum(arr);

			System.out.print("input: [");
			for (int j = 0; j < arr.length - 1; j++) {
				System.out.print(j + ", ");
			}
			System.out.println(arr[arr.length - 1] + "] | expected: " + expected + " | actual: " + actual);
			assertEquals(expected, actual);
		}
	}
}
