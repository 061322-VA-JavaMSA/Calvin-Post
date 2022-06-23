package com.revature.oop;

import com.revature.oop.daos.TaskDAO;
import com.revature.oop.daos.TaskPostgres;
import com.revature.oop.models.BoringTask;
import com.revature.oop.models.Task;

public class Driver {

	public static void main(String[] args) {
		Task t = new BoringTask();
		
		System.out.println(t.getDueDate());
		System.out.println(t.isCompleted());
		System.out.println(t.getDescription());
		System.out.println();
		
		Task t1 = new Task("Do laundry.");
		System.out.println(t1.getDueDate());
		System.out.println(t1.isCompleted());
		System.out.println(t1.getDescription());
		
		TaskDAO td = new TaskPostgres();

	}

}
