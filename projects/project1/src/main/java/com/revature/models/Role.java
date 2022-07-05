package com.revature.models;

public enum Role {

	USER(1), EMPLOYEE(2), MANAGER(3);

	private int val;

	Role(int val) {
		this.val = val;
	}
	
	public static Role valueOf(int val) {
		for (Role r : Role.values()) {
			if (r.val == val) {
				return r;
			}
		}
		return null;
	}

}
