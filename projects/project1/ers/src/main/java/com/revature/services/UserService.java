package com.revature.services;

import java.util.List;

import com.revature.daos.UserDAO;
import com.revature.daos.UserHibernate;
import com.revature.exceptions.NotCreatedException;
import com.revature.exceptions.NotFoundException;
import com.revature.models.User;

public class UserService {

	private UserDAO ud = new UserHibernate();
	
	public User createUser(User u) throws NotCreatedException {
		u.setRole(null);
		
		User createdUser = ud.insertUser(u);
		if (createdUser.getId() == -1) {
			throw new NotCreatedException();
		}
		return createdUser;
	}
	
	public List<User> getUsers(){
		return ud.retrieveUsers();
	}
	
	public User getUserById(int id) throws NotFoundException {
		User u = ud.retrieveUserById(id);
		if (u == null) {
			throw new NotFoundException();
		}
		return u;
	}
}
