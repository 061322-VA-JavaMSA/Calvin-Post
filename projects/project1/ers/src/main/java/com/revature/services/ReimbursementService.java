package com.revature.services;

import java.util.List;

import com.revature.daos.ReimbursementDAO;
import com.revature.daos.ReimbursementHibernate;
import com.revature.exceptions.NotCreatedException;
import com.revature.exceptions.NotFoundException;
import com.revature.exceptions.NotUpdatedException;
import com.revature.models.Reimbursement;
import com.revature.models.User;

public class ReimbursementService {

	private ReimbursementDAO rd = new ReimbursementHibernate();
	
	public Reimbursement createReimbursement(Reimbursement r) throws NotCreatedException {
		Reimbursement created = rd.insertReimbursement(r);
		if (created.getId() == -1) {
			throw new NotCreatedException();
		}
		return created;
	}
	
	public Reimbursement updateReimbursement(Reimbursement r){
		return rd.updateReimbursement(r);
	}
	
	public List<Reimbursement> getReimbursements() {
		return rd.retrieveReimbursements();
	}
	
	public List<Reimbursement> getReimbursementsByAuthor(User u) {
		return rd.retrieveReimbursementsByAuthor(u);
	}
	
	public Reimbursement getReimbursementById(int id) throws NotFoundException {
		Reimbursement r = rd.retrieveReimbursementById(id);
		if (r == null) {
			throw new NotFoundException();
		}
		return r;
	}
}
