package com.revature.util;

public class Table {

	public static void title(String s) {
		Util.hr();
		Util.hr();
		row(s);
		Util.hr();
		Util.hr();
		Util.println();
	}
	
	public static void header(String s) {
		int space = (80 - s.length()) / 2;
		System.out.printf("%" + (space + s.length()) + "s%n", s);
		Util.hr();
	}
	
	public static void row(String s) {
		int space = (80 - s.length()) / 2;
		System.out.printf("%" + (space + s.length()) + "s%n", s);
	}
}
