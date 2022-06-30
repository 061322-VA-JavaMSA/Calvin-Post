package com.revature;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.enums.Role;
import com.revature.exceptions.BadRequestException;
import com.revature.exceptions.LoginException;
import com.revature.models.Item;
import com.revature.models.Offer;
import com.revature.models.Payment;
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
	static User principal;
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
		principal = new User();

		is.updateNewItemStatus();

		while (true) {
			Util.println(principal.getRole());
			switch (principal.getRole()) {

			case GUEST:
				mainMenu();
				break;

			case USER:
				customerMenu();
				break;

			case EMPLOYEE:
				employeeMenu();
				break;

			case MANAGER:
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
		Util.println("Please select an option");
		Util.hr();
		Util.println(" 1: Register\n 2: Sign in\n 3: Quit");
		String choice = Util.in.nextLine();
		switch (choice) {

		case "1":
			register();
			break;

		case "2":
			login();
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
			viewItems();
			break;

		case "2":
			principalItems();
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
			viewItems();
			break;

		case "2":
			viewPayments();
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
				" 1: View Employees\n 2: View Customers\n 3: Browse Games\n 4: View Payments\n 5: View Offer History\n 6: Sign Out\n 7: Quit");
		String choice = Util.in.nextLine();
		switch (choice) {

		case "1":
			viewEmployees();
			break;

		case "2":
			viewUsers();
			break;

		case "3":
			viewItems();
			break;

		case "4":
			viewPayments();
			break;

		case "5":
			viewOffers();
			break;
			
		case "6":
			signOut();
			break;

		case "7":
			Util.exit();

		default:
			Util.invalid();
		}
	}

	private static void greeting() {
		Util.println("Hello, " + principal.getFirstName() + "! Please select an option.");
		Util.hr();
		Util.println();
	}

	private static void signOut() {
		log.info("User " + principal.getUsername() + " signed out.");
		Util.pause();
		principal = new User();
	}

	private static void register() {
		Table.title("Create New User");

		Util.println("Please fill in all fields");
		Util.hr();
		Util.println();
		Util.print("Username: ");
		String user = Util.in.nextLine();
		Util.print("Password: ");
		String pass = Util.in.nextLine();
		Util.print("First name: ");
		String first = Util.in.nextLine();
		Util.print("Last name: ");
		String last = Util.in.nextLine();

		try {
			principal = us.createUser(user, pass, first, last);
		} catch (BadRequestException e) {
			log.error("Unable to create user account.");
		}
	}

	private static void createEmployee() {
		Table.title("Create New User");

		Util.println("Please fill in all fields");
		Util.hr();
		Util.println();
		Util.print("Username: ");
		String user = Util.in.nextLine();
		Util.print("Password: ");
		String pass = Util.in.nextLine();
		Util.print("First name: ");
		String first = Util.in.nextLine();
		Util.print("Last name: ");
		String last = Util.in.nextLine();

		if (us.createEmployee(user, pass, first, last)) {

		}
	}

	private static void viewUsers() {
		List<User> users = us.getUsers(principal.getRole());
		if (!users.isEmpty()) {
			log.info("Successfully retrieved " + users.size() + " users.");
		}

		Table.title("Existing User Accounts");
		Table.header(String.format("%-12s    %-30.30s    %-8s", "Username", "Name", "Level"));
		for (User u : users) {
			Table.row(String.format("%-12s    %-30.30s    %-8s", u.getUsername(),
					u.getFirstName() + " " + u.getLastName(), "Customer"));
		}
	}

	private static void viewEmployees() {
		List<User> users = us.getEmployees(principal.getRole());
		if (!users.isEmpty()) {
			log.info("Successfully retrieved " + users.size() + " users.");
		}

		Table.title("Existing Employee Accounts");
		Table.header(String.format("%-12s    %-30.30s    %-8s", "Username", "Name", "Level"));
		for (User u : users) {

			Table.row(String.format("%-12s    %-30.30s    %-8s", u.getUsername(),
					u.getFirstName() + " " + u.getLastName(), "Employee"));
		}

		Util.hr();
		Util.println();
		Util.println(
				"Type 'new' to create new employee or 'delete [username]' to remove an employee.\nPress ENTER to return.");
		Util.hr();
		Util.println();
		String choice = Util.in.nextLine();
		if (choice.equals(" ")) {

		} else if (choice.equalsIgnoreCase("new")) {
			createEmployee();
		} else {
			String[] args = choice.split(" ");
			if (args.length == 2 && args[0].equalsIgnoreCase("delete")) {
				boolean userFound = false;
				for (User u : users) {
					if (u.getUsername().equalsIgnoreCase(args[1]) && !u.getUsername().equals(principal.getUsername())) {
						us.removeUser(u);
						userFound = true;
						break;
					}
				}
				Util.println(
						userFound ? "User " + args[1] + " was deleted." : "Unable to delete user '" + args[1] + ".");
				viewEmployees();
			} else {
				Util.invalid();
				viewEmployees();
			}
		}

	}

	private static void addItem() {
		Table.title("Add New Game");
		Item i = new Item();
		Util.print("Title: ");
		String name = Util.in.nextLine();
		Util.print("Description: ");
		String desc = Util.in.nextLine();

		i = is.addItem(name, desc);
		if (i != null) {
			Util.println(name + " was added.");
			Util.pause();
			i.setStatus("new");
			viewItem(i);
		} else {
			Util.println("Failed to add " + name);
		}
	}

	private static void viewItems() {
		boolean isEmployee = principal.getRole().greaterThan(Role.USER);
		List<Item> items = is.getItems(principal.getRole());
		int line = 1;
		Table.title(isEmployee ? "All Games" : "In Stock Games");

		Table.header(String.format(isEmployee ? "%3.3s    %-30.30s    %-9s    %-12s" : "%3.3s    %-30.30s    %-9s",
				"No.", "Title", "Status", isEmployee ? "Owner" : null));
		for (Item i : items) {
			Table.row(String.format(isEmployee ? "%3d    %-30.30s    %-9s    %-12s" : "%3d    %-30.30s    %-9s", line++,
					i.getName(), i.getStatus(),
					isEmployee ? i.getUser().getUsername() == null ? "" : i.getUser().getUsername() : null));
		}

		Util.hr();
		Util.print("Select a game to view or press ENTER to return.");
		if (isEmployee) {
			Util.print(" Enter 'add' to add a new game.");
		}
		Util.hr();
		String choice = Util.in.nextLine();

		if (!choice.isEmpty()) {
			if (isEmployee && choice.equals("add")) {
				addItem();
			} else if (Util.isInt(choice)) {
				int i = Integer.parseInt(choice) - 1;
				if (i > -1 && i < items.size()) {
					viewItem(items.get(i));
				} else {
					Util.invalid();
				}
			} else {
				Util.invalid();
			}
			viewItems();
		}

	}

	private static void viewItem(Item i) {
		Table.title(i.getName());
		if (i.getStatus().equals("owned")) {
			Table.header(String.format("%80s", "Owned by: " + i.getUser().getUsername()));
		}

		Util.println(i.getDescription());

		Util.println();
		Util.hr();
		Util.println();
		if (principal.getRole().greaterThan(Role.USER)) {
			// employee options
			Util.println("What would you like to do?");
			Util.hr();
			Util.println();
			Util.println(" 1. Update information\n 2. View offers\n 3. Remove this game\n 4. Go back");
			switch (Util.in.nextLine()) {

			case "1":
				editItem(i);
				viewItem(i);
				break;

			case "2":
				viewOffersForItem(i);

				break;
			case "3":
				if (i.getStatus().equals("owned")) {
					Util.println("Cannot delete an item that is already owned!");
					Util.pause();
					viewItem(i);
				} else {
					if (is.deleteItemById(i.getId())) {
						Util.println(i.getName() + " was removed.");
						Util.pause();
					} else {
						Util.println("Failed to remove " + i.getName());
						Util.pause();
						viewItem(i);
					}
				}

			case "4":
				break;

			default:
				Util.invalid();
				viewItem(i);

			}
		} else {
			submitOffer(i);
		}
	}

	private static void submitOffer(Item i) {
		Util.println("Enter an amount to offer or press ENTER to return.");
		Util.hr();
		Util.println();
		String input = Util.in.nextLine();
		if (!input.equals("")) {
			if (Util.isDouble(input)) {
				double d = Math.round(Double.parseDouble(input) * 100.0) / 100.0;
				os.createOffer(i, principal, d);
			} else {
				Util.invalid();
			}
			viewItem(i);
		}
	}

	private static void editItem(Item i) {
		Table.title("Editing information for '" + i.getName() + "'");
		Util.print("Title: ");
		String name = Util.in.nextLine();
		Util.println("Description:");
		String desc = Util.in.nextLine();
		if (is.updateItem(i.getId(), name, desc)) {
			i.setName(name);
			i.setDescription(desc);
			Util.println("Saved changes to " + name);
		} else {
			Util.println("Unable to edit " + i.getName());
		}
		viewItem(i);
	}

	private static void principalItems() {
		List<Item> items = is.getItemsByUserId(principal.getId());

		Table.title(principal.getFirstName() + "'s Games");
		int line = 1;
		for (Item i : items) {

			Util.println(" " + line + ". " + i.getName());

			line++;
		}

		Util.println();
		Util.hr();
		Util.println();
		Util.println("Select a game to view or press ENTER to return.");
		String choice = Util.in.nextLine();
		if (choice.equals("")) {

		} else if (Util.isInt(choice)) {
			int c = Integer.parseInt(choice) - 1;
			if (c > -1 && c < items.size()) {
				viewPaymentsByItem(items.get(c));
				principalItems();
			} else {
				Util.invalid();
				principalItems();
			}
		} else {
			Util.invalid();
			principalItems();
		}

	}

	private static void viewPayments() {
		List<Payment> payments = ps.getPayments(principal.getRole());
		if (!payments.isEmpty()) {
			log.info("Successfully retrieved " + payments.size() + " payments.");
		}

		Table.title("View All Payments");

		Table.header(String.format("%-10s    %-8s    %10s    %10s    %-12.12s", "Due Date", "Status", "Amount Due",
				"Received", "User"));
		for (Payment p : payments) {
			Table.row(String.format("%-10s    %-8s          $%7.2f       $%7.2f    %-12.12s", p.getDateDue().toString(),
					p.getStatus(), p.getAmountDue(), p.getAmountReceived(), p.getItem().getUser().getUsername()));
		}
		Util.hr();
		Util.pause();
	}

	private static void viewPaymentsByItem(Item i) {
		i.setUser(principal);
		List<Payment> payments = ps.getPaymentsByItem(i);
		double bal = i.getBalance();
		double pay;
		if (!payments.isEmpty()) {
			log.info("Successfully retrieved " + payments.size() + " payments.");
		}

		Table.title("Payments for " + i.getName());
		if (bal > 0) {
			Table.row(String.format("%36s      $%7.2f", "Remaining balance:", bal));
			Util.hr();

			Table.header(
					String.format("%-10s    %-8s    %10s    %10s", "Due Date", "Status", "Amount Due", "Received"));
			for (Payment p : payments) {
				if (!p.getStatus().equals("paid")) {
					Table.row(String.format("%-10s    %-8s      $%7.2f      $%7.2f", p.getDateDue().toString(),
							p.getStatus(), p.getAmountDue(), p.getAmountReceived()));
				}
			}
			Util.hr();
			Util.println();
			Util.println("Enter an amount to make a payment. Press ENTER to return.");
			Util.hr();
			String choice = Util.in.nextLine();
			if (choice.equals("")) {

			} else if (Util.isDouble(choice)) {
				pay = Math.floor(Double.parseDouble(choice) * 100.0) / 100.0;
				log.info("User " + i.getUser().getUsername() + " made a payment of $" + pay + " on '" + i.getName()
						+ "'.");
				for (Payment p : payments) {
					if (!p.getStatus().equals("paid")) {
						double rem = p.getAmountDue() - p.getAmountReceived();
						if (pay > rem) {
							pay -= rem;
							bal -= rem;
							p.setStatus("paid");
							p.setAmountReceived(p.getAmountDue());
							ps.updatePayment(p);
							log.info("Payment id=" + p.getId() + " was updated.");
						} else {
							p.setAmountReceived(p.getAmountReceived() + pay);
							bal -= pay;
							pay = 0;
							p.setStatus("partial");
							ps.updatePayment(p);
							log.info("Payment id=" + p.getId() + " was updated.");
							break;
						}
					}
				}
				i.setBalance(bal);
				is.updateItemBalance(i);

				paymentMessage(i, payments, pay);
				viewPaymentsByItem(i);
			} else {
				Util.invalid();
				viewPaymentsByItem(i);
			}
		} else {
			Table.row(String.format("%46s", "Paid in full"));
			Util.hr();
			Util.pause();
		}
	}

	private static void paymentMessage(Item i, List<Payment> payments, double refund) {
		double bal = i.getBalance();
		Table.title("Payments for " + i.getName());
		if (bal > 0) {
			Table.row(String.format("%36s      $%7.2f", "Remaining balance:", bal));
		} else {
			Table.row(String.format("%46s", "Paid in full"));
		}
		Util.hr();

		Table.header(String.format("%-10s    %-8s    %10s    %10s", "Due Date", "Status", "Amount Due", "Received"));
		for (Payment p : payments) {
			Table.row(String.format("%-10s    %-8s      $%7.2f      $%7.2f", p.getDateDue().toString(), p.getStatus(),
					p.getAmountDue(), p.getAmountReceived()));
		}
		Util.hr();
		Util.println();
		if (refund > 0) {
			Util.println(String.format("Refunded $%5.2f", refund));
			log.info("User " + i.getUser().getUsername() + " was refunded $" + refund);
		}
		Util.pause();
	}

	private static void login() {
		Table.title("Calvin's Game Shop");

		Util.print("Username: ");
		String user = Util.in.nextLine();
		Util.print("Password: ");
		String pass = Util.in.nextLine();

		try {
			principal = as.login(user, pass);
		} catch (LoginException e) {
			// TODO Auto-generated catch block
			log.error("Unable to sign in.");
		}
	}

	private static void viewOffersForItem(Item i) {
		List<Offer> offers = os.getOffersByItem(i);
		Table.title("Offers for '" + i.getName() + "'");
		Table.header(String.format("%4s    %-10s    %-10s    %-20s    %6s", "No.", "Date", "Status", "User", "Amount"));

		int line = 1;

		for (Offer o : offers) {
			Table.row(String.format("%4d    %-10s    %-10s    %-20s  $ %6.2f", line++, o.getDate(), o.getStatus(),
					o.getUser().getUsername(), o.getAmount()));
		}
		Util.hr();
		Util.println();
		if (!i.getStatus().equals("owned")) {
			Util.println("Select an offer and specify 'a' to accept or 'r' to reject. (ex: 2 a)");
			Util.println("Press ENTER to return and commit changes.");
			Util.hr();
			String selection = Util.in.nextLine();
			if (selection.equals("")) {
				for (Offer o : offers) {
					if (o.getStatus().equals("accepted")) {
						is.updateItemStatus(o);
						i.setStatus("owned");
						i.setBalance(o.getAmount());
						ps.createPayments(i);
					}
				}
			} else {
				String[] args = selection.split(" ");
				if (args.length == 2) {
					if (Util.isInt(args[0])) {
						int x = Integer.parseInt(args[0]) - 1;
						if (x > -1 && x < offers.size()) {
							Offer o = offers.get(x);

							if (args[1].equalsIgnoreCase("a")) {
								i.setBalance(o.getAmount());
								offers = os.acceptOffer(o);
								viewOffersForItem(i);
							} else if (args[1].equalsIgnoreCase("r")) {
								os.rejectOffer(o);
								viewOffersForItem(i);
							} else {
								Util.invalid();
								viewOffersForItem(i);
							}
						} else {
							Util.invalid();
							viewOffersForItem(i);
						}

					}
				} else {
					Util.invalid();
					viewOffersForItem(i);
				}
			}
		} else {
			Util.pause();
		}
	}

	private static void viewOffers() {
		List<Offer> offers = os.getOffers();
		Table.title("Offer History");
		Table.header(String.format("%-10s    %-10s    %-20s    %6s", "Date", "Status", "User", "Amount"));

		for (Offer o : offers) {
			Table.row(String.format("%-10s    %-10s    %-20s  $ %6.2f", o.getDate(), o.getStatus(),
					o.getUser().getUsername(), o.getAmount()));
		}
		Util.hr();
		Util.println();
		Util.pause();
	}
}
