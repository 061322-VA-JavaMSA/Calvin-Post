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

import com.revature.Driver;
import com.revature.models.Item;
import com.revature.models.Payment;
import com.revature.util.ConnectionUtil;

public class PaymentPostgres implements PaymentDAO {
	
	private ItemDAO id = new ItemPostgres();
	private static Logger log = LogManager.getLogger(PaymentPostgres.class);

	@Override
	public List<Payment> getPaymentsByItem(Item i) {
		List<Payment> payments = new ArrayList<>();
		String sql = "select * from payments where item_id = " + i.getId() + " order by date_due asc;";
		
		try(Connection c = ConnectionUtil.getConnectionFromFile()) {
			
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);
			
			while(rs.next()) {
				Payment p = new Payment();
				p.setId(rs.getInt("id"));
				p.setDateDue(rs.getDate("date_due").toLocalDate());
				p.setStatus(rs.getString("status"));
				p.setAmountDue(rs.getDouble("amount_due"));
				p.setItem(i);
				
				payments.add(p);
			}
		} catch(SQLException e) {
			log.error(e.getSQLState(), e.getMessage());
		}
		return payments;
	}

	@Override
	public void createPaymentsByItem(Item i) {
		double payment = i.getBalance() / 4;
		LocalDate date = LocalDate.now().plusDays(7);
		String sql = "insert into payments (date_due, amount_due, item_id) values (?, ?, ?);";
		
		try(Connection c = ConnectionUtil.getConnectionFromFile()) {
			for(int n = 0; n < 4; n ++) {
				PreparedStatement ps = c.prepareStatement(sql);
				ps.setDate(1, Date.valueOf(date));
				ps.setDouble(2, payment);
				ps.setInt(3, i.getId());
				ps.execute();
				
				date = date.plusDays(7);
			}
		} catch(SQLException e) {
			log.error(e.getSQLState(), e.getMessage());
		}
	}
	
	@Override
	public void updatePayment(Payment p) {
		String sql = "update payments set status = ?, amount_due = ? where id = ?;";
		
		try(Connection c = ConnectionUtil.getConnectionFromFile()) {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, p.getStatus());
			ps.setDouble(2, p.getAmountDue());
			ps.setInt(3, p.getId());
			ps.executeUpdate();
			
		} catch(SQLException e) {
			log.error(e.getSQLState(), e.getMessage());
		}
	}
	
	@Override
	public List<Payment> getPayments(){
		String sql = "select * from payments order by status desc, date_due asc;";
		List<Payment> payments = new ArrayList<>();
		
		try(Connection c = ConnectionUtil.getConnectionFromFile()) {
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);
			
			while(rs.next()) {
				Payment p = new Payment();
				p.setId(rs.getInt("id"));
				p.setDateDue(rs.getDate("date_due").toLocalDate());
				p.setAmountDue(rs.getDouble("amount_due"));
				Item i = id.getItemById(rs.getInt("item_id"));
				p.setItem(i);
				p.setStatus(rs.getString("status"));
				
				payments.add(p);
			}
			
		} catch(SQLException e) {
			log.error(e.getSQLState(), e.getMessage());
		}
		return payments;
	}

}
