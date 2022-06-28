package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Item;
import com.revature.models.Offer;
import com.revature.models.User;
import com.revature.util.ConnectionUtil;

public class ItemPostgres implements ItemDAO {

	@Override
	public Item getItemById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void updateNewItemStatus() {
		String sql = "update items set status = 'available' where status = 'new' and created_on < current_date - 7;";
		try(Connection c = ConnectionUtil.getConnectionFromFile()) {
			Statement s = c.createStatement();
			s.execute(sql);
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public List<Item> getItemsByStatus(String operator, String status){
		String sql = "select * from items where status " + operator + " '" + status + "';";
		List<Item> items = new ArrayList<>();
		
		try(Connection c = ConnectionUtil.getConnectionFromFile()) {
			
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);
			
			while(rs.next()) {
				Item i = new Item();
				i.setId(rs.getInt("id"));
				i.setName(rs.getString("name"));
				i.setDescription(rs.getString("description"));
				i.setStatus(rs.getString("status"));
				i.setBalance(rs.getDouble("balance"));
				
				items.add(i);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return items;
	}

	@Override
	public List<Item> getItems() {
		String sql = "select id, name, description, status from items order by status desc, name asc;";
		List<Item> items = new ArrayList<>();
		
		try (Connection c = ConnectionUtil.getConnectionFromFile()) {
			
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);
			
			while(rs.next()) {
				Item i = new Item();
				i.setId(rs.getInt("id"));
				i.setName(rs.getString("name"));
				i.setDescription(rs.getString("description"));
				i.setStatus(rs.getString("status"));
				
				items.add(i);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return items;
	}

	@Override
	public Item createItem(Item i) {
		String sql = "insert into items (name, status, description) values (?, 'new', ?) returning id";
		
		try (Connection c = ConnectionUtil.getConnectionFromFile()) {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, i.getName());
			ps.setString(2, i.getDescription());
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				i.setId(rs.getInt("id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
//		Util.message(i.getName() + " was added successfully.", "New game added");
		
		return i;
	}

	@Override
	public boolean removeItemById(int id) {
		String sql = "delete from items where id = ?;";
		int rowsChanged = -1;
		
		try(Connection c = ConnectionUtil.getConnectionFromFile()) {
			
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, id);
			
			rowsChanged = ps.executeUpdate();
			return rowsChanged < 1 ? false : true;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Item> getItemsByUser(User u) {
		String sql = "select id, name, description, balance from items where user_id = ? order by name asc;";
		List<Item> items = new ArrayList<>();
		
		try(Connection c = ConnectionUtil.getConnectionFromFile()) {
			
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, u.getId());
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Item i = new Item();
				i.setId(rs.getInt("id"));
				i.setName(rs.getString("name"));
				i.setDescription(rs.getString("description"));
				i.setBalance(rs.getDouble("balance"));
				i.setUser(u);
				
				items.add(i);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return items;
	}

	@Override
	public void updateItemOwnedStatus(Offer o) {
		String sql = "begin; delete from offers where item_id = ?; update items set user_id = ?, balance = ?, status = 'owned' where id = ?; commit;";
		try(Connection c = ConnectionUtil.getConnectionFromFile()) {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, o.getItem().getId());
			ps.setInt(2, o.getUser().getId());
			ps.setDouble(3, o.getAmount());
			ps.setInt(4, o.getItem().getId());
			
			ps.executeUpdate();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void updateItemBalance(Item i) {
		String sql = "update items set balance = " + i.getBalance() + " where id = " + i.getId() + ";";
		
		try(Connection c = ConnectionUtil.getConnectionFromFile()) {
			Statement s = c.createStatement();
			s.execute(sql);
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
