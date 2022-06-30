package com.revature.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.daos.ItemDAO;
import com.revature.daos.ItemPostgres;
import com.revature.daos.OfferDAO;
import com.revature.daos.OfferPostgres;
import com.revature.models.Item;
import com.revature.models.Offer;
import com.revature.models.User;
import com.revature.util.Util;

public class OfferService {

	private OfferDAO od = new OfferPostgres();
	private ItemDAO id = new ItemPostgres();
	private PaymentService ps = new PaymentService();
	private Logger log = LogManager.getLogger(OfferService.class);

	public boolean createOffer(Item i, User u, double d) {
		Offer o = new Offer();

		if(i == null || u == null) {
			return false;
		}
		o.setAmount(d);
		o.setDate(LocalDate.now());
		o.setUser(u);
		o.setItem(i);
		o.setStatus("pending");

		if (od.createOffer(o)) {
			Util.println(String.format("Offering $%.2f for %s.", o.getAmount(), i.getName()));
			return true;
		} else {
			Util.println("Failed to process offer.");
			return false;
		}
	}

	public List<Offer> getOffersByItem(Item i) {
		if(i == null) {
			return null;
		}
		return od.getOffersByItem(i);
	}
	
	public List<Offer> acceptOffer(Offer o){
		if(o == null || o.getItem() == null || o.getUser() == null) {
			return null;
		}
		return od.updateOffers(o);
	}
	
	public boolean rejectOffer(Offer o) {
		if(o == null || o.getItem() == null || o.getUser() == null) {
			return false;
		}
		return od.rejectOffer(o);
	}
	
	public List<Offer> getOffers(){
		List<Offer> offers = od.getOffers();
		return offers;
	}
}
