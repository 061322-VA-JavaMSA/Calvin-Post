package com.revature.daos;

import java.util.List;

import com.revature.models.User;

public interface UserDAO {

	User createUser(User u);
	User retrieveUserById(int id);
	List<User> retrieveUsers();
	User retrieveUserByUsername(String username);
	boolean updateUserById(int id);
}
