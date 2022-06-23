package com.revature.daos;

import java.util.List;

import com.revature.models.User;

public interface UserDAO {

	User createUser(User u);
	User getUserById(int id);
	User getUserByUsername(String username);
	boolean deleteUserById(int id);
	List<User> getUsers();
	List<User> getUsers(String level);
	boolean updateUserById(int id);
}
