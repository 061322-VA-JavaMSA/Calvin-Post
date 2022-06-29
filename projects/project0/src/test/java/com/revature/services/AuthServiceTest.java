package com.revature.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

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
		User expected = new User();
		expected.setFirstName("Employee");
		expected.setLastName("Smith");
		expected.setId(11);
		expected.setLevel(2);
		expected.setUsername("employee");
		expected.setPassword("password");
		User actual = sut.login();
		assertEquals(expected, actual);
	}
	
	@Test
	public void loginUnknownUser() {
		assertThrows(LoginException.class, () -> sut.login());
	}

}
