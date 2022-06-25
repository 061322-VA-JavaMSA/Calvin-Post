package com.revature.services;

import com.revature.daos.UserDAO;
import com.revature.daos.UserPostgres;
import com.revature.exceptions.LoginException;
import com.revature.models.User;
import com.revature.util.Util;

public class AuthService {

	private UserDAO ud = new UserPostgres();

	public User login() {
		Util.clear();

		System.out.print("Username: ");
		String username = Util.in.nextLine();
		System.out.print("Password: ");
		String password = Util.in.nextLine();

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
