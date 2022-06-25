package com.revature.daos;

import java.util.List;

import com.revature.models.Item;
import com.revature.models.User;

public interface ItemDAO {

	Item getItemById(int id);

	List<Item> getItemsByUser(User u);

	List<Item> getAvailableItems();

	Item createItem(Item i);

	boolean updateItem(Item i);

	boolean removeItemById(int id);
}
