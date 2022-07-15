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

import com.revature.daos.ReimbursementDAO;
import com.revature.exceptions.NotCreatedException;
import com.revature.exceptions.NotFoundException;
import com.revature.models.ReimbStatus;
import com.revature.models.ReimbType;
import com.revature.models.Reimbursement;
import com.revature.models.Role;
import com.revature.models.User;

@ExtendWith(MockitoExtension.class)
public class ReimbursementServiceTest {

	@Mock
	private static ReimbursementDAO mockReimbursementDao;

	@InjectMocks
	private static ReimbursementService sut;

	@BeforeAll
	public static void setup() {
		sut = new ReimbursementService();
	}

	@Test
	public void getReimbursementByIdExists() throws NotFoundException {
		ReimbType rt = new ReimbType();
		rt.setId(1);
		rt.setType("FOOD");
		ReimbStatus rs = new ReimbStatus();
		rs.setId(1);
		rs.setStatus("PENDING");
		
		Reimbursement rdaoExpected = new Reimbursement();
		rdaoExpected.setId(3);
		rdaoExpected.setAmount(10.95);
		rdaoExpected.setDescription("Some description");
		rdaoExpected.setReimbType(rt);
		rdaoExpected.setReimbStatus(rs);

		Reimbursement rservExpected = new Reimbursement();
		rservExpected.setId(3);
		rservExpected.setAmount(10.95);
		rservExpected.setDescription("Some description");
		rservExpected.setReimbType(rt);
		rservExpected.setReimbStatus(rs);

		Mockito.when(mockReimbursementDao.retrieveReimbursementById(1)).thenReturn(rdaoExpected);

		Reimbursement rservActual = sut.getReimbursementById(1);

		assertEquals(rservExpected, rservActual);
	}

	@Test
	public void getReimbursementByIdDoesNotExist() {

		Mockito.when(mockReimbursementDao.retrieveReimbursementById(8)).thenReturn(null);

		assertThrows(NotFoundException.class, () -> sut.getReimbursementById(8));
	}

	@Test
	public void createReimbursementSuccess() throws NotCreatedException {
		ReimbType rt = new ReimbType();
		rt.setId(1);
		rt.setType("FOOD");
		ReimbStatus rs = new ReimbStatus();
		rs.setId(1);
		rs.setStatus("PENDING");
		
		Reimbursement rdaoExpected = new Reimbursement();
		rdaoExpected.setId(3);
		rdaoExpected.setAmount(10.95);
		rdaoExpected.setDescription("Some description");
		rdaoExpected.setReimbType(rt);
		rdaoExpected.setReimbStatus(rs);

		Reimbursement rToCreate = new Reimbursement();
		rToCreate.setAmount(10.95);
		rToCreate.setDescription("Some description");
		rToCreate.setReimbType(rt);
		rToCreate.setReimbStatus(rs);

		Reimbursement rservExpected = new Reimbursement();
		rservExpected.setId(3);
		rservExpected.setAmount(10.95);
		rservExpected.setDescription("Some description");
		rservExpected.setReimbType(rt);
		rservExpected.setReimbStatus(rs);

		Mockito.when(mockReimbursementDao.insertReimbursement(rToCreate)).thenReturn(rdaoExpected);

		Reimbursement rservActual = sut.createReimbursement(rToCreate);

		assertEquals(rservExpected, rservActual);
	}

	@Test
	public void createReimbursementExists() {
		ReimbType rt = new ReimbType();
		rt.setId(1);
		rt.setType("FOOD");
		ReimbStatus rs = new ReimbStatus();
		rs.setId(1);
		rs.setStatus("PENDING");
		
		Reimbursement rToCreate = new Reimbursement();
		rToCreate.setAmount(10.95);
		rToCreate.setDescription("Some description");
		rToCreate.setReimbType(rt);
		rToCreate.setReimbStatus(rs);

		Mockito.when(mockReimbursementDao.insertReimbursement(rToCreate)).thenReturn(rToCreate);

		assertThrows(NotCreatedException.class, () -> sut.createReimbursement(rToCreate));
	}

