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
	public List<Item> getOwnedItems(User u) {
		String sql = "select id, name, description, balance from owned where user_id = ? order by name asc;";
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
				i.setUser(u);
				
				items.add(i);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return items;
	}

	@Override
	public Item updateItemStatusById(int id, String status) {
		String sql = "update items set status = ? where id = ?";
		
		try(Connection c = ConnectionUtil.getConnectionFromFile()) {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, status);
			ps.setInt(2, id);
			
			ps.executeUpdate();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return this.getItemById(id);
	}

	@Override
	public void moveItemToOwned(Item i, Offer o) {
		String sql = "begin; delete from offers where item_id = ?; delete from items where id = ?; insert into owned (name, description, balance, user_id) values (?, ?, ?, ?); commit;";
		
		try(Connection c = ConnectionUtil.getConnectionFromFile()) {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, i.getId());
			ps.setInt(2, i.getId());
			ps.setString(3, i.getName());
			ps.setString(4, i.getDescription());
			ps.setDouble(5, o.getAmount());
			ps.setInt(6, o.getUser().getId());
			
			ps.executeUpdate();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
