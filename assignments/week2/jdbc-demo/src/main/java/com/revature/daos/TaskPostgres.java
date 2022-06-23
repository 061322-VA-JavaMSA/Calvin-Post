package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Task;
import com.revature.models.User;
import com.revature.util.ConnectionUtil;

public class TaskPostgres implements TaskDAO {

	@Override
	public Task createTash(Task t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Task> retrieveTasks() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Task retrieveTaskById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Task> retrieveTasksByUserId(int id) {
		String sql = "select * from tasks t join users u on t.user_assigned_id = u.id where u.id = ?;";
		List<Task> tasks = new ArrayList<>();
		
try(Connection c = ConnectionUtil.getConnectionFromEnv()){
			
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Task t = new Task();
				t.setDescription(rs.getString("description"));
				t.setDueDate(rs.getDate("due_date").toLocalDate());
				t.setId(rs.getInt("id"));
				t.setStatus(rs.getString("status"));
				
				User u = new User();
				u.setId(rs.getInt("user_assigned_id"));
				u.setUsername(rs.getString("username"));
				u.setPassword(rs.getString("password"));
				
				t.setUserAssigned(u);
				
				tasks.add(t);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return tasks;
	}

	@Override
	public boolean updateTask(Task t) {
		// TODO Auto-generated method stub
		return false;
	}

}
