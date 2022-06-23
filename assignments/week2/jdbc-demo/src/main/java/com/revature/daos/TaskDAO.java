package com.revature.daos;

import java.util.List;

import com.revature.models.Task;

public interface TaskDAO {

	Task createTash(Task t);
	List<Task> retrieveTasks();
	Task retrieveTaskById(int id);
	List<Task> retrieveTasksByUserId(int id);
	boolean updateTask(Task t);
}
