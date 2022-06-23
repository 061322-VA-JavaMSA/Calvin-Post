package com.revature;

import java.util.Scanner;

import com.revature.models.User;
import com.revature.services.AuthService;
import com.revature.services.ItemService;
import com.revature.services.OfferService;
import com.revature.services.PaymentService;
import com.revature.services.UserService;

public class Driver {
	
	static Scanner scan;
	static AuthService as;
	static UserService us;
	static ItemService is;
	static OfferService os;
	static PaymentService ps;
	static User activeUser;

	public static void main(String[] args) {
		/*
		 * Check if a user is logged in
		 * 	- if not
		 * 		- give option to log in or create account
		 *  - else display menu based on user level
		 *  	- customer
		 *  		- view items
		 *  		- make offers
		 *  		- make payments
		 *  	- employee
		 *  		- view/accept/decline offers
		 *  		- view payments
		 *  		- add items
		 *  	- manager
		 *  		- employee options
		 *  		- create employee account
		 *  		- fire (delete) employee
		 */
		scan = new Scanner(System.in);
		as = new AuthService();
		String username = null;
		String password = null;
		
		System.out.println("");
		System.out.print("Please enter username: ");
		/*  1st:
		 * - Register user
		 * 	- Create a new Account
		 * 		- Gather Name, Username, Password
		 * 			- Check if username already exists (Service layer)
		 * 			- more validation
		 * 			- logic to persist to a database
		 * 			- feedback of if operation was successful
		 * 	- Cancel/Go back
		 * - Login
		 */
		System.out.println("Welcome to Project0!");
		System.out.println("Please select an option \n -1: Register\n -2: Sign in");
		String choice = scan.nextLine();
		switch(choice) {
		
		case "1":
			activeUser = as.createUser(scan);
			System.out.println(activeUser);
			break;
			
		case "2":
			
		default:
		}
		/*
		 * syso - 1 for register, 2 for login
		 * choice = sc.nextLn
		 * if/switch(choice){
		 * 		1 -
		 * 			User u = new User()
		 * 			syso Name
		 * 			setter sc.nextLn
		 * 			syso username
		 * 			setter sc.nextLn
		 * 			syso password
		 * 			setter sc.nextLn
		 * 			// check db if username already exists
		 * 				- call to database for a username and see if it returns anything
		 * 			// persist to db logic
		 * 
		 * }
		 */
		
		scan.close();
	}

}
