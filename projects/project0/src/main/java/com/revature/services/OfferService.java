package com.revature.services;

import java.time.LocalDate;
import java.util.List;

import com.revature.daos.ItemDAO;
import com.revature.daos.ItemPostgres;
import com.revature.daos.OfferDAO;
import com.revature.daos.OfferPostgres;
import com.revature.daos.PaymentDAO;
import com.revature.daos.PaymentPostgres;
import com.revature.models.Item;
import com.revature.models.Offer;
import com.revature.models.User;
import com.revature.util.Table;
import com.revature.util.Util;

public class OfferService {

	private OfferDAO od = new OfferPostgres();
	private ItemDAO id = new ItemPostgres();
	private PaymentDAO pd = new PaymentPostgres();

	public void addOffer(Item i, User u) {
		Offer o = new Offer();

		Util.println("How much would you like to offer?");
		String s = Util.in.nextLine();
		if (Util.isDouble(s)) {
			o.setAmount(Double.parseDouble(s));
			o.setDate(LocalDate.now());
			o.setUser(u);
			o.setItem(i);
			o.setStatus("pending");

			if (od.createOffer(o)) {
				Util.println("Offering $" + o.getAmount() + " for " + i.getName() + ".");
			} else {
				Util.println("Failed to process offer.");
			}
			Util.pause();
		} else {
			Util.invalid();
			this.addOffer(i, u);
		}
	}

	public void viewOffersForItem(Item i) {
		List<Offer> offers = od.getOffersByItem(i);
		Util.clear();
		Table.title("Offers for '" + i.getName() + "'");
		Table.header(String.format("%4s    %-10s    %-10s    %-20s    %6s", "No.", "Date", "Status", "User", "Amount"));

		int line = 1;

		for (Offer o : offers) {
			Table.row(String.format("%4d    %-10s    %-10s    %-20s  $ %6.2f", line++, o.getDate(), o.getStatus(),
					o.getUser().getUsername(), o.getAmount()));
		}
		Util.hr();
		Util.println();
		Util.println("Select an offer and specify 'a' to accept or 'r' to reject. (ex: 2 a)");
		Util.println("Press ENTER to return and commit changes.");
		String selection = Util.in.nextLine();
		if (selection.equals("")) {
			for(Offer o: offers) {
				if(o.getStatus().equals("accepted")) {
					id.updateItemOwnedStatus(o);
					pd.createPaymentsByItem(i);
				}
			}
		} else {
			String[] args = selection.split(" ");
			if (args.length == 2) {
				if (Util.isInt(args[0])) {
					int x = Integer.parseInt(args[0]) - 1;
					if (x > -1 && x < offers.size()) {
						Offer o = offers.get(x);

						if (args[1].equalsIgnoreCase("a")) {
							i.setBalance(o.getAmount());
							offers = od.acceptOffer(o);
							this.viewOffersForItem(i);
						} else if (args[1].equalsIgnoreCase("r")) {
							od.rejectOffer(o);
							this.viewOffersForItem(i);
						} else {
							Util.invalid();
							this.viewOffersForItem(i);
						}
					} else {
						Util.invalid();
						this.viewOffersForItem(i);
					}

				}
			} else {
				Util.invalid();
				this.viewOffersForItem(i);
			}
		}
	}
}
