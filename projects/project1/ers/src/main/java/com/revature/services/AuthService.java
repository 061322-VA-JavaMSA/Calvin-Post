package com.revature.services;

import com.revature.daos.UserDAO;
import com.revature.daos.UserHibernate;
import com.revature.exceptions.AuthException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.User;
import com.revature.util.Validate;

public class AuthService {

	private UserDAO ud = new UserHibernate();

	public User login(String username, String password) throws AuthException, UserNotFoundException {
		User principal;

		if (Validate.isEmail(username)) {
			principal = ud.retrieveUserByEmail(username);
		} else {
			principal = ud.retrieveUserByUsername(username);
		}
		
		if(principal == null) {
			throw new UserNotFoundException();
		}
		if (!principal.getPassword().equals(password)) {
			throw new AuthException();
		}
		
		return principal;
	}
	
}
