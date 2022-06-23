package com.revature.daos;

import java.util.List;

import com.revature.models.Item;
import com.revature.models.Offer;

public interface OfferDAO {

	List<Offer> getOffersByItem(Item i);
	Offer getOfferById(int id);
	List<Offer> getOffers();
	boolean updateOffer(Offer o);
	boolean acceptOfferUpdate(Offer o);
	boolean removeOfferById(int id);
}
