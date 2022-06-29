package com.revature.services;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.daos.UserDAO;
import com.revature.daos.UserPostgres;
import com.revature.models.User;
import com.revature.util.Table;
import com.revature.util.Util;

public class UserService {

	private UserDAO ud = new UserPostgres();
	private Logger log = LogManager.getLogger(UserService.class);

	public User createUser() {
		Table.title("Create New User");

		User u = new User();

		Util.println("Please fill in all fields");
		Util.print("Username: ");
		u.setUsername(Util.in.nextLine());
		Util.print("Password: ");
		u.setPassword(Util.in.nextLine());
		Util.print("First name: ");
		u.setFirstName(Util.in.nextLine());
		Util.print("Last name: ");
		u.setLastName(Util.in.nextLine());
		u.setLevel(1);

		u = ud.createUser(u);

		if (u.getId() == 0) {
			Util.println("User exists. Unable to create user.");
			return null;
		}

		Util.println("User created successfully.");
		return u;
	}

	public void modifyUser(User u) {

	}

	public User createEmployee() {
		Table.title("Create New Employee");

		User u = new User();

		Util.println("Please fill in all fields");
		Util.print("Username: ");
		u.setUsername(Util.in.nextLine());
		Util.print("Password: ");
		u.setPassword(Util.in.nextLine());
		Util.print("First name: ");
		u.setFirstName(Util.in.nextLine());
		Util.print("Last name: ");
		u.setLastName(Util.in.nextLine());
		u.setLevel(2);

		u = ud.createUser(u);

		if (u.getId() == 0) {
			Util.println("User exists. Unable to create user.");
			return null;
		}

		Util.println("User created successfully.");
		return u;
	}

	public void viewEmployees(User activeUser) {
		List<User> users = ud.getUsers();

		Table.title("Existing User Accounts");
		Table.header(String.format("%-12s    %-30.30s    %-8s", "Username", "Name", "Level"));
		for (User u : users) {
			int l = u.getLevel();
			if (l > 1) {
				Table.row(String.format("%-12s    %-30.30s    %-8s", u.getUsername(),
						u.getFirstName() + " " + u.getLastName(), l == 2 ? "Employee" : "Manager"));
			}
		}

		Util.println("Type 'new' to create new employee or 'delete [username]' to remove an employee.\nPress ENTER to return.");
		String choice = Util.in.nextLine();
		if (choice.equals(" ")) {

		} else if(choice.equalsIgnoreCase("new")) {
			this.createEmployee();
		} else {
			String[] args = choice.split(" ");
			if (args.length == 2 && args[0].equalsIgnoreCase("delete")) {
				boolean userFound = false;
				for (User u : users) {
					if (u.getUsername().equalsIgnoreCase(args[1])
							&& !u.getUsername().equals(activeUser.getUsername())) {
						this.removeUser(u);
						userFound = true;
						break;
					}
				}
				Util.println(
						userFound ? "User " + args[1] + " was deleted." : "Unable to delete user '" + args[1] + ".");
				this.viewUsers();
			} else {
				Util.invalid();
				this.viewUsers();
			}
		}

	}

	public void viewUsers() {
		List<User> users = ud.getUsers();

		Table.title("Existing User Accounts");
		Table.header(String.format("%-12s    %-30.30s    %-8s", "Username", "Name", "Level"));
		for (User u : users) {
			if (u.getLevel() == 1) {
				Table.row(String.format("%-12s    %-30.30s    %-8s", u.getUsername(),
						u.getFirstName() + " " + u.getLastName(), "Customer"));
			}
		}
	}

	public void removeUser(User u) {
		ud.deleteUserById(u.getId());
	}

	public List<User> getEmployees() {

		List<User> employees = ud.getUsers("> 1");
		return employees;

	}
}
