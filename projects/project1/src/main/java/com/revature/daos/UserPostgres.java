package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Role;
import com.revature.models.User;
import com.revature.util.ConnectionUtil;

public class UserPostgres implements UserDAO {

	@Override
	public User retrieveUserById(int id) throws SQLException {
		String sql = "select * from users where id = " + id;
		User u = null;

		Connection c = ConnectionUtil.getConnectionFromEnv();
		Statement s = c.createStatement();
		ResultSet rs = s.executeQuery(sql);
		if (rs.next()) {
			u = new User();
			u.setId(rs.getInt("id"));
			u.setUsername(rs.getString("username"));
			u.setPassword(rs.getString("password"));
			u.setFirstName(rs.getString("first_name"));
			u.setLastName(rs.getString("last_name"));
			u.setEmail(rs.getString("email"));
			u.setRole(rs.getInt("role"));
		}
		return u;
	}

	@Override
	public User retrieveUserByUsername(String username) throws SQLException {
		String sql = "select * from users where username = ?;";
		User u = null;

		Connection c = ConnectionUtil.getConnectionFromEnv();
		PreparedStatement ps = c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			u = new User();
			u.setId(rs.getInt("id"));
			u.setUsername(rs.getString("username"));
			u.setPassword(rs.getString("password"));
			u.setFirstName(rs.getString("first_name"));
			u.setLastName(rs.getString("last_name"));
			u.setEmail(rs.getString("email"));
			u.setRole(rs.getInt("role"));
		}
		return u;
	}

	@Override
	public List<User> retrieveUsers() throws SQLException {
		String sql = "select * from users;";
		List<User> users = new ArrayList<>();

		Connection c = ConnectionUtil.getConnectionFromEnv();
		Statement s = c.createStatement();
		ResultSet rs = s.executeQuery(sql);
		while (rs.next()) {
			User u = new User();
			u.setId(rs.getInt("id"));
			u.setUsername(rs.getString("username"));
			u.setPassword(rs.getString("password"));
			u.setFirstName(rs.getString("first_name"));
			u.setLastName(rs.getString("last_name"));
//			u.setEmail(rs.getString("email"));
//			u.setRole(rs.getInt("role"));

			users.add(u);
		}
		return users;
	}

}
