package com.revature.daos;

import java.util.List;

import com.revature.models.Item;
import com.revature.models.Offer;
import com.revature.models.User;

public interface ItemDAO {

	Item getItemById(int id);
	
	boolean updateItem(Item i);

	List<Item> getItemsByUserId(int id);
	
	List<Item> getAvailableItems();

	List<Item> getItems();

	Item createItem(Item i);
	
	void updateNewItemStatus();
	
	boolean updateItemOwnedStatus(Offer o);
	
	boolean updateItemBalance(Item i);

	boolean deleteItemById(int id);

}
