package com.revature.daos;

import java.util.List;

import com.revature.models.Item;
import com.revature.models.Offer;
import com.revature.models.User;

public interface ItemDAO {

	Item getItemById(int id);

	List<Item> getItemsByUser(User u);
	
	List<Item> getItemsByStatus(String operator, String status);

	List<Item> getItems();

	Item createItem(Item i);
	
	void updateNewItemStatus();
	
	void updateItemOwnedStatus(Offer o);
	
	void updateItemBalance(Item i);

	boolean removeItemById(int id);
}
