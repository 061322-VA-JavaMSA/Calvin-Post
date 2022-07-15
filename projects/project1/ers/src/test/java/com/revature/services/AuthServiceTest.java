package com.revature.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.revature.daos.UserDAO;
import com.revature.exceptions.AuthException;
import com.revature.exceptions.NotCreatedException;
import com.revature.exceptions.NotFoundException;
import com.revature.models.Role;
import com.revature.models.User;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {

	@Mock
	private static UserDAO mockUserDao;

	@InjectMocks
	private static AuthService sut;

	@BeforeAll
	public static void setup() {
		sut = new AuthService();
	}

	@Test
	public void loginUsername() throws NotFoundException, AuthException {
		String username = "calpost";
		String password = "mypass";
		
		Role r = new Role();
		r.setId(2);
		r.setRole("MANAGER");
		
		User udaoExpected = new User();
		udaoExpected.setId(1);
		udaoExpected.setUsername("calpost");
		udaoExpected.setPassword("mypass");
		udaoExpected.setFirstName("Calvin");
		udaoExpected.setLastName("Post");
		udaoExpected.setRole(r);
		udaoExpected.setEmail("calvinhenry826@revature.net");
		
		User aservExpected = new User();
		aservExpected.setId(1);
		aservExpected.setUsername("calpost");
		aservExpected.setPassword("mypass");
		aservExpected.setFirstName("Calvin");
		aservExpected.setLastName("Post");
		aservExpected.setRole(r);
		aservExpected.setEmail("calvinhenry826@revature.net");

		Mockito.when(mockUserDao.retrieveUserByUsername(username)).thenReturn(udaoExpected);

		User aservActual = sut.login(username, password);

		assertEquals(aservExpected, aservActual);
	}
	
	@Test
	public void loginEmail() throws NotFoundException, AuthException {
		String email = "calvinhenry826@revature.net";
		String password = "mypass";
		
		Role r = new Role();
		r.setId(2);
		r.setRole("MANAGER");
		
		User udaoExpected = new User();
		udaoExpected.setId(1);
		udaoExpected.setUsername("calpost");
		udaoExpected.setPassword("mypass");
		udaoExpected.setFirstName("Calvin");
		udaoExpected.setLastName("Post");
		udaoExpected.setRole(r);
		udaoExpected.setEmail("calvinhenry826@revature.net");
		
		User aservExpected = new User();
		aservExpected.setId(1);
		aservExpected.setUsername("calpost");
		aservExpected.setPassword("mypass");
		aservExpected.setFirstName("Calvin");
		aservExpected.setLastName("Post");
		aservExpected.setRole(r);
		aservExpected.setEmail("calvinhenry826@revature.net");

		Mockito.when(mockUserDao.retrieveUserByEmail(email)).thenReturn(udaoExpected);

		User aservActual = sut.login(email, password);

		assertEquals(aservExpected, aservActual);
	}
	
	@Test
	public void loginPasswordMismatch() throws NotFoundException, AuthException {
		String username = "calpost";
		String password = "wrongpass";
		
		Role r = new Role();
		r.setId(2);
		r.setRole("MANAGER");
		
		User udaoExpected = new User();
		udaoExpected.setId(1);
		udaoExpected.setUsername("calpost");
		udaoExpected.setPassword("mypass");
		udaoExpected.setFirstName("Calvin");
		udaoExpected.setLastName("Post");
		udaoExpected.setRole(r);
		udaoExpected.setEmail("calvinhenry826@revature.net");

		Mockito.when(mockUserDao.retrieveUserByUsername(username)).thenReturn(udaoExpected);

		assertThrows(AuthException.class, () -> sut.login(username, password));
	}

}
