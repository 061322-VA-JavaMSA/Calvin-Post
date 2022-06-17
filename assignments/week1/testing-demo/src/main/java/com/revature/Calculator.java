package com.revature;

import com.revature.exceptions.DivideByZeroException;
import com.revature.exceptions.NumberThirteenException;

/**
 * 
 * @author Calvin
 *
 */

public class Calculator {
	/*
	 * 	- add(int a, int b)
	 *  	- if the result is 13
	 *  		- throw NumberThirteenException
	 *  - subtract(int a, int b)
	 *  - multiply(int a, int b)
	 *  - sum(int[] arr)
	 *  - divide(int a, int b)
	 *  	- if divide by 0
	 *  		- throw DivideByZeroException
	 */

	public int add(int a, int b) {
		if(a + b == 13) {
			throw new NumberThirteenException();
		}
		return a + b;
	}
	
	public int subtract(int a, int b) {
		return a - b;
	}
	
	public int multiply(int a, int b) {
		return a * b;
	}
	
	public int divide(int a, int b) {
		if(b == 0) {
			throw new DivideByZeroException();
		}
		return a / b;
	}
	
	public int sum(int[] arr) {
		int sum = 0;
		for(int i : arr) {
			sum += i;
		}
		return sum;
	}
}
