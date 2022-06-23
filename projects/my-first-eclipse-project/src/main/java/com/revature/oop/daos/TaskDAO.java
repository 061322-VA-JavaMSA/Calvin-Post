package com.revature.oop.daos;

import com.revature.oop.models.Task;

public interface TaskDAO {

	public abstract Task getTaskById(int id);
	Task addTask(Task newTask); // Implicitly public and abstract
	default boolean deleteTask(int id) {
		return true;
	}
}
