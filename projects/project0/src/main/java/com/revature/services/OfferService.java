package com.revature.services;

import java.time.LocalDate;
import java.util.List;

import com.revature.daos.OfferDAO;
import com.revature.daos.OfferPostgres;
import com.revature.models.Item;
import com.revature.models.Offer;
import com.revature.models.User;
import com.revature.util.Util;

public class OfferService {

	private OfferDAO od = new OfferPostgres();
	
	public void addOffer(Item i, User u) {
		Offer o = new Offer();
		
		Util.println("How much would you like to offer?");
		String s = Util.in.nextLine();
		if(Util.isDouble(s)) {
			o.setAmount(Double.parseDouble(s));
			o.setDate(LocalDate.now());
			o.setUser(u);
			o.setItem(i);
			o.setStatus("pending");
			
			if(od.createOffer(o)) {
				Util.println("Offering $" + o.getAmount() + " for " + i.getName() + ".");
			}
			else {
				Util.println("Failed to process offer.");
			}
			Util.pause();
		} else {
			Util.println("Invalid input.");
			this.addOffer(i, u);
		}
	}
	
	public void viewOffersForItem(Item i) {
		List<Offer> offers = od.getOffersByItem(i);
		
		
	}
}
