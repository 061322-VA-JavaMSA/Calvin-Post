package com.revature.dtos;

import java.util.Objects;

import com.revature.models.User;

public class UserDTO {

	private int id;
	private String username;
	private String fullName;
	private String firstName;
	private String lastName;
	private String email;
	private String role;

	public UserDTO(User u) {
		if (u != null) {
			id = u.getId();
			username = u.getUsername();
			fullName = u.getFirstName() + " " + u.getLastName();
			firstName = u.getFirstName();
			lastName = u.getLastName();
			email = u.getEmail();
			role = u.getRole().getRole();
		}
	}

	public UserDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, firstName, fullName, id, lastName, role, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDTO other = (UserDTO) obj;
		return Objects.equals(email, other.email) && Objects.equals(firstName, other.firstName)
				&& Objects.equals(fullName, other.fullName) && id == other.id
				&& Objects.equals(lastName, other.lastName) && Objects.equals(role, other.role)
				&& Objects.equals(username, other.username);
	}

	@Override
	public String toString() {
		return "UserDTO [id=" + id + ", username=" + username + ", fullName=" + fullName + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", email=" + email + ", role=" + role + "]";
	}
	
	
}
