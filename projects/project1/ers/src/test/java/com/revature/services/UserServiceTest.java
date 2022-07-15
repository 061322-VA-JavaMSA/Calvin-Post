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
import com.revature.exceptions.NotCreatedException;
import com.revature.exceptions.NotFoundException;
import com.revature.models.Role;
import com.revature.models.User;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

	@Mock
	private static UserDAO mockUserDao;

	@InjectMocks
	private static UserService sut;

	@BeforeAll
	public static void setup() {
		sut = new UserService();
	}

	@Test
	public void getUserByIdExists() throws NotFoundException {
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

		User uservExpected = new User();
		uservExpected.setId(1);
		uservExpected.setUsername("calpost");
		uservExpected.setPassword("mypass");
		uservExpected.setFirstName("Calvin");
		uservExpected.setLastName("Post");
		uservExpected.setRole(r);
		uservExpected.setEmail("calvinhenry826@revature.net");

		Mockito.when(mockUserDao.retrieveUserById(1)).thenReturn(udaoExpected);

		User uservActual = sut.getUserById(1);

		assertEquals(uservExpected, uservActual);
	}

	@Test
	public void getUserByIdDoesNotExist() {

		Mockito.when(mockUserDao.retrieveUserById(8)).thenReturn(null);

		assertThrows(NotFoundException.class, () -> sut.getUserById(8));
	}

	@Test
	public void createUserSuccess() throws NotCreatedException {
		Role r = new Role();
		r.setId(1);
		r.setRole("EMPLOYEE");
		User udaoExpected = new User();
		udaoExpected.setId(3);
		udaoExpected.setUsername("jdoe");
		udaoExpected.setPassword("mypass");
		udaoExpected.setFirstName("John");
		udaoExpected.setLastName("Doe");
		udaoExpected.setRole(r);
		udaoExpected.setEmail("jdoe@somedomain.com");

		User uToCreate = new User();
		uToCreate.setUsername("jdoe");
		uToCreate.setPassword("mypass");
		uToCreate.setFirstName("John");
		uToCreate.setLastName("Doe");
		uToCreate.setRole(r);
		uToCreate.setEmail("jdoe@somedomain.com");

		User uservExpected = new User();
		uservExpected.setId(3);
		uservExpected.setUsername("jdoe");
		uservExpected.setPassword("mypass");
		uservExpected.setFirstName("John");
		uservExpected.setLastName("Doe");
		uservExpected.setRole(r);
		uservExpected.setEmail("jdoe@somedomain.com");

		Mockito.when(mockUserDao.insertUser(uToCreate)).thenReturn(udaoExpected);

		User uservActual = sut.createUser(uToCreate);

		assertEquals(uservExpected, uservActual);
	}

	@Test
	public void createUserExists() {
		Role r = new Role();
		r.setId(2);
		r.setRole("MANAGER");
		User uToCreate = new User();
		uToCreate.setUsername("calpost");
		uToCreate.setPassword("mypass");
		uToCreate.setFirstName("Calvin");
		uToCreate.setLastName("Post");
		uToCreate.setRole(r);
		uToCreate.setEmail("calvinhenry826@revature.net");

		Mockito.when(mockUserDao.insertUser(uToCreate)).thenReturn(uToCreate);

		assertThrows(NotCreatedException.class, () -> sut.createUser(uToCreate));
	}
	
	@Test
	public void updateUserSuccess() throws NotCreatedException {
		Role r = new Role();
		r.setId(1);
		r.setRole("EMPLOYEE");
		User udaoExpected = new User();
		udaoExpected.setId(3);
		udaoExpected.setUsername("jdoe");
		udaoExpected.setPassword("mypass");
		udaoExpected.setFirstName("John");
		udaoExpected.setLastName("Doe");
		udaoExpected.setRole(r);
		udaoExpected.setEmail("jdoe@somedomain.com");

		User uToUpdate = new User();
		uToUpdate.setId(3);
		uToUpdate.setUsername("jdoe");
		uToUpdate.setPassword("mypass");
		uToUpdate.setFirstName("John");
		uToUpdate.setLastName("Doe");
		uToUpdate.setRole(r);
		uToUpdate.setEmail("jdoe@somedomain.com");

		User uservExpected = new User();
		uservExpected.setId(3);
		uservExpected.setUsername("jdoe");
		uservExpected.setPassword("mypass");
		uservExpected.setFirstName("John");
		uservExpected.setLastName("Doe");
		uservExpected.setRole(r);
		uservExpected.setEmail("jdoe@somedomain.com");

		Mockito.when(mockUserDao.updateUser(uToUpdate)).thenReturn(udaoExpected);

		User uservActual = sut.updateUser(uToUpdate);

		assertEquals(uservExpected, uservActual);
	}
	
	@Test
	public void getUsersEmpty() {
		List<User> udaoExpected = new ArrayList<>();
		Mockito.when(mockUserDao.retrieveUsers()).thenReturn(udaoExpected);

		assertThrows(NotFoundException.class, () -> sut.getUsers());
	}
	
	@Test
	public void getUsersNull() {
		Mockito.when(mockUserDao.retrieveUsers()).thenReturn(null);

		assertThrows(NotFoundException.class, () -> sut.getUsers());
	}
	
	@Test
	public void getUsersByRoleEmpty() {
		List<User> udaoExpected = new ArrayList<>();
		Role r = new Role();
		r.setId(3);
		r.setRole("ADMIN");
		Mockito.when(mockUserDao.retrieveUsersByRole(r)).thenReturn(udaoExpected);

		assertThrows(NotFoundException.class, () -> sut.getUsersByRole(r));
	}
	
	@Test
	public void getUsersByRoleSuccess() throws NotFoundException {
		List<User> udaoExpected = new ArrayList<>();
		List<User> uservExpected = new ArrayList<>();
		Role r = new Role();
		r.setId(1);
		r.setRole("EMPLOYEE");
		User one = new User();
		one.setId(3);
		one.setUsername("jdoe");
		one.setPassword("mypass");
		one.setFirstName("John");
		one.setLastName("Doe");
		one.setRole(r);
		one.setEmail("jdoe@somedomain.com");
		User two = new User();
		two.setId(5);
		two.setUsername("jsmith");
		two.setPassword("mypass");
		two.setFirstName("Jane");
		two.setLastName("Smith");
		two.setRole(r);
		two.setEmail("jsmith@somedomain.com");
		
		udaoExpected.add(one);
		udaoExpected.add(two);
		uservExpected.add(one);
		uservExpected.add(two);
		
		Mockito.when(mockUserDao.retrieveUsersByRole(r)).thenReturn(udaoExpected);

		List<User> uservActual = sut.getUsersByRole(r);
		assertEquals(uservExpected, uservActual);
	}
	
	
	
}
