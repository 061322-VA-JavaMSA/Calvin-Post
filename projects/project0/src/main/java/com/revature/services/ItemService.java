package com.revature.services;

import java.util.List;

import com.revature.daos.ItemDAO;
import com.revature.daos.ItemPostgres;
import com.revature.models.Item;
import com.revature.models.Payment;
import com.revature.models.User;
import com.revature.util.Table;
import com.revature.util.Util;

public class ItemService {

	private ItemDAO id = new ItemPostgres();
	private OfferService os = new OfferService();
	private PaymentService ps = new PaymentService();

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
		List<Item> items = id.getItemsByUser(u);

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
			int c = Integer.parseInt(choice) - 1;
			if (c > -1 && c < items.size()) {
				this.viewOwnedItem(items.get(c));
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
		List<Payment> payments = ps.getPaymentsByItem(i);
		Util.clear();

		Table.title(i.getName());
		Table.row(String.format("%-20s      $ %6.2f", "Remaining balance:", i.getBalance()));
		Util.hr();
		
		Table.header(String.format("%-10s    %-6s    %10s", "Due Date", "Status", "Amount Due"));
		for(Payment p : payments) {
		Table.header(String.format("%-10s    %-6s      $ %6.2f", p.getDateDue().toString(), p.getStatus(), p.getAmountDue()));
		}
		Util.println(i.getDescription());

		Util.println("\n\n\n");
		Util.println("What would you like to do?");
		Util.println(" 1. Make a payment");
		String choice = Util.in.nextLine();
		
		//make payment
		for(Payment p : payments) {
			if(p.getStatus().equals("unpaid")) {
				p.setStatus("paid");
				ps.updatePaymentStatus(p);
				i.setBalance(i.getBalance() - p.getAmountDue());
				id.updateItemBalance(i);
				break;
			}
		}
		this.viewOwnedItem(i);
		
	}

	public void viewItems(User u) {
		boolean isEmployee = u.getLevel() > 1;
		List<Item> items = isEmployee ? id.getItems() : id.getItemsByStatus("<>", "owned");
		int line = 1;
		Util.clear();
		Table.row(isEmployee ? "All Games" : "In Stock Games");

		for (Item i : items) {
			String row = isEmployee ? "" : "";
			Table.row(row);
			Util.print(i.getStatus().equals("new") ? "*" : " ");
			Util.println(line + ". " + i.getName());
			line++;
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
