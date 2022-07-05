package com.revature.services;

import java.sql.SQLException;

import com.revature.daos.UserDAO;
import com.revature.daos.UserPostgres;
import com.revature.exceptions.AuthException;
import com.revature.models.User;

public class AuthService {

	private UserDAO ud = new UserPostgres();

	public User login(String username, String password) throws AuthException, SQLException {
		User u = null;

		if (username == null || password == null) {
			throw new AuthException();
		}

		u = ud.retrieveUserByUsername(username);
		if (u.getPassword().equals(password)) {
			return u;
		} else {
			throw new AuthException();
		}
	}
}
