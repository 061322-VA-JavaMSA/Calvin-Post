package com.revature;

import java.util.List;

public class SimpleArraySum {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public static int simpleArraySum(List<Integer> ar) {
		int sum = 0;
		for (int i : ar) {
			sum += i;
		}
		return sum;

	}

}
