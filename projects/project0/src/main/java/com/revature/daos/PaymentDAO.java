package com.revature.daos;

import java.util.List;

import com.revature.models.Item;
import com.revature.models.Payment;

public interface PaymentDAO {

	List<Payment> getPaymentsByItem(Item i);
	
	void createPayment(Payment p);
	
	boolean updatePayment(Payment p);
	
	List<Payment> getPayments();
}
