package com.revature.services;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.daos.UserDAO;
import com.revature.daos.UserPostgres;
import com.revature.enums.Role;
import com.revature.exceptions.BadRequestException;
import com.revature.models.User;
import com.revature.util.Table;

public class UserService {

	private UserDAO ud = new UserPostgres();
	private Logger log = LogManager.getLogger(UserService.class);
	
	public User createUser(String user, String pass, String first, String last) throws BadRequestException {
		Table.title("Create New User");

		User u = new User();
		u.setUsername(user);
		u.setPassword(pass);
		u.setFirstName(first);
		u.setLastName(last);
		u.setRole(Role.USER);

		u = ud.createUser(u);

		if (u.getId() == -1) {
			log.error("Unable to create user " + u.getUsername());
			u = new User();
			throw new BadRequestException();
		} else {
			log.info("User " + u.getUsername() + " was created.");
		}
		return u;
	}

	public List<User> getUsers() {
		return ud.getUsers();
	}

	public boolean createEmployee(String user, String pass, String first, String last) {
		Table.title("Create New Employee");

		User u = new User();

		u.setUsername(user);
		u.setPassword(pass);
		u.setFirstName(first);
		u.setLastName(last);
		u.setRole(Role.EMPLOYEE);

		u = ud.createUser(u);

		if (u.getId() == -1 || u == null) {
			log.error("Unable to create employee " + u.getUsername());
			return false;
		} else {
			log.info("Employee " + u.getUsername() + " was created.");
			return true;
		}
	}
	
	public List<User> getEmployees(Role role){
		if(role.greaterThan(Role.EMPLOYEE)) {
			return ud.getUsersByRole(Role.EMPLOYEE);
		} else {
			return null;
		}
	}

	public List<User> getUsers(Role role) {
		if(role.greaterThan(Role.USER)) {
			return ud.getUsersByRole(Role.USER);
		} else {
			return null;
		}
	}

	public boolean removeUser(User u) {
		if(ud.deleteUserById(u.getId())) {
			log.info("User " + u.getUsername() + " was removed.");
			return true;
		} else {
			return false;
		}
		
	}

}
