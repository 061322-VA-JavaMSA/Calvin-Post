package com.revature;

public class MyClass {
	
	public String myString;

	public MyClass() {
		
	}
	
	public MyClass(String myString) {
		this.myString = myString;
	}
	
	public String reverseString(String s) {
		char[] letters = s.toCharArray();
		String reverse = "";
		for(char c : letters) {
			reverse = c + reverse;
		}
		return reverse;
	}
}
