package com.revature;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
	private static Logger log = LogManager.getLogger(Driver.class);

	public static void main(String[] args) {
		/*
		 * Check if a user is logged in - if not - give option to log in or create
		 * account - else display menu based on user level - customer - view items -
		 * make offers - make payments - employee - view/accept/decline offers - view
		 * payments - add items - manager - employee options - create employee account -
		 * fire (delete) employee
		 */

		scan = new Scanner(System.in);
		as = new AuthService();
		us = new UserService();
		activeUser = new User();

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
				System.out.println("Invalid input.");

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

			System.out.println("Welcome to Project0!");
			System.out.println("Please select an option \n 1: Register\n 2: Sign in\n 3: Quit");
			String choice = scan.nextLine();
			switch (choice) {

			case "1":
				activeUser = us.createUser(scan);
				System.out.println(activeUser);
				break;

			case "2":
				activeUser = as.login(scan);
				System.out.println(activeUser);
				break;

			case "3":
				System.out.println("Goodbye!");
				scan.close();
				System.exit(0);

			default:
				System.out.println("Invalid input.");
			}
		}
	}

	private static void customerMenu() {
		while (activeUser.getLevel() == 1) {

			System.out.println("Hello, " + activeUser.getFirstName() + "!");
			System.out.println("Please select an option\n 1: Browse Games\n 2: My Games\n 3: Payments\n 4: Sign out");
			String choice = scan.nextLine();
			switch (choice) {

			case "1":
				
				break;

			case "2":
				
				break;

			case "3":

				break;

			case "4":
				signOut();
				break;

			default:

			}
		}
	}

	private static void employeeMenu() {
		while (activeUser == null) {

			System.out.println("Welcome to Project0!");
			System.out.println("Please select an option \n 1: Register\n 2: Sign in\n 3: Quit");
			String choice = scan.nextLine();
			switch (choice) {

			case "1":
				activeUser = us.createUser(scan);
				System.out.println(activeUser);
				break;

			case "2":
				activeUser = as.login(scan);
				System.out.println(activeUser);
				break;

			case "3":
				exit();

			default:
				System.out.println("Invalid input.");
			}
		}
	}

	private static void managerMenu() {
		while (activeUser == null) {

			System.out.println("Welcome to Project0!");
			System.out.println("Please select an option \n -1: Register\n -2: Sign in\n - 3: Quit");
			String choice = scan.nextLine();
			switch (choice) {

			case "1":
				activeUser = us.createUser(scan);
				System.out.println(activeUser);
				break;

			case "2":
				activeUser = as.login(scan);
				System.out.println(activeUser);
				break;

			case "3":
				exit();

			default:
				System.out.println("Invalid input.");
			}
		}
	}

	private static void signOut() {
		System.out.println("User " + activeUser.getUsername() + " signed out.");
		pause();
		activeUser = new User();
	}

	private static void pause() {
		System.out.print("Press \"ENTER\" to continue...");
		scan.nextLine();
		System.out.flush();
	}

	private static void exit() {
		System.out.println("Goodbye!");
		pause();
		scan.close();
		System.exit(0);
	}
}
