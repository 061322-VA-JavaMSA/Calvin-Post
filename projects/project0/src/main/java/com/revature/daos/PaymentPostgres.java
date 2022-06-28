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

import com.revature.models.Item;
import com.revature.models.Payment;
import com.revature.util.ConnectionUtil;

public class PaymentPostgres implements PaymentDAO {
	
	private ItemDAO id = new ItemPostgres();

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
			e.printStackTrace();
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
			e.printStackTrace();
		}
	}
	
	@Override
	public void updatePaymentStatus(Payment p) {
		String sql = "update payments set status = '" + p.getStatus() + "' where id = " + p.getId() + ";";
		
		try(Connection c = ConnectionUtil.getConnectionFromFile()) {
			Statement s = c.createStatement();
			s.execute(sql);
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public List<Payment> getPayments(){
		String sql = "select * from payments;";
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
			e.printStackTrace();
		}
		return payments;
	}

}
