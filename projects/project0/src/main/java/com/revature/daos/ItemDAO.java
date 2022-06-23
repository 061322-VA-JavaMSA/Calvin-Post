package com.revature.daos;

import java.util.List;

import com.revature.models.Item;

public interface ItemDAO {

	Item getItemById(int id);
	List<Item> getItems();
	boolean updateItem(Item i);
	boolean removeItemById(int id);
}
