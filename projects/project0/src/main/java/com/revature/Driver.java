package com.revature;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.models.User;
import com.revature.services.AuthService;
import com.revature.services.ItemService;
import com.revature.services.OfferService;
import com.revature.services.PaymentService;
import com.revature.services.UserService;
import com.revature.util.Table;
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
		ps = new PaymentService();
		activeUser = new User();

		is.updateNewItemStatus();

		while (!activeUser.equals(null)) {
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
		}
	}

	private static void mainMenu() {
		Table.title("Calvin's Game Shop");
		Util.println("Welcome to the Game Shop!");
		Util.println("Please select an option \n 1: Register\n 2: Sign in\n 3: Quit");
		String choice = Util.in.nextLine();
		switch (choice) {

		case "1":
			activeUser = us.createUser();
			break;

		case "2":
			activeUser = as.login();
			break;

		case "3":
			Util.exit();

		default:
			Util.println("Invalid input.");
		}
	}

	private static void customerMenu() {
		Table.title("Customer Menu");
		greeting();
		Util.println(" 1: Browse Games\n 2: Owned Games\n 3: Sign out\n 4: Quit");
		String choice = Util.in.nextLine();
		switch (choice) {

		case "1":
			is.viewItems(activeUser);
			break;

		case "2":
			is.viewOwnedItems(activeUser);
			break;

		case "3":
			signOut();
			break;

		case "4":
			Util.exit();

		default:
			Util.invalid();
		}
	}

	private static void employeeMenu() {
		Table.title("Employee Menu");
		greeting();
		Util.println(" 1: Browse Games / Offers\n 2: View Payments\n 3: Sign Out\n 4: Quit");
		String choice = Util.in.nextLine();
		switch (choice) {

		case "1":
			is.viewItems(activeUser);
			break;

		case "2":
			ps.viewPayments();
			break;

		case "3":
			signOut();
			break;

		case "4":
			Util.exit();

		default:
			Util.invalid();
		}
	}

	private static void managerMenu() {
		Table.title("Manager Menu");
		greeting();
		Util.println(
				" 1: View Employees\n 2: View Customers\n 3: Browse Games / Offers\n 4: View Payments\n 5: Sign Out\n 6: Quit");
		String choice = Util.in.nextLine();
		switch (choice) {

		case "1":
			us.viewEmployees(activeUser);
			break;

		case "2":
			us.viewUsers();
			break;
			
		case "3":
			is.viewItems(activeUser);
			break;

		case "4":
			ps.viewPayments();
			break;

		case "5":
			signOut();
			break;

		case "6":
			Util.exit();

		default:
			Util.invalid();
		}
	}

	private static void greeting() {
		Util.println("Hello, " + activeUser.getFirstName() + "! Please select an option.");
	}

	private static void signOut() {
		Util.println("User " + activeUser.getUsername() + " signed out.");
		Util.pause();
		activeUser = new User();
	}

}
