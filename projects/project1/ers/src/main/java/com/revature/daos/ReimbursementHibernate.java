package com.revature.daos;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import com.revature.models.ReimbStatus;
import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.util.HibernateUtil;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class ReimbursementHibernate implements ReimbursementDAO {

	@Override
	public Reimbursement insertReimbursement(Reimbursement r) {
		try (Session s = HibernateUtil.getSessionFactory().openSession()) {
			Transaction tx = s.beginTransaction();
			int id = (int) s.save(r);
			r.setId(id);
			tx.commit();
		} catch (ConstraintViolationException e) {
			// log it
		}
		return r;
	}
	
	@Override
	public List<Reimbursement> retrieveReimbursements() {
		List<Reimbursement> reimbs = null;
		
		try (Session s = HibernateUtil.getSessionFactory().openSession()) {
			reimbs = s.createQuery("from Reimbursement", Reimbursement.class).list();
		}
		return reimbs;
	}

	@Override
	public List<Reimbursement> retrieveReimbursementsByAuthorId(int id) {
		List<Reimbursement> reimbs = null;

		try (Session s = HibernateUtil.getSessionFactory().openSession()) {
			CriteriaBuilder cb = s.getCriteriaBuilder();
			CriteriaQuery<Reimbursement> cq = cb.createQuery(Reimbursement.class);
			Root<Reimbursement> root = cq.from(Reimbursement.class);

			Predicate pFromUser = cb.equal(root.get("author"), id);
			cq.select(root).where(pFromUser);

			reimbs = s.createQuery(cq).list();
		}
		return reimbs;
	}

	@Override
	public List<Reimbursement> retrieveReimbursementsByResolverId(int id) {
		List<Reimbursement> reimbs = null;

		try (Session s = HibernateUtil.getSessionFactory().openSession()) {
			CriteriaBuilder cb = s.getCriteriaBuilder();
			CriteriaQuery<Reimbursement> cq = cb.createQuery(Reimbursement.class);
			Root<Reimbursement> root = cq.from(Reimbursement.class);

			Predicate pFromUser = cb.equal(root.get("resolver"), id);
			cq.select(root).where(pFromUser);

			reimbs = s.createQuery(cq).list();
		}
		return reimbs;
	}

	@Override
	public Reimbursement updateReimbursement(Reimbursement r) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reimbursement> retrieveReimbursementsByStatus(ReimbStatus rs) {
		List<Reimbursement> reimbs = null;

		try (Session s = HibernateUtil.getSessionFactory().openSession()) {
			CriteriaBuilder cb = s.getCriteriaBuilder();
			CriteriaQuery<Reimbursement> cq = cb.createQuery(Reimbursement.class);
			Root<Reimbursement> root = cq.from(Reimbursement.class);

			Predicate pFromStatus = cb.equal(root.get("status"), rs.getId());
			cq.select(root).where(pFromStatus);

			reimbs = s.createQuery(cq).list();
		}
		return reimbs;
	}

}
