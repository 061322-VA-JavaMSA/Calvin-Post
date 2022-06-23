package com.revature.services;

import java.util.Scanner;

import com.revature.daos.UserDAO;
import com.revature.daos.UserPostgres;
import com.revature.exceptions.LoginException;
import com.revature.models.User;

public class AuthService {

	private UserDAO ud = new UserPostgres();

	public User login(Scanner s) {
		System.out.flush();

		System.out.print("Username: ");
		String username = s.nextLine();
		System.out.print("Password: ");
		String password = s.nextLine();

		try {
			if (username == null || password == null) {
				throw new LoginException();
			}

			User u = ud.getUserByUsername(username);

			if (u == null || !u.getPassword().equals(password)) {
				throw new LoginException();
			}

			System.out.println("Login successful.");
			return u;
		} catch (LoginException e) {
			System.out.println("Unable to sign in.");
			return new User();
		}
	}
	
}
