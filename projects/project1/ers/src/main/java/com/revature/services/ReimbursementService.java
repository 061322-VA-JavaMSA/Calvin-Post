package com.revature.services;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.daos.ReimbursementDAO;
import com.revature.daos.ReimbursementHibernate;
import com.revature.exceptions.NotCreatedException;
import com.revature.exceptions.NotFoundException;
import com.revature.models.Reimbursement;
import com.revature.models.User;

public class ReimbursementService {

	private ReimbursementDAO rd = new ReimbursementHibernate();
	private static Logger log = LogManager.getLogger(ReimbursementService.class);

	public Reimbursement createReimbursement(Reimbursement r) throws NotCreatedException {
		Reimbursement created = rd.insertReimbursement(r);
		if (created.getId() == -1) {
			throw new NotCreatedException();
		}
		log.info("Created new reimbursement" + created);
		return created;
	}

	public Reimbursement updateReimbursement(Reimbursement r) {
		Reimbursement updated = rd.updateReimbursement(r);
		log.info("Updated reimbursement " + updated);
		return updated;
	}

	public List<Reimbursement> getReimbursements() throws NotFoundException {
		List<Reimbursement> reimbs = rd.retrieveReimbursements();
		if (reimbs == null || reimbs.isEmpty()) {
			throw new NotFoundException();
		}
		log.info("Retrieved " + reimbs.size() + " reimbursements.");
		return reimbs;
	}

	public List<Reimbursement> getReimbursementsByAuthor(User u) throws NotFoundException {
		List<Reimbursement> reimbs = rd.retrieveReimbursementsByAuthor(u);
		if (reimbs == null || reimbs.isEmpty()) {
			throw new NotFoundException();
		}
		log.info("Retrived " + reimbs.size() + " reimbursements for author: " + u);
		return rd.retrieveReimbursementsByAuthor(u);
	}

	public Reimbursement getReimbursementById(int id) throws NotFoundException {
		Reimbursement r = rd.retrieveReimbursementById(id);
		if (r == null) {
			throw new NotFoundException();
		}
		log.info("Retrieved reimbursement: " + r);
		return r;
	}
}
