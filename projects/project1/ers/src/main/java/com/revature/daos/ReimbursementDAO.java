package com.revature.daos;

import java.util.List;

import com.revature.models.ReimbStatus;
import com.revature.models.Reimbursement;
import com.revature.models.User;

public interface ReimbursementDAO {

	Reimbursement insertReimbursement(Reimbursement r);
	
	List<Reimbursement> retrieveReimbursements();
	
	List<Reimbursement> retrieveReimbursementsByAuthorId(int id);
	
	List<Reimbursement> retrieveReimbursementsByResolverId(int id);
	
	Reimbursement updateReimbursement(Reimbursement r);
	
	List<Reimbursement> retrieveReimbursementsByStatus(ReimbStatus rs);
}
