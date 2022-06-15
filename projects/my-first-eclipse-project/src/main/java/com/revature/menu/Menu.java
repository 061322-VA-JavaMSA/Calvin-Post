package com.revature.menu;

import java.util.Scanner;

public class Menu {

	static Scanner scan = new Scanner(System.in);
	static boolean run = true;
	
	public static void main(String[] args) {
		
		print("Hello!");
		
		while(run) {
			doSomething();
		}
		
		print("Goodbye");
		scan.close();

	}
	
	static void doSomething() {
	
		print("Please choose an option:");
		print("  1: Get a random number");
		print("  2: Get the reverse of a String");
		print("  3: Quit");
		
		switch(scan.nextLine()) {
		
		case "1":
			randomNumber();
			break;
			
		case "2":
			reverseString();
			break;
			
		case "3":
			run = false;
			break;
			
		default:
			print("Invalid input");
		}
		
	}
	
	static void randomNumber() {
		
		print("Generate a random number between 1 and ?: ");
		int n = scan.nextInt();
		scan.nextLine();
		int rand = (int)(Math.random() * n) + 1;
		print("Your random number is " + rand);
		
	}
	
	static void reverseString() {
		
		print("What string would you like to reverse?");
		String s = scan.nextLine();
		String s1 = "";
		char[] letters = s.toCharArray();
		for(char c : letters) {
			s1 = c + s1;
		}
		print("Here is your reversed string:");
		print(s1);
	}
	
	static void print(String s) {
		System.out.println(s);
	}

}
