package com.revature.services;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.daos.ItemDAO;
import com.revature.daos.ItemPostgres;
import com.revature.enums.Role;
import com.revature.models.Item;
import com.revature.models.Offer;
import com.revature.util.Util;

public class ItemService {

	private ItemDAO id = new ItemPostgres();
	private OfferService os = new OfferService();
	private PaymentService ps = new PaymentService();
	private Logger log = LogManager.getLogger(ItemService.class);

	public void updateNewItemStatus() {
		id.updateNewItemStatus();
	}

	public Item addItem(String name, String desc) {
		Item i = new Item();
		i.setName(name);
		i.setDescription(desc);
		if (name.equals("") || desc.equals("")) {
			return null;
		}
		return id.createItem(i);
	}

	public boolean updateItemStatus(Offer o) {
		if (o.getUser() == null || o.getItem() == null) {
			return false;
		}
		if (id.updateItemOwnedStatus(o)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean updateItem(int id, String name, String desc) {
		Item i = new Item();
		i.setId(id);
		i.setName(name);
		i.setDescription(desc);
		if (this.id.updateItem(i)) {
			return true;
		} else {
			return false;
		}
	}

	public List<Item> getItemsByUserId(int id) {
		List<Item> items = this.id.getItemsByUserId(id);
		if (items.isEmpty()) {
			return null;
		}
		return items;
	}

	public boolean updateItemBalance(Item i) {
		if (id.updateItemBalance(i)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean deleteItemById(int id) {
		if (this.id.deleteItemById(id)) {
			return true;
		} else {
			return false;
		}
	}

	public List<Item> getItems(Role role) {
		if (role.greaterThan(Role.USER)) {
			return id.getItems();
		} else {
			return id.getAvailableItems();
		}
	}

}
