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

import com.revature.models.Item;
import com.revature.models.Offer;
import com.revature.models.User;
import com.revature.util.ConnectionUtil;

public class ItemPostgres implements ItemDAO {

	private UserDAO ud = new UserPostgres();
	private static Logger log = LogManager.getLogger(ItemPostgres.class);

	@Override
	public Item getItemById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateNewItemStatus() {
		String sql = "update items set status = 'available' where status = 'new' and created_on < current_date - 7;";
		try (Connection c = ConnectionUtil.getConnectionFromFile()) {
			Statement s = c.createStatement();
			s.execute(sql);
		} catch (SQLException e) {
			log.error("Failed to update items. - updateNewItemStatus");
		}
	}

	@Override
	public List<Item> getAvailableItems() {
		String sql = "select * from items where status <> 'owned';";
		List<Item> items = new ArrayList<>();

		try (Connection c = ConnectionUtil.getConnectionFromFile()) {

			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);

			while (rs.next()) {
				Item i = new Item();
				i.setId(rs.getInt("id"));
				i.setName(rs.getString("name"));
				i.setDescription(rs.getString("description"));
				i.setStatus(rs.getString("status"));
				i.setBalance(rs.getDouble("balance"));

				items.add(i);
			}
		} catch (SQLException e) {
			log.error("Failed to retrieve data from items. - getAvailableItems");
		}

		return items;
	}

	@Override
	public List<Item> getItems() {
		String sql = "select * from items left join users on user_id = users.id order by status desc, name asc;";
		List<Item> items = new ArrayList<>();

		try (Connection c = ConnectionUtil.getConnectionFromFile()) {

			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);

			while (rs.next()) {
				Item i = new Item();
				i.setId(rs.getInt("id"));
				i.setName(rs.getString("name"));
				i.setDescription(rs.getString("description"));
				i.setStatus(rs.getString("status"));
				User u = new User();
				u.setUsername(rs.getString("username"));
				u.setFirstName(rs.getString("first_name"));
				u.setLastName(rs.getString("last_name"));
				u.setId(rs.getInt("user_id"));
				i.setUser(u);
				items.add(i);
			}
		} catch (SQLException e) {
			log.error("Failed to retrieve data from items. - getItems");
		}
		return items;
	}

	@Override
	public Item createItem(Item i) {
		String sql = "insert into items (name, description) values (?, ?) returning id;";

		try (Connection c = ConnectionUtil.getConnectionFromFile()) {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, i.getName());
			ps.setString(2, i.getDescription());

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				i.setId(rs.getInt("id"));
				return i;
			}
		} catch (SQLException e) {
			log.error("Failed to insert into items. - createItem");
		}

		return null;
	}

	@Override
	public boolean deleteItemById(int id) {
		String sql = "delete from items where id = " + id + ";";
		int rowsChanged = -1;

		try (Connection c = ConnectionUtil.getConnectionFromFile()) {

			Statement s = c.createStatement();

			rowsChanged = s.executeUpdate(sql);
			return rowsChanged > 0 ? true : false;
		} catch (SQLException e) {
			log.error("Failed to delete from items. - removeItem");
		}
		return false;
	}

	@Override
	public boolean updateItem(Item i) {
		String sql = "update items set name = ?, description = ? where id = ?;";

		try (Connection c = ConnectionUtil.getConnectionFromFile()) {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, i.getName());
			ps.setString(2, i.getDescription());
			ps.setInt(3, i.getId());

			int rowsChanged = ps.executeUpdate();
			if (rowsChanged > 0) {
				return true;
			}

		} catch (SQLException e) {
			log.error("Failed to update items. - updateItem");
		}
		return false;
	}

	@Override
	public List<Item> getItemsByUserId(int id) {
		String sql = "select id, name, description, balance from items where user_id = ? order by name asc;";
		List<Item> items = new ArrayList<>();

		try (Connection c = ConnectionUtil.getConnectionFromFile()) {

			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Item i = new Item();
				i.setId(rs.getInt("id"));
				i.setName(rs.getString("name"));
				i.setDescription(rs.getString("description"));
				i.setBalance(rs.getDouble("balance"));

				items.add(i);
			}
		} catch (SQLException e) {
			log.error("Failed to retrieve data from items. - getItemsByUser");
		}
		return items;
	}

	@Override
	public boolean updateItemOwnedStatus(Offer o) {
		String sql = "update items set user_id = ?, balance = ?, status = 'owned' where id = ?;";
		try (Connection c = ConnectionUtil.getConnectionFromFile()) {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, o.getUser().getId());
			ps.setDouble(2, o.getAmount());
			ps.setInt(3, o.getItem().getId());

			int rowsChanged = ps.executeUpdate();
			return rowsChanged > 0 ? true : false;
			
		} catch (SQLException e) {
			log.error("Failed to update items. - updateItemOwnedStatus");
		}
		return false;
	}

	@Override
	public boolean updateItemBalance(Item i) {
		String sql = "update items set balance = " + i.getBalance() + " where id = " + i.getId() + ";";

		try (Connection c = ConnectionUtil.getConnectionFromFile()) {
			Statement s = c.createStatement();
			int rowsChanged = s.executeUpdate(sql);
			return rowsChanged > 0 ? true : false;
		} catch (SQLException e) {
			log.error("Failed to update items. - updateItemBalance");
		}
		return false;
	}
}