	@Test
	public void updateReimbursementSuccess() throws NotCreatedException {
		ReimbType rt = new ReimbType();
		rt.setId(1);
		rt.setType("FOOD");
		ReimbStatus rs = new ReimbStatus();
		rs.setId(1);
		rs.setStatus("PENDING");
		
		Reimbursement rdaoExpected = new Reimbursement();
		rdaoExpected.setId(3);
		rdaoExpected.setAmount(10.95);
		rdaoExpected.setDescription("Some description");
		rdaoExpected.setReimbType(rt);
		rdaoExpected.setReimbStatus(rs);

		Reimbursement rToUpdate = new Reimbursement();
		rToUpdate.setAmount(10.95);
		rToUpdate.setDescription("Some description");
		rToUpdate.setReimbType(rt);
		rToUpdate.setReimbStatus(rs);

		Reimbursement rservExpected = new Reimbursement();
		rservExpected.setId(3);
		rservExpected.setAmount(10.95);
		rservExpected.setDescription("Some description");
		rservExpected.setReimbType(rt);
		rservExpected.setReimbStatus(rs);

		Mockito.when(mockReimbursementDao.updateReimbursement(rToUpdate)).thenReturn(rdaoExpected);

		Reimbursement rservActual = sut.updateReimbursement(rToUpdate);

		assertEquals(rservExpected, rservActual);
	}

	@Test
	public void getReimbursementsEmpty() {
		List<Reimbursement> udaoExpected = new ArrayList<>();
		Mockito.when(mockReimbursementDao.retrieveReimbursements()).thenReturn(udaoExpected);

		assertThrows(NotFoundException.class, () -> sut.getReimbursements());
	}

	@Test
	public void getReimbursementsNull() {
		Mockito.when(mockReimbursementDao.retrieveReimbursements()).thenReturn(null);

		assertThrows(NotFoundException.class, () -> sut.getReimbursements());
	}

	@Test
	public void getReimbursementsByAuthorEmpty() {
		List<Reimbursement> udaoExpected = new ArrayList<>();
		User author = new User();
		Role r = new Role();
		r.setId(1);
		r.setRole("EMPLOYEE");
		author.setId(3);
		author.setUsername("jdoe");
		author.setPassword("mypass");
		author.setFirstName("John");
		author.setLastName("Doe");
		author.setRole(r);
		author.setEmail("jdoe@somedomain.com");
		
		Mockito.when(mockReimbursementDao.retrieveReimbursementsByAuthor(author)).thenReturn(udaoExpected);

		assertThrows(NotFoundException.class, () -> sut.getReimbursementsByAuthor(author));
	}

	@Test
	public void getReimbursementsByAuthorSuccess() throws NotFoundException {
		List<Reimbursement> rdaoExpected = new ArrayList<>();
		List<Reimbursement> rservExpected = new ArrayList<>();

		User author = new User();
		Role r = new Role();
		r.setId(1);
		r.setRole("EMPLOYEE");
		author.setId(3);
		author.setUsername("jdoe");
		author.setPassword("mypass");
		author.setFirstName("John");
		author.setLastName("Doe");
		author.setRole(r);
		author.setEmail("jdoe@somedomain.com");
		
		ReimbType rt = new ReimbType();
		rt.setId(1);
		rt.setType("FOOD");
		ReimbStatus rs = new ReimbStatus();
		rs.setId(1);
		rs.setStatus("PENDING");
		
		Reimbursement one = new Reimbursement();
		one.setId(3);
		one.setAmount(10.95);
		one.setDescription("Some description");
		one.setReimbType(rt);
		one.setReimbStatus(rs);
		one.setAuthor(author);
		Reimbursement two = new Reimbursement();
		two.setId(6);
		two.setAmount(8.78);
		two.setDescription("Some other description");
		two.setReimbType(rt);
		two.setReimbStatus(rs);
		two.setAuthor(author);
		rdaoExpected.add(one);
		rdaoExpected.add(two);
		rservExpected.add(one);
		rservExpected.add(two);

		Mockito.when(mockReimbursementDao.retrieveReimbursementsByAuthor(author)).thenReturn(rdaoExpected);

		List<Reimbursement> rservActual = sut.getReimbursementsByAuthor(author);
		assertEquals(rservExpected, rservActual);
	}

}
