package com.revature.services;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.revature.enums.Role;
import com.revature.exceptions.BadRequestException;
import com.revature.models.User;

public class UserServiceTest {
private static UserService sut;
	
	@BeforeAll
	public static void setUp() {
		sut = new UserService();
		System.out.println("Begin testing.");
	}
	
	@AfterAll
	public static void tearDown() {
		System.out.println("End of testing.");
	}
	
	@Test
	public void createUserExisting() {
		String user = "employee";
		String pass = "password";
		String first = "Employee";
		String last = "Smith";
		assertThrows(BadRequestException.class, () -> sut.createUser(user, pass, first, last));
	}
	
	@Test
	public void createEmployeeBob() {
		String user = "canhefixit";
		String pass = "yeshecan";
		String first = "Bob";
		String last = "Builder";
		assertTrue(sut.createEmployee(user, pass, first, last));
	}
	
	@Test
	public void getEmployeesAsUser() {
		List<User> expected = sut.getEmployees(Role.USER);
		assertNull(expected);
	}
	
	@Test
	public void getUsersAsManager() {
		List<User> expected = sut.getUsers(Role.MANAGER);
		assertNotNull(expected);
	}
	
	@ Test
	public void removeUserDoesntExist() {
		User expected = new User();
		assertFalse(sut.removeUser(expected));
	}

}
