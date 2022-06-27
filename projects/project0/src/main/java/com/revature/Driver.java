package com.revature;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.models.User;
import com.revature.services.AuthService;
import com.revature.services.ItemService;
import com.revature.services.OfferService;
import com.revature.services.PaymentService;
import com.revature.services.UserService;
import com.revature.util.Util;

public class Driver {

	static AuthService as;
	static UserService us;
	static ItemService is;
	static OfferService os;
	static PaymentService ps;
	static User activeUser;
	private static Logger log = LogManager.getLogger(Driver.class);

	public static void main(String[] args) {
		/*
		 * Check if a user is logged in - if not - give option to log in or create
		 * account - else display menu based on user level - customer - view items -
		 * make offers - make payments - employee - view/accept/decline offers - view
		 * payments - add items - manager - employee options - create employee account -
		 * fire (delete) employee
		 */

		as = new AuthService();
		us = new UserService();
		is = new ItemService();
		os = new OfferService();
		activeUser = new User();
		
		is.updateNewItemStatus();

		while (true) {
			switch (activeUser.getLevel()) {

			// Unregistered user
			case 0:
				mainMenu();
				break;

			// Customer menu
			case 1:
				customerMenu();
				break;

			// Employee menu
			case 2:
				employeeMenu();
				break;

			// Manager menu
			case 3:
				managerMenu();
				break;

			default:
				Util.println("Invalid input.");

			}

			/*
			 * 1st: - Register user - Create a new Account - Gather Name, Username, Password
			 * - Check if username already exists (Service layer) - more validation - logic
			 * to persist to a database - feedback of if operation was successful -
			 * Cancel/Go back - Login
			 */

			/*
			 * syso - 1 for register, 2 for login choice = sc.nextLn if/switch(choice){ 1 -
			 * User u = new User() syso Name setter sc.nextLn syso username setter sc.nextLn
			 * syso password setter sc.nextLn // check db if username already exists - call
			 * to database for a username and see if it returns anything // persist to db
			 * logic
			 * 
			 * }
			 */
		}
	}

	private static void mainMenu() {
		while (activeUser.getLevel() == 0) {
			Util.clear();
			Util.println("Welcome to Project0!");
			Util.println("Please select an option \n 1: Register\n 2: Sign in\n 3: Quit");
			String choice = Util.in.nextLine();
			switch (choice) {

			case "1":
				activeUser = us.createUser();
				Util.println(activeUser);
				break;

			case "2":
				activeUser = as.login();
				Util.println(activeUser);
				break;

			case "3":
				Util.exit();

			default:
				Util.println("Invalid input.");
			}
		}
	}

	private static void customerMenu() {
		while (activeUser.getLevel() == 1) {
			greeting();
			Util.println(" 1: Browse Games\n 2: Owned Games\n 3: Payments\n 4: Sign out");
			String choice = Util.in.nextLine();
			switch (choice) {

			case "1":
				is.viewItems(activeUser);;
				break;

			case "2":
				is.viewOwnedItems(activeUser);
				break;

			case "3":

				break;

			case "4":
				signOut();
				break;

			default:
				Util.invalid();
			}
		}
	}

	private static void employeeMenu() {
		while (activeUser.getLevel() == 2) {
			greeting();
			Util.println(" 1: Browse Games\n 2: Sign in\n 3: Quit");
			String choice = Util.in.nextLine();
			switch (choice) {

			case "1":
				is.viewItems(activeUser);
				break;

			case "2":
				activeUser = as.login();
				Util.println(activeUser);
				break;

			case "3":
				Util.exit();

			default:
				Util.invalid();
			}
		}
	}

	private static void managerMenu() {
		while (activeUser == null) {
			greeting();
			Util.println(" -1: Register\n -2: Sign in\n - 3: Quit");
			String choice = Util.in.nextLine();
			switch (choice) {

			case "1":
				activeUser = us.createUser();
				Util.println(activeUser);
				break;

			case "2":
				activeUser = as.login();
				Util.println(activeUser);
				break;

			case "3":
				Util.exit();

			default:
				Util.invalid();
			}
		}
	}
	
	private static void greeting() {
		Util.println("Please select an option");
	}

	private static void signOut() {
		Util.println("User " + activeUser.getUsername() + " signed out.");
		Util.pause();
		activeUser = new User();
	}
	
}
