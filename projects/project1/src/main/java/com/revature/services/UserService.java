package com.revature.services;

import java.sql.SQLException;
import java.util.List;

import com.revature.daos.UserDAO;
import com.revature.daos.UserPostgres;
import com.revature.models.User;

public class UserService {

	private UserDAO ud = new UserPostgres();
	
	public List<User> getUsers() throws SQLException {
		return ud.retrieveUsers();
	}
	
	public User getUser(int id) throws SQLException {
		if(id < 1) {
			return null;
		} else {
			return ud.retrieveUserById(id);
		}
	}
}
