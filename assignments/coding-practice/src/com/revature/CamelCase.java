package com.revature;

public class CamelCase {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public static int camelcase(String s) {
		// Write your code here
		int l = 1;
		for (char c : s.toCharArray()) {
			if (Character.isUpperCase(c)) {
				l++;
			}
		}
		return l;
	}

}
