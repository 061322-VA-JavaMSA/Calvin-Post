package com.revature.daos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.models.Item;
import com.revature.models.Payment;
import com.revature.models.User;
import com.revature.util.ConnectionUtil;

public class PaymentPostgres implements PaymentDAO {

	private ItemDAO id = new ItemPostgres();
	private static Logger log = LogManager.getLogger(PaymentPostgres.class);

	@Override
	public List<Payment> getPaymentsByItem(Item i) {
		List<Payment> payments = new ArrayList<>();
		String sql = "select * from payments where item_id = " + i.getId()
				+ " and status <> 'paid' order by date_due asc;";

		try (Connection c = ConnectionUtil.getConnectionFromFile()) {

			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);

			while (rs.next()) {
				Payment p = new Payment();
				p.setId(rs.getInt("id"));
				p.setDateDue(rs.getDate("date_due").toLocalDate());
				p.setStatus(rs.getString("status"));
				p.setAmountDue(rs.getDouble("amount_due"));
				p.setAmountReceived(rs.getDouble("amount_received"));
				p.setItem(i);

				payments.add(p);
			}
		} catch (SQLException e) {
			log.error("Failed to retrieve data from payments.");
		}
		return payments;
	}

	@Override
	public void createPayment(Payment p) {
		String sql = "insert into payments (date_due, amount_due, item_id) values (?, ?, ?);";

		try (Connection c = ConnectionUtil.getConnectionFromFile()) {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setDate(1, Date.valueOf(p.getDateDue()));
			ps.setDouble(2, p.getAmountDue());
			ps.setInt(3, p.getItem().getId());
			ps.execute();

		} catch (SQLException e) {
			log.error("Failed to insert into payments.");
		}
	}

	@Override
	public boolean updatePayment(Payment p) {
		String sql = "update payments set status = ?, amount_received = ? where id = ?;";

		try (Connection c = ConnectionUtil.getConnectionFromFile()) {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, p.getStatus());
			ps.setDouble(2, p.getAmountReceived());
			ps.setInt(3, p.getId());
			int rowsChanged = ps.executeUpdate();
			return rowsChanged > 0 ? true : false;
		} catch (SQLException e) {
			log.error("Failed to update payments.");
		}
		return false;
	}

	@Override
	public List<Payment> getPayments() {
		String sql = "select * from payments p join (select i.id item_id, name item_name, description, i.status item_status, balance, user_id, username, first_name, last_name from items i join users u on user_id = u.id) r on r.item_id = p.item_id order by p.status desc, date_due asc;";
		List<Payment> payments = new ArrayList<>();

		try (Connection c = ConnectionUtil.getConnectionFromFile()) {
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);

			while (rs.next()) {
				Payment p = new Payment();
				p.setId(rs.getInt("id"));
				p.setDateDue(rs.getDate("date_due").toLocalDate());
				p.setAmountDue(rs.getDouble("amount_due"));
				p.setAmountReceived(rs.getDouble("amount_received"));
				p.setStatus(rs.getString("status"));

				Item i = new Item();
				i.setId(rs.getInt("item_id"));
				i.setBalance(rs.getDouble("balance"));
				i.setDescription(rs.getString("description"));
				i.setName(rs.getString("item_name"));
				i.setStatus(rs.getString("item_status"));

				User u = new User();
				u.setId(rs.getInt("user_id"));
				u.setUsername(rs.getString("username"));
				u.setFirstName(rs.getString("first_name"));
				u.setLastName(rs.getString("last_name"));

				i.setUser(u);
				p.setItem(i);

				payments.add(p);
			}

		} catch (SQLException e) {
			log.error("Failed to retrieve data from payments.");
		}
		return payments;
	}

}
