package com.revature;

public class Driver {

	public static void main(String[] args) {
		MyClass mc = new MyClass("Calvin");
		System.out.println(mc.reverseString("Calvin"));
		
		String a = "Test";
		String b = "Test";
		a = "New Test";
		System.out.println("String a: " + a);
		System.out.println("String b: " + b);
	}
}
