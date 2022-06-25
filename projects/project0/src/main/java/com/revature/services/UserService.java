package com.revature.services;

import java.util.List;

import com.revature.daos.UserDAO;
import com.revature.daos.UserPostgres;
import com.revature.models.User;
import com.revature.util.Util;

public class UserService {

	private UserDAO ud = new UserPostgres();

	public User createUser() {
		Util.clear();

		User u = new User();

		Util.println("Please fill in all fields");
		Util.print("Username: ");
		u.setUsername(Util.in.nextLine());
		Util.print("Password: ");
		u.setPassword(Util.in.nextLine());
		Util.print("First name: ");
		u.setFirstName(Util.in.nextLine());
		Util.print("Last name: ");
		u.setLastName(Util.in.nextLine());
		u.setLevel(1);

		u = ud.createUser(u);

		if (u.getId() == 0) {
			Util.println("User exists. Unable to create user.");
			return null;
		}

		Util.println("User created successfully.");
		return u;
	}

	public User createEmployee() {
		Util.clear();

		User u = new User();

		Util.println("Please fill in all fields");
		Util.print("Username: ");
		u.setUsername(Util.in.nextLine());
		Util.print("Password: ");
		u.setPassword(Util.in.nextLine());
		Util.print("First name: ");
		u.setFirstName(Util.in.nextLine());
		Util.print("Last name: ");
		u.setLastName(Util.in.nextLine());
		u.setLevel(2);

		u = ud.createUser(u);

		if (u.getId() == 0) {
			Util.println("User exists. Unable to create user.");
			return null;
		}

		Util.println("User created successfully.");
		return u;
	}

	public boolean removeUser(int id) {
		return false;
	}

	public List<User> getEmployees() {

		List<User> employees = ud.getUsers("> 1");
		return employees;

	}
}
