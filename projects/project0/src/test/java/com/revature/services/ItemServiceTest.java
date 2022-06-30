package com.revature.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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

public class ItemServiceTest {

	private static ItemService sut;

	@BeforeAll
	public static void setUp() {
		sut = new ItemService();
		System.out.println("Begin testing.");
	}

	@AfterAll
	public static void tearDown() {
		System.out.println("End of testing.");
	}

	@Test
	public void addItemMarioKart() {
		String name = "Mario Kart";
		String desc = "Racing game";

		assertNotNull(sut.addItem(name, desc));
	}

	@Test
	public void updateItemStatusMarioKart() {
		Offer o = new Offer();
		assertFalse(sut.updateItemStatus(o));
	}

	@Test
	public void updateItemSuperSmashBros() {
		int id = 1;
		String name = "Super Smash Bros.";
		String desc = "Pit your favorite Nintendo characters against each other!";

		assertTrue(sut.updateItem(id, name, desc));
	}

	@Test
	public void getItemsUser() {
		List<Item> expected = sut.getItemsByUserId(2);
		assertNotNull(expected);
	}

	@Test
	public void updateItemBalance50() {
		Item i = new Item();
		i.setId(4);
		i.setBalance(50.0);
		assertTrue(sut.updateItemBalance(i));
	}

	@Test
	public void deleteItemId9() {
		assertFalse(sut.deleteItemById(9));
	}

	@Test
	public void getItemsAsEmployee() {
		List<Item> user = sut.getItems(Role.USER);
		List<Item> employee = sut.getItems(Role.EMPLOYEE);
		assertTrue(employee.size() > user.size());
		
	}
}
