package com.revature.daos;

import java.util.List;

import com.revature.models.Item;
import com.revature.models.Offer;
import com.revature.models.User;

public interface OfferDAO {

	List<Offer> getOffersByItem(Item i);

	List<Offer> getOffersByUser(User u);

	List<Offer> getOffers();
	
	boolean createOffer(Offer o);

	boolean rejectOffer(Offer o);

	List<Offer> updateOffers(Offer o);
}
