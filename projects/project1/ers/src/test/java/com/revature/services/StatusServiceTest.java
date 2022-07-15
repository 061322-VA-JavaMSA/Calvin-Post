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

import com.revature.daos.StatusDAO;
import com.revature.exceptions.NotFoundException;
import com.revature.models.ReimbStatus;

@ExtendWith(MockitoExtension.class)
public class StatusServiceTest {

	@Mock
	private static StatusDAO mockStatusDao;

	@InjectMocks
	private static StatusService sut;

	@BeforeAll
	public static void setup() {
		sut = new StatusService();
	}

	@Test
	public void getStatusString() throws NotFoundException {
		String value = "FOOD";
		
		ReimbStatus sdaoExpected = new ReimbStatus();
		sdaoExpected.setId(1);
		sdaoExpected.setStatus("FOOD");
		
		ReimbStatus sservExpected = new ReimbStatus();
		sservExpected.setId(1);
		sservExpected.setStatus("FOOD");
		
		Mockito.when(mockStatusDao.retrieveStatusByValue(value)).thenReturn(sdaoExpected);

		ReimbStatus sservActual = sut.getStatus(value);

		assertEquals(sservExpected, sservActual);
	}
	
	@Test
	public void getStatusStringNotFound() {
		String value = "RANDOM";
		
		Mockito.when(mockStatusDao.retrieveStatusByValue(value)).thenReturn(null);
		
		assertThrows(NotFoundException.class, () -> sut.getStatus(value));
	}
	
	@Test
	public void getStatusInt() throws NotFoundException {
		int id = 1;
		
		ReimbStatus sdaoExpected = new ReimbStatus();
		sdaoExpected.setId(1);
		sdaoExpected.setStatus("FOOD");
		
		ReimbStatus sservExpected = new ReimbStatus();
		sservExpected.setId(1);
		sservExpected.setStatus("FOOD");
		
		Mockito.when(mockStatusDao.retrieveStatusById(id)).thenReturn(sdaoExpected);

		ReimbStatus sservActual = sut.getStatus(id);

		assertEquals(sservExpected, sservActual);
	}
	
	@Test
	public void getStatusIntNotFound() {
		int id = 5;
		
		Mockito.when(mockStatusDao.retrieveStatusById(id)).thenReturn(null);
		
		assertThrows(NotFoundException.class, () -> sut.getStatus(id));
	}
	
}
