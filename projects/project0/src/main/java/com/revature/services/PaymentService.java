package com.revature.services;

import java.awt.Color;
import java.time.LocalDate;
import java.util.List;

import com.revature.daos.PaymentDAO;
import com.revature.daos.PaymentPostgres;
import com.revature.models.Item;
import com.revature.models.Payment;
import com.revature.models.User;
import com.revature.util.Table;
import com.revature.util.Util;

public class PaymentService {
	
	private PaymentDAO pd = new PaymentPostgres();

	public List<Payment> getPaymentsByItem(Item i) {
		return pd.getPaymentsByItem(i);
	}
	
	public void updatePaymentStatus(Payment p) {
		pd.updatePaymentStatus(p);
	}
	
	public void viewPayments() {
		List<Payment> payments = pd.getPayments();
		Util.clear();

		Table.title("Payment History");
//		Table.row(String.format("%-20s      $ %6.2f", "Remaining balance:", i.getBalance()));
//		Util.hr();
		
		Table.header(String.format("%-10s    %-6s    %10s", "Due Date", "Status", "Amount Due"));
		for(Payment p : payments) {
			if(p.getStatus().equals("unpaid")) {
				if(p.getDateDue().isBefore(LocalDate.now())) {
//					System.out.print(Color.RED);
				}
				Table.header(String.format("%-10s    %-6s      $ %6.2f", p.getDateDue().toString(), p.getStatus(), p.getAmountDue()));
			}
//			System.out.print(Color.BLACK);
		}
//		System.out.print(Color.GREEN);
		for(Payment p : payments) {
			if(p.getStatus().equals("paid")) {
				Table.header(String.format("%-10s    %-6s      $ %6.2f", p.getDateDue().toString(), p.getStatus(), p.getAmountDue()));
			}
		}
//		System.out.print(Color.BLACK);

//		Util.println("\n\n\n");
//		Util.println("What would you like to do?");
//		Util.println(" 1. Make a payment");
		String choice = Util.in.nextLine();
		
		//make payment
		/*
		for(Payment p : payments) {
			if(p.getStatus().equals("unpaid")) {
				p.setStatus("paid");
				ps.updatePaymentStatus(p);
				i.setBalance(i.getBalance() - p.getAmountDue());
				id.updateItemBalance(i);
				break;
			}
		}
	
		}
		*/
	}
}
