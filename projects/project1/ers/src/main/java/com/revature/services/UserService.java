package com.revature.services;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.daos.UserDAO;
import com.revature.daos.UserHibernate;
import com.revature.exceptions.NotCreatedException;
import com.revature.exceptions.NotFoundException;
import com.revature.models.Role;
import com.revature.models.User;

public class UserService {

	private UserDAO ud = new UserHibernate();
	private static Logger log = LogManager.getLogger(UserService.class);

	public User createUser(User u) throws NotCreatedException {
		u.setRole(null);

		User createdUser = ud.insertUser(u);
		if (createdUser.getId() == -1) {
			throw new NotCreatedException();
		}
		log.info("Created user: " + createdUser);
		return createdUser;
	}

	public User updateUser(User u) {
		User updated = ud.updateUser(u);
		log.info("Updated user: " + updated);
		return updated;
	}

	public List<User> getUsers() throws NotFoundException {
		List<User> users = ud.retrieveUsers();
		if (users == null || users.isEmpty()) {
			throw new NotFoundException();
		}
		
		log.info("Retrieved " + users.size() + " users.");
		return users;
	}

	public List<User> getUsersByRole(Role r) throws NotFoundException {
		List<User> users = null;

		if (r == null) {
			throw new NotFoundException();
		}

		users = ud.retrieveUsersByRole(r);
		if (users == null || users.isEmpty()) {
			throw new NotFoundException();
		}
		log.info("Retrieved " + users.size() + " users with role " + r);
		return users;
	}

	public User getUserById(int id) throws NotFoundException {
		User u = ud.retrieveUserById(id);
		if (u == null) {
			throw new NotFoundException();
		}
		log.info("Retrieved user: " + u);
		return u;
	}
}
