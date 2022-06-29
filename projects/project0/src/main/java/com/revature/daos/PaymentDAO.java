package com.revature.daos;

import java.util.List;

import com.revature.models.Item;
import com.revature.models.Payment;

public interface PaymentDAO {

	List<Payment> getPaymentsByItem(Item i);
	
	void createPaymentsByItem(Item i);
	
	void updatePayment(Payment p);
	
	List<Payment> getPayments();
}
