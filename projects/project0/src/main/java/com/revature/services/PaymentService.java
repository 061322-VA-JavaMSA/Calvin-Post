package com.revature.services;

import java.time.LocalDate;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.daos.ItemDAO;
import com.revature.daos.ItemPostgres;
import com.revature.daos.PaymentDAO;
import com.revature.daos.PaymentPostgres;
import com.revature.enums.Role;
import com.revature.models.Item;
import com.revature.models.Payment;

public class PaymentService {

	private PaymentDAO pd = new PaymentPostgres();
	private Logger log = LogManager.getLogger(PaymentService.class);

	public List<Payment> getPaymentsByItem(Item i) {
		if(i == null) {
			return null;
		}
		return pd.getPaymentsByItem(i);
	}

	public boolean updatePayment(Payment p) {
		if(p.getItem() == null) {
			return false;
		}
		return pd.updatePayment(p);
	}

	public boolean createPayments(Item i) {
		if(i == null) {
			return false;
		}
		double bal = i.getBalance();
		double pay = Math.round(bal * 100.0 / 4.0) / 100.0;
		LocalDate date = LocalDate.now().plusDays(7);

		for (int n = 0; n < 4; n++) {
			Payment p = new Payment();
			p.setDateDue(date);
			p.setItem(i);
			if (n < 3) {
				p.setAmountDue(pay);
				bal -= pay;
			} else {
				p.setAmountDue(bal);
			}

			date = date.plusDays(7);

			pd.createPayment(p);
		}

		log.info("Successfully created payments for '" + i.getName() + "'.");
		return true;
	}

	public List<Payment> getPayments(Role role) {
		if(role.greaterThan(Role.USER)) {
			return pd.getPayments();
		}
		return null;
	}
}
