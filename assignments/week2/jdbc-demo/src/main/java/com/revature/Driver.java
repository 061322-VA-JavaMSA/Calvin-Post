package com.revature;

import java.util.List;
import java.util.Scanner;

import com.revature.exceptions.LoginException;
import com.revature.models.Task;
import com.revature.models.User;
import com.revature.services.AuthService;
import com.revature.services.TaskService;
import com.revature.services.UserService;

public class Driver {

	static Scanner scan;
	static AuthService as;
	static UserService us;
	static TaskService ts;
	
	public static void main(String[] args) {
		
		scan = new Scanner(System.in);
		as = new AuthService();
		us = new UserService();
		ts = new TaskService();
		String username = null;
		String password = null;
	/*	
		System.out.println("Please enter username:");
		username = scan.nextLine();
		System.out.println("Please enter password:");
		password = scan.nextLine();
		
		try {
			System.out.println(as.login(username, password));
		} catch (LoginException e) {
			System.out.println("Invalid credentials.");
			e.printStackTrace();
		}
		
		List<User> users = us.getUsers();
		for(User u : users) {
			System.out.println(u);
		}
		
		System.out.println("Create User");
		System.out.println("Username:");
		String uname = scan.nextLine();
		System.out.println("Password:");
		String pass = scan.nextLine();
		User userTBC = new User();
		userTBC.setUsername(uname);
		userTBC.setPassword(pass);
		
		System.out.println(us.createUser(userTBC));
		*/
		System.out.println("Retrieve tasks for which user id?");
		int userId = Integer.parseInt(scan.nextLine());
		List<Task> tasksForUser = ts.getTasksByUserId(userId);
		for(Task t : tasksForUser) {
			System.out.println(t);
		}
		
		scan.close();
	}

}
