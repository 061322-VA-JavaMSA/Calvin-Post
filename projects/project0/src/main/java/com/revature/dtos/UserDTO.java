package com.revature.dtos;

import com.revature.enums.Role;
import com.revature.models.User;

public class UserDTO {

	private int id;
	private Role role;
	private String username;
	private String firstName;
	private String lastName;
	
	public UserDTO(User u) {
		this.id = u.getId();
		this.role = u.getRole();
		this.username = u.getUsername();
		this.firstName = u.getFirstName();
		this.lastName = u.getLastName();
	}
}
