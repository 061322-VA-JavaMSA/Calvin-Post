package com.revature.daos;

import java.util.List;

import com.revature.models.User;

public interface UserDAO {
	
	User insertUser(User u);
	
	User updateUser(User u);

	User retrieveUserById(int id);

	User retrieveUserByUsername(String username);
	
	User retrieveUserByEmail(String email);

	List<User> retrieveUsers();
}
