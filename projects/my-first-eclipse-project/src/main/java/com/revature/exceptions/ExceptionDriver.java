package com.revature.exceptions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class ExceptionDriver {

	public static void main(String[] args) {
		System.out.println("Beginning of the main method.");
		int a = 1;
		int b = 2;
		if (b != 0) {
			System.out.println(a / b);
		}

		int[] intArr = new int[3];
		for (int i = 0; i < intArr.length; i++) {
			System.out.println(intArr[i]);
		}
		
		List<Integer> al = new ArrayList<>();
		int i = 1;
		while(i<999999999) {
			al.add(i);
			i++;
		}
		System.out.println("End of the main method.");
	}

	public static void maybeException() throws FileNotFoundException {
		double fileExists = Math.random();
		if (fileExists > 0.5) {
			throw new FileNotFoundException();
		}

		FileInputStream in = new FileInputStream("input.txt");
	}
}
