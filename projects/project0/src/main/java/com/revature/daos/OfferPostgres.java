package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.revature.models.Item;
import com.revature.models.Offer;
import com.revature.util.ConnectionUtil;

public class OfferPostgres implements OfferDAO {

	@Override
	public List<Offer> getOffersByItem(Item i) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Offer getOfferById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Offer> getOffers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateOffer(Offer o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean acceptOfferUpdate(Offer o) {
		String sql = "begin;update offers set status = 'rejected' where item_id = ?; update offers set status = 'accepted' where id = ?; commit;";
		
		try (Connection c = ConnectionUtil.getConnectionFromFile()) {
			
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, o.getItem().getId());
			ps.setInt(2, o.getId());
			
			int rowsChanged = -1;
			
			rowsChanged = ps.executeUpdate();
			return rowsChanged < 1 ? false : true;
			
		} catch (SQLException e) {
			
		}
		return false;
	}

	@Override
	public boolean removeOfferById(int id) {
		// TODO Auto-generated method stub
		return false;
	}

}
