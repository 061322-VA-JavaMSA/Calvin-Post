package com.revature.services;

import java.util.List;

import com.revature.daos.ItemDAO;
import com.revature.daos.ItemPostgres;
import com.revature.models.Item;
import com.revature.models.User;
import com.revature.util.Table;
import com.revature.util.Util;

public class ItemService {

	private ItemDAO id = new ItemPostgres();
	private OfferService os = new OfferService();

	public void updateNewItemStatus() {
		id.updateNewItemStatus();
	}

	public Item addItem() {
		Util.clear();
		Item i = new Item();
		Table.header("Add a new game");
		Util.print("Title: ");
		i.setName(Util.in.nextLine());
		Util.print("Description: ");
		i.setDescription(Util.in.nextLine());

		i = id.createItem(i);

		if (i.getId() == 0) {

		}

		return i;
	}

	public void viewOwnedItems(User u) {
		List<Item> items = id.getOwnedItems(u);

		Util.clear();
		Table.header(u.getFirstName() + "'s Games");
		int line = 1;
		for (Item i : items) {

			Util.println(" " + line + ". " + i.getName());

			line++;
		}

		Util.println("\n\n\n");
		Util.println("Select a game to view or press ENTER to return.");
		String choice = Util.in.nextLine();
		if (choice.equals("")) {

		} else if (Util.isInt(choice)) {

			this.viewOwnedItems(u);
		} else {
			Util.invalid();
			this.viewOwnedItems(u);
		}

	}

	public void viewOwnedItem(Item i, User u) {
		Util.clear();

		Util.println(i.getName());
		Util.println();
		Util.println(i.getDescription());

		Util.println("\n\n\n");
		Util.println("What would you like to do?");
		Util.println(" 1. M");
	}

	public void viewItems(User u) {
		List<Item> items = id.getItems();

		Util.clear();
		if (u.getLevel() > 1) {
			int line = 1;
			for (Item i : items) {
				Util.println(" " + line + ". " + i.getName());
				line++;
			}
		} else {
			Util.println("Here's what we have in stock.    (* new item)");
			int line = 1;
			for (Item i : items) {
				if (i.getStatus().equals("owned")) {
					items.remove(i);
				} else {
					Util.print(i.getStatus().equals("new") ? "*" : " ");
					Util.println(line + ". " + i.getName());
					line++;
				}

			}
		}

		Util.println("\n\n\n");
		Util.println("Select a game to view or press ENTER to return.");
		String choice = Util.in.nextLine();

		if (!choice.isEmpty()) {
			int i = Integer.parseInt(choice) - 1;
			if (i > -1 && i < items.size()) {
				this.viewItem(items.get(i), u);
				this.viewItems(u);
			} else {
				Util.invalid();
				this.viewItems(u);
			}
		}

	}

	public void viewItem(Item i, User u) {
		Util.clear();

		Util.println(i.getName());
		Util.println();
		Util.println(i.getDescription());

		Util.println("\n\n\n");
		Util.println("What would you like to do?");
		if (u.getLevel() > 1) {
			// employee options
			Util.println(" 1. Update information\n 2. View offers\n 3. Remove this game\n 4. Go back");
			switch (Util.in.nextLine()) {

			case "1":
				// this.updateItem(i);
				this.viewItem(i, u);
				break;

			case "2":
				os.viewOffersForItem(i);

				break;
			case "3":
				if (id.removeItemById(i.getId())) {
					Util.println(i.getName() + " was removed.");
					Util.pause();
				} else {
					Util.println("Failed to remove " + i.getName());
					Util.pause();
					this.viewItem(i, u);
				}

			case "4":
				break;

			default:
				Util.invalid();
				this.viewItem(i, u);

			}
		} else if (u.getLevel() == 1) {
			// customer options
			Util.println(" 1. Make an offer\n 2. Go back");
			switch (Util.in.nextLine()) {

			case "1":
				os.addOffer(i, u);
				break;

			case "2":
				break;

			default:
				Util.invalid();
				this.viewItem(i, u);
			}
		}
	}
}
