package com.revature.services;

import java.util.Scanner;

import com.revature.daos.UserDAO;
import com.revature.daos.UserPostgres;
import com.revature.exceptions.LoginException;
import com.revature.models.User;

public class AuthService {

	private UserDAO ud = new UserPostgres();
	
	public User login(String username, String password) throws LoginException {
		if (username == null || password == null) {
			throw new LoginException();
		}
		
		User u = ud.getUserByUsername(username);
		
		if (u == null || !u.getPassword().equals(password)) {
			throw new LoginException();
		}
		
		return u;
	}
	
	public User createUser(Scanner s) {
		System.out.flush();
		
		User u = new User();
		
		System.out.println("Please fill in all fields");
		System.out.print("Username: ");
		u.setUsername(s.nextLine());
		System.out.print("Password: ");
		u.setPassword(s.nextLine());
		System.out.println("First name: ");
		u.setFirstName(s.nextLine());
		System.out.println("Last name: ");
		u.setLastName(s.nextLine());
		
//		String street;
//		String state;
//		String zip;
		
		return u;
	}
}
