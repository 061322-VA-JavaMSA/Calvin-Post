package com.revature.enums;

public enum Role {
	GUEST(0), USER(1), EMPLOYEE(2), MANAGER(3);

	private int level;

	Role(int level) {
		this.level = level;
	}

	public boolean greaterThan(Role role) {
		return this.level > role.level;
	}
	
	public boolean lessThan(Role role) {
		return this.level < role.level;
	}
	
	public boolean equals(Role role) {
		return this.level == role.level;
	}
}
