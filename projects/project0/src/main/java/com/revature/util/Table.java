package com.revature.util;

public class Table {

	public static void title(String s) {
		Util.clear();
		Util.banner();
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
	
	public static void row(Object[] columns) {
		int w = 80;
		int s = 4;
		String p = String.format("%0" + s + "d", 0).replace('0', ' ');
		String row = "";
		for(int i=0; i<columns.length; i++) {
			if(i>0) row += p;
			row += columns[i];
		}
		int leadSpace = (w - row.length()) / 2;
		System.out.printf("%" + (leadSpace + row.length()) + "s%n", row);
	}
	
	public static void row(Object[] columns, int width) {
		int w = width;
		int s = 4;
		String p = String.format("%0" + s + "d", 0).replace('0', ' ');
		String row = "";
		for(int i=0; i<columns.length; i++) {
			if(i>0) row += p;
			row += columns[i];
		}
		int leadSpace = (w - row.length()) / 2;
		System.out.printf("%" + (leadSpace + row.length()) + "s%n", row);
	}
	
	public static void row(Object[] columns, int width, int space) {
		int w = width;
		int s = space;
		String p = String.format("%0" + s + "d", 0).replace('0', ' ');
		String row = "";
		for(int i=0; i<columns.length; i++) {
			if(i>0) row += p;
			row += columns[i];
		}
		int leadSpace = (w - row.length()) / 2;
		System.out.printf("%" + (leadSpace + row.length()) + "s%n", row);
	}
}
