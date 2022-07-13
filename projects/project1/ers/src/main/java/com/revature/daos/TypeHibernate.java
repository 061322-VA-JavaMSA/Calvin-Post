package com.revature.daos;

import org.hibernate.Session;

import com.revature.models.ReimbType;
import com.revature.util.HibernateUtil;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class TypeHibernate implements TypeDAO {

	@Override
	public ReimbType retrieveTypeByValue(String value) {
		ReimbType rs = null;

		try (Session s = HibernateUtil.getSessionFactory().openSession();) {
			CriteriaBuilder cb = s.getCriteriaBuilder();
			CriteriaQuery<ReimbType> cq = cb.createQuery(ReimbType.class);
			Root<ReimbType> root = cq.from(ReimbType.class);
			
			Predicate pValue = cb.equal(root.get("type"), value);
			cq.select(root).where(pValue);
			
			rs = (ReimbType) s.createQuery(cq).getSingleResult();
		}

		return rs;
	}

	@Override
	public ReimbType retrieveTypeById(int id) {
		ReimbType rs = null;

		try (Session s = HibernateUtil.getSessionFactory().openSession();) {
			rs = s.get(ReimbType.class, id);
		}

		return rs;
	}
}
