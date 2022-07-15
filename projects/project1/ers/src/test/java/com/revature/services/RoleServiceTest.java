package com.revature.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.revature.daos.RoleDAO;
import com.revature.exceptions.AuthException;
import com.revature.exceptions.NotFoundException;
import com.revature.models.Role;
import com.revature.models.User;

@ExtendWith(MockitoExtension.class)
public class RoleServiceTest {

	@Mock
	private static RoleDAO mockRoleDao;

	@InjectMocks
	private static RoleService sut;

	@BeforeAll
	public static void setup() {
		sut = new RoleService();
	}

	@Test
	public void getRoleString() throws NotFoundException {
		String value = "EMPLOYEE";
		
		Role rdaoExpected = new Role();
		rdaoExpected.setId(1);
		rdaoExpected.setRole("EMPLOYEE");
		
		Role rservExpected = new Role();
		rservExpected.setId(1);
		rservExpected.setRole("EMPLOYEE");
		
		Mockito.when(mockRoleDao.retrieveRoleByValue(value)).thenReturn(rdaoExpected);

		Role rservActual = sut.getRole(value);

		assertEquals(rservExpected, rservActual);
	}
	
	@Test
	public void getRoleStringNotFound() {
		String value = "ADMIN";
		
		Mockito.when(mockRoleDao.retrieveRoleByValue(value)).thenReturn(null);
		
		assertThrows(NotFoundException.class, () -> sut.getRole(value));
	}
	
	@Test
	public void getRoleInt() throws NotFoundException {
		int id = 1;
		
		Role rdaoExpected = new Role();
		rdaoExpected.setId(1);
		rdaoExpected.setRole("EMPLOYEE");
		
		Role rservExpected = new Role();
		rservExpected.setId(1);
		rservExpected.setRole("EMPLOYEE");
		
		Mockito.when(mockRoleDao.retrieveRoleById(id)).thenReturn(rdaoExpected);

		Role rservActual = sut.getRole(id);

		assertEquals(rservExpected, rservActual);
	}
	
	@Test
	public void getRoleIntNotFound() {
		int id = 4;
		
		Mockito.when(mockRoleDao.retrieveRoleById(id)).thenReturn(null);
		
		assertThrows(NotFoundException.class, () -> sut.getRole(id));
	}
	
}
