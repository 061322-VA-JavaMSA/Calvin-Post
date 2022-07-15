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

import com.revature.daos.TypeDAO;
import com.revature.exceptions.NotFoundException;
import com.revature.models.ReimbType;

@ExtendWith(MockitoExtension.class)
public class TypeServiceTest {

	@Mock
	private static TypeDAO mockTypeDao;

	@InjectMocks
	private static TypeService sut;

	@BeforeAll
	public static void setup() {
		sut = new TypeService();
	}

	@Test
	public void getTypeString() throws NotFoundException {
		String value = "FOOD";
		
		ReimbType tdaoExpected = new ReimbType();
		tdaoExpected.setId(1);
		tdaoExpected.setType("FOOD");
		
		ReimbType tservExpected = new ReimbType();
		tservExpected.setId(1);
		tservExpected.setType("FOOD");
		
		Mockito.when(mockTypeDao.retrieveTypeByValue(value)).thenReturn(tdaoExpected);

		ReimbType tservActual = sut.getType(value);

		assertEquals(tservExpected, tservActual);
	}
	
	@Test
	public void getTypeStringNotFound() {
		String value = "RANDOM";
		
		Mockito.when(mockTypeDao.retrieveTypeByValue(value)).thenReturn(null);
		
		assertThrows(NotFoundException.class, () -> sut.getType(value));
	}
	
	@Test
	public void getTypeInt() throws NotFoundException {
		int id = 1;
		
		ReimbType tdaoExpected = new ReimbType();
		tdaoExpected.setId(1);
		tdaoExpected.setType("FOOD");
		
		ReimbType tservExpected = new ReimbType();
		tservExpected.setId(1);
		tservExpected.setType("FOOD");
		
		Mockito.when(mockTypeDao.retrieveTypeById(id)).thenReturn(tdaoExpected);

		ReimbType tservActual = sut.getType(id);

		assertEquals(tservExpected, tservActual);
	}
	
	@Test
	public void getTypeIntNotFound() {
		int id = 5;
		
		Mockito.when(mockTypeDao.retrieveTypeById(id)).thenReturn(null);
		
		assertThrows(NotFoundException.class, () -> sut.getType(id));
	}
	
}
