package com.revature.services;

import java.time.LocalDate;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.daos.ItemDAO;
import com.revature.daos.ItemPostgres;
import com.revature.daos.PaymentDAO;
import com.revature.daos.PaymentPostgres;
import com.revature.models.Item;
import com.revature.models.Payment;
import com.revature.util.Table;
import com.revature.util.Util;

public class PaymentService {

	private PaymentDAO pd = new PaymentPostgres();
	private ItemDAO id = new ItemPostgres();
	private Logger log = LogManager.getLogger(PaymentService.class);

	public void viewPaymentsByItem(Item i) {
		List<Payment> payments = pd.getPaymentsByItem(i);
		double bal = i.getBalance();
		double pay;

		Table.title("Payments for " + i.getName());
		if (bal > 0) {
			Table.row(String.format("%-20s      $ %6.2f", "Remaining balance:", bal));
			Util.hr();

			Table.header(String.format("%-10s    %-6s    %10s", "Due Date", "Status", "Amount Due"));
			for (Payment p : payments) {
				if (p.getStatus().equals("unpaid")) {
					Table.row(String.format("%-10s    %-6s      $ %6.2f", p.getDateDue().toString(), p.getStatus(),
							p.getAmountDue()));
				}
			}
			Util.hr();
			Util.println();
			Util.println("Enter an amount to make a payment. Press ENTER to return.");
			Util.hr();
			String choice = Util.in.nextLine();
			if (choice.equals("")) {
				
			} else if (Util.isDouble(choice)) {
				pay = Math.floor(Double.parseDouble(choice) * 100.0) / 100.0;
				if (pay < bal) {
					bal -= pay;
					i.setBalance(bal);
				} else {
					i.setBalance(0);
				}
				id.updateItemBalance(i);
				for (Payment p : payments) {
					if (p.getStatus().equals("unpaid")) {
						if (pay > p.getAmountDue()) {
							pay -= p.getAmountDue();
							p.setStatus("paid");
							pd.updatePayment(p);
						} else {
							p.setAmountDue(p.getAmountDue() - pay);
							pd.updatePayment(p);
							pay = 0;
							break;
						}
					}
				}
				if (pay > 0) {
					Util.println(String.format("Refunded $%5.2f", pay));
					Util.pause();
				}
				this.viewPaymentsByItem(i);
			} else {
				Util.invalid();
				this.viewPaymentsByItem(i);
			}
		} else {
			Table.row(String.format("%32s", "Paid in full"));
			Util.hr();
			Util.pause();
		}
	}

	public void updatePaymentStatus(Payment p) {
		pd.updatePayment(p);
	}

	public void viewPayments() {
		List<Payment> payments = pd.getPayments();

		Table.title("View All Payments");

		Table.header(String.format("%-10s    %-6s    %10s", "Due Date", "Status", "Amount Due"));
		for (Payment p : payments) {
			Table.row(String.format("%-10s    %-6s      $ %6.2f", p.getDateDue().toString(), p.getStatus(),
					p.getAmountDue()));
		}
		Util.hr();
		Util.pause();
	}
}
