package com.revature.daos;

import java.util.List;

import com.revature.models.ReimbStatus;
import com.revature.models.Reimbursement;
import com.revature.models.User;

public interface ReimbursementDAO {

	Reimbursement insertReimbursement(Reimbursement r);

	Reimbursement updateReimbursement(Reimbursement r);
	
	Reimbursement retrieveReimbursementById(int id);

	List<Reimbursement> retrieveReimbursements();

	List<Reimbursement> retrieveReimbursementsByAuthor(User u);

	List<Reimbursement> retrieveReimbursementsByResolver(User u);

	List<Reimbursement> retrieveReimbursementsByStatus(ReimbStatus rs);
}