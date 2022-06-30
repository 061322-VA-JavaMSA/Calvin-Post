package com.revature.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.daos.UserDAO;
import com.revature.daos.UserPostgres;
import com.revature.exceptions.LoginException;
import com.revature.models.User;
import com.revature.util.Table;
import com.revature.util.Util;

public class AuthService {

	private UserDAO ud = new UserPostgres();
	private Logger log = LogManager.getLogger(AuthService.class);

	public User login(String user, String pass) throws LoginException {
		User u = new User();
		
		if (user.equals(null) || pass.equals(null)) {
			throw new LoginException();
		}
		
		u = ud.getUserByUsername(user);

		if (u == null || !u.getPassword().equals(pass)) {
			throw new LoginException();
		}

		log.info("User " + u.getUsername() + " signed in successfully.");

		return u;

	}

}
