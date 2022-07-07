package com.revature.services;

import java.util.List;

import com.revature.daos.ReimbursementDAO;
import com.revature.daos.ReimbursementHibernate;
import com.revature.models.Reimbursement;
import com.revature.models.User;

public class ReimbursementService {

	private ReimbursementDAO rd = new ReimbursementHibernate();
	
	public List<Reimbursement> getReimbursements() {
		return rd.retrieveReimbursements();
	}
	
	public List<Reimbursement> getReimbursementsByAuthorId(int id) {
		return rd.retrieveReimbursementsByAuthor(id);
	}
}
