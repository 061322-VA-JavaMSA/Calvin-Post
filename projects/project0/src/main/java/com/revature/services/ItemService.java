package com.revature.services;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.daos.ItemDAO;
import com.revature.daos.ItemPostgres;
import com.revature.models.Item;
import com.revature.models.User;
import com.revature.util.Table;
import com.revature.util.Util;

public class ItemService {

	private ItemDAO id = new ItemPostgres();
	private OfferService os = new OfferService();
	private PaymentService ps = new PaymentService();
	private Logger log = LogManager.getLogger(ItemService.class);

	public void updateNewItemStatus() {
		id.updateNewItemStatus();
	}

	public Item addItem() {
		Table.title("Add New Game");
		Item i = new Item();
		Util.print("Title: ");
		i.setName(Util.in.nextLine());
		Util.print("Description: ");
		i.setDescription(Util.in.nextLine());

		i = id.createItem(i);

		if (i.getId() == 0) {

		}

		return i;
	}
	
	public void updateItem(Item i) {
		
	}

	public void viewOwnedItems(User u) {
		List<Item> items = id.getItemsByUser(u);

		Table.title(u.getFirstName() + "'s Games");
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
			int c = Integer.parseInt(choice) - 1;
			if (c > -1 && c < items.size()) {
				this.viewOwnedItem(items.get(c));
				this.viewOwnedItems(u);
			} else {
				Util.invalid();
				this.viewOwnedItems(u);
			}
		} else {
			Util.invalid();
			this.viewOwnedItems(u);
		}

	}

	public void viewOwnedItem(Item i) {
		ps.viewPaymentsByItem(i);
	}

	public void viewItems(User u) {
		boolean isEmployee = u.getLevel() > 1;
		List<Item> items = isEmployee ? id.getItems() : id.getItemsByStatus("<>", "owned");
		int line = 1;
		Table.title(isEmployee ? "All Games" : "In Stock Games");

		Table.header(String.format("%3.3s    %-30.30s    %-9s", "No.", "Title", "Status"));
		for (Item i : items) {
			Table.row(String.format("%3d    %-30.30s    %-9s", line++, i.getName(), i.getStatus()));
		}

		Util.hr();
		Util.println("Select a game to view or press ENTER to return.");
		Util.hr();
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
		Table.title(i.getName());

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
