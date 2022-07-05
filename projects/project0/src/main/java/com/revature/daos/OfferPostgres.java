package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.Driver;
import com.revature.models.Item;
import com.revature.models.Offer;
import com.revature.models.User;
import com.revature.util.ConnectionUtil;

public class OfferPostgres implements OfferDAO {
	
	private static Logger log = LogManager.getLogger(OfferPostgres.class);

	@Override
	public List<Offer> getOffersByItem(Item i) {
		String sql = "select * from (select created_on, status, amount, item_id, user_id, username from offers join users on user_id = id) o where item_id = ?;";
		List<Offer> offers= new ArrayList<>();
		
		try (Connection c = ConnectionUtil.getConnectionFromFile()) {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, i.getId());
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Offer o = new Offer();
				o.setDate(rs.getDate("created_on").toLocalDate());
				o.setStatus(rs.getString("status"));
				o.setAmount(rs.getDouble("amount"));
				o.setItem(i);
				User u = new User();
				u.setId(rs.getInt("user_id"));
				u.setUsername(rs.getString("username"));
				o.setUser(u);
				
				offers.add(o);
			}
			
		} catch (SQLException e) {
			log.error("Failed to retrieve data from offers.");
		}
		return offers;
	}

	@Override
	public List<Offer> getOffers() {
		String sql = "select o.created_on, o.status, amount, item_id, name, o.user_id, username from offers o join users u on u.id = o.user_id join items i on i.id = item_id order by o.created_on desc;";
		List<Offer> offers= new ArrayList<>();
		
		try (Connection c = ConnectionUtil.getConnectionFromFile()) {
			Statement s = c.createStatement();
			
			ResultSet rs = s.executeQuery(sql);
			while(rs.next()) {
				Offer o = new Offer();
				o.setDate(rs.getDate("created_on").toLocalDate());
				o.setStatus(rs.getString("status"));
				o.setAmount(rs.getDouble("amount"));
				Item i = new Item();
				i.setId(rs.getInt("item_id"));
				i.setName(rs.getString("name"));
				o.setItem(i);
				User u = new User();
				u.setId(rs.getInt("user_id"));
				u.setUsername(rs.getString("username"));
				o.setUser(u);
				
				offers.add(o);
			}
			
		} catch (SQLException e) {
			log.error("Failed to retrieve data from offers.");
		}
		return offers;
	}

	@Override
	public List<Offer> updateOffers(Offer o) {
		String sql = "begin;update offers set status = 'rejected' where item_id = ? and user_id <> ?; update offers set status = 'accepted' where item_id = ? and user_id = ?; commit;";
		int itemId = o.getItem().getId();
		int userId = o.getUser().getId();
		
		try (Connection c = ConnectionUtil.getConnectionFromFile()) {
			
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, itemId);
			ps.setInt(2, userId);
			ps.setInt(3, itemId);
			ps.setInt(4, userId);
			
			ps.executeUpdate();
			
			
			
		} catch (SQLException e) {
			log.error("Failed to update offers.");
		}
		
		Item i = new Item();
		i.setId(itemId);
		return this.getOffersByItem(i);
	}

	@Override
	public List<Offer> getOffersByUser(User u) {
		String sql = "select date, status, amount, item_id, name, o.user_id, username from offers o join users u on u.id = o.user_id join items i on i.id = item_id having user_id = ?;";
		List<Offer> offers= new ArrayList<>();
		
		try (Connection c = ConnectionUtil.getConnectionFromFile()) {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, u.getId());
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Offer o = new Offer();
				o.setDate(rs.getDate("date").toLocalDate());
				o.setStatus(rs.getString("status"));
				o.setAmount(rs.getDouble("amount"));
				Item i = new Item();
				i.setId(rs.getInt("item_id"));
				i.setName(rs.getString("name"));
				o.setItem(i);
				o.setUser(u);
				
				offers.add(o);
			}
			
		} catch (SQLException e) {
			log.error("Failed to retrieve data from offers.");
		}
		return offers;
	}

	@Override
	public boolean createOffer(Offer o) {
		String sql = "insert into offers (amount, item_id, user_id) values (?, ?, ?);";
		int rowsChanged = -1;
		
		try (Connection c = ConnectionUtil.getConnectionFromFile()) {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setDouble(1, o.getAmount());
			ps.setInt(2, o.getItem().getId());
			ps.setInt(3, o.getUser().getId());
			
			rowsChanged = ps.executeUpdate();
			return rowsChanged < 1 ? false : true;
			
		} catch (SQLException e) {
			log.error("Failed to insert into offers.");
			return false;
		}
	}

	@Override
	public boolean rejectOffer(Offer o) {
		String sql = "update offers set status = 'rejected' where user_id = ? and item_id = ?;";
		int rowsChanged = -1;
		
		try(Connection c = ConnectionUtil.getConnectionFromFile()) {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, o.getUser().getId());
			ps.setInt(2, o.getItem().getId());
			
			rowsChanged = ps.executeUpdate();
			if(rowsChanged > 0) {
				o.setStatus("rejected");
				return true;
			}
		} catch(SQLException e) {
			log.error("Failed to update offers.");
			return false;
		}
		return false;
	}

}
