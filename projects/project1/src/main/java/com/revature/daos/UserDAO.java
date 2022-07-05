package com.revature.daos;

import java.sql.SQLException;
import java.util.List;

import com.revature.models.User;

public interface UserDAO {

	User retrieveUserById(int id) throws SQLException;

	User retrieveUserByUsername(String username) throws SQLException;

	List<User> retrieveUsers() throws SQLException;
}
