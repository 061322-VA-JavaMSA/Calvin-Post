package com.revature.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.revature.enums.Role;
import com.revature.models.Item;
import com.revature.models.Offer;
import com.revature.models.User;

public class OfferServiceTest {
	
	private static OfferService sut;
	
	@BeforeAll
	public static void setUp() {
		sut = new OfferService();
		System.out.println("Begin testing.");
	}
	
	@AfterAll
	public static void tearDown() {
		System.out.println("End of testing.");
	}
	
	@Test
	public void getOffersNotNull() {
		assertNotNull(sut.getOffers());
	}
	
	@Test
	public void createOfferItemNull() {
		Item i = new Item();
		User u = null;
		double amount = 50.0;
		
		assertFalse(sut.createOffer(i, u, amount));
	}
	
	@Test
	public void getOffersByItemId10() {
		Item i = new Item();
		i.setId(10);
		List<Offer> actual = sut.getOffersByItem(i);
		assertNotNull(actual);
	}
	
	@Test
	public void acceptOfferNull() {
		Offer o = new Offer();
		List<Offer> actual = sut.acceptOffer(o);
		assertNull(sut.acceptOffer(o));
	}
	
	@Test
	public void rejectOfferNull() {
		Offer o = new Offer();
		assertFalse(sut.rejectOffer(o));
	}
	
}
