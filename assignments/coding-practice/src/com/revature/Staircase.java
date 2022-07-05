package com.revature;

public class Staircase {

	public static void main(String[] args) {
		
	}
	
	public static void staircase(int n) {
        // Write your code here
        for (int i = 1; i <= n; i++) {
            if (i < n) {
                System.out.print(String.format("%0" + (n - i) + "d", 0).replace("0", " "));
            }
            System.out.println(String.format("%0" + i + "d", 0).replace("0", "#"));
        }
    }
}
