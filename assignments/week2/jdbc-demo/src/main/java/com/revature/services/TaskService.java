package com.revature.services;

import java.util.List;

import com.revature.daos.TaskDAO;
import com.revature.daos.TaskPostgres;
import com.revature.models.Task;

public class TaskService {

	private TaskDAO td = new TaskPostgres();
	
	public List<Task> getTasksByUserId(int id){
		return td.retrieveTasksByUserId(id);
	}
}
