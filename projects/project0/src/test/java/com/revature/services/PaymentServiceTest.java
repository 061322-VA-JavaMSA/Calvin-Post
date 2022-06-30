package com.revature.services;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.revature.enums.Role;
import com.revature.models.Item;
import com.revature.models.Payment;

public class PaymentServiceTest {

	private static PaymentService sut;

	@BeforeAll
	public static void setUp() {
		sut = new PaymentService();
		System.out.println("Begin testing.");
	}

	@AfterAll
	public static void tearDown() {
		System.out.println("End of testing.");
	}

	@Test
	public void getPaymentsByItemNullUser() {
		Item i = new Item();
		List<Payment> actual = sut.getPaymentsByItem(i);
		assertNull(actual);
	}

	@Test
	public void updatePaymentItemNull() {
		Payment p = new Payment();
		assertFalse(sut.updatePayment(p));
	}

	@Test
	public void createPaymentsItemNull() {
		Item i = null;
		assertFalse(sut.createPayments(i));
	}

	@Test
	public void getPaymentsAsUser() {
		List<Payment> actual = sut.getPayments(Role.USER);
		assertNull(actual);
	}

	@Test
	public void getPaymentsAsEmployee() {
		List<Payment> actual = sut.getPayments(Role.EMPLOYEE);
		assertNotNull(actual);
	}
}
