package com.revature;

import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
	private static Logger log = LogManager.getLogger(Driver.class);

	
	public static void main(String[] args) {
		
		scan = new Scanner(System.in);
		as = new AuthService();
		us = new UserService();
		ts = new TaskService();
		String username = null;
		String password = null;
		
		System.out.println("Please enter username:");
		username = scan.nextLine();
		System.out.println("Please enter password:");
		password = scan.nextLine();
		
		try {
			log.info(as.login(username, password));
		} catch (LoginException e) {
			System.out.println("Invalid credentials.");
			log.error("Login exception was thrown: " + e.fillInStackTrace());
//			e.printStackTrace();
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
		
		log.info(us.createUser(userTBC));
		
		System.out.println("Retrieve tasks for which user id?");
		int userId = Integer.parseInt(scan.nextLine());
		List<Task> tasksForUser = ts.getTasksByUserId(userId);
		for(Task t : tasksForUser) {
			System.out.println(t);
		}
		
		scan.close();
	}

}
