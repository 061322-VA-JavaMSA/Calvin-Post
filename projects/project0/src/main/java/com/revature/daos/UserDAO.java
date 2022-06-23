package com.revature.daos;

import java.util.List;

import com.revature.models.User;

public interface UserDAO {

	User newUser(User u);
	User getUserById(int id);
	User getUserByUsername(String username);
	List<User> getUsers();
	boolean updateUserById(int id);
}
