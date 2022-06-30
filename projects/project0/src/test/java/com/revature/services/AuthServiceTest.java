package com.revature.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.revature.enums.Role;
import com.revature.exceptions.LoginException;
import com.revature.models.User;

public class AuthServiceTest {

	private static AuthService sut;
	
	@BeforeAll
	public static void setUp() {
		sut = new AuthService();
		System.out.println("Begin testing.");
	}
	
	@AfterAll
	public static void tearDown() {
		System.out.println("End of testing.");
	}
	
	@Test
	public void loginEmployee() {
		String user = "employee";
		String pass = "password";
		User expected = new User();
		expected.setFirstName("Employee");
		expected.setLastName("Smith");
		expected.setId(11);
		expected.setRole(Role.EMPLOYEE);
		expected.setUsername("employee");
		expected.setPassword("password");
		User actual = new User();
		try {
			actual = sut.login(user, pass);
		} catch (LoginException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(expected, actual);
	}
	
	@Test
	public void loginUnknownUser() {
		String user = "nosuchuser";
		String pass = "nosuchpass";
		assertThrows(LoginException.class, () -> sut.login(user, pass));
	}

}
