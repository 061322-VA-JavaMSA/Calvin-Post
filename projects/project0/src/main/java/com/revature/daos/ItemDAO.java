package com.revature.daos;

import java.util.List;

import com.revature.models.Item;
import com.revature.models.Offer;
import com.revature.models.User;

public interface ItemDAO {

	Item getItemById(int id);

	List<Item> getOwnedItems(User u);

	List<Item> getItems();

	Item createItem(Item i);

	Item updateItemStatusById(int id, String status);
	
	void updateNewItemStatus();
	
	void moveItemToOwned(Item i, Offer o);

	boolean removeItemById(int id);
}
