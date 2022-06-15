package com.revature.scanner;

import java.util.Scanner;

public class ScannerDriver {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);

		System.out.println("Hello World!");
		System.out.println("What is your name?");
		
		String name = scan.nextLine();
		
		System.out.println("Hello, " + name + "!");
		System.out.println("Please pick a number");
		
		int num = scan.nextInt();
		System.out.println("Your number is " + num);
		
		System.out.println("Please pick a number from 1-3:");
		String number = scan.nextLine();
		
		switch (number) {
		case "1":
			System.out.println("Hello");
			break;
		case "2":
			System.out.println("Goodbye");
			break;
		default:
			System.out.println("Not recognized");
		}
		
	}

}
