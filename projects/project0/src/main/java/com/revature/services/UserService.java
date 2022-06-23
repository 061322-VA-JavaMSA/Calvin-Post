package com.revature.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.revature.daos.UserDAO;
import com.revature.daos.UserPostgres;
import com.revature.models.User;

public class UserService {
	
	private UserDAO ud = new UserPostgres();

	public User createUser(Scanner s) {
		System.out.flush();

		User u = new User();

		System.out.println("Please fill in all fields");
		System.out.print("Username: ");
		u.setUsername(s.nextLine());
		System.out.print("Password: ");
		u.setPassword(s.nextLine());
		System.out.print("First name: ");
		u.setFirstName(s.nextLine());
		System.out.print("Last name: ");
		u.setLastName(s.nextLine());
		u.setLevel(1);

		u = ud.createUser(u);

		if (u.getId() == 0) {
			System.out.println("User exists. Unable to create user.");
			return null;
		}

		System.out.println("User created successfully.");
		return u;
	}
	
	public User createEmployee(Scanner s) {
		System.out.flush();

		User u = new User();

		System.out.println("Please fill in all fields");
		System.out.print("Username: ");
		u.setUsername(s.nextLine());
		System.out.print("Password: ");
		u.setPassword(s.nextLine());
		System.out.print("First name: ");
		u.setFirstName(s.nextLine());
		System.out.print("Last name: ");
		u.setLastName(s.nextLine());
		u.setLevel(2);

		u = ud.createUser(u);

		if (u.getId() == 0) {
			System.out.println("User exists. Unable to create user.");
			return null;
		}

		System.out.println("User created successfully.");
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
