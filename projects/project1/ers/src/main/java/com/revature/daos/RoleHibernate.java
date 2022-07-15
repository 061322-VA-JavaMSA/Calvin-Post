package com.revature.daos;

import org.hibernate.Session;

import com.revature.models.Role;
import com.revature.util.HibernateUtil;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class RoleHibernate implements RoleDAO {

	@Override
	public Role retrieveRoleByValue(String value) {
		Role r = null;

		try (Session s = HibernateUtil.getSessionFactory().openSession();) {
			CriteriaBuilder cb = s.getCriteriaBuilder();
			CriteriaQuery<Role> cq = cb.createQuery(Role.class);
			Root<Role> root = cq.from(Role.class);
			
			Predicate pValue = cb.equal(root.get("role"), value);
			cq.select(root).where(pValue);
			
			r = (Role) s.createQuery(cq).getSingleResult();
		}

		return r;
	}

	@Override
	public Role retrieveRoleById(int id) {
		Role r = null;

		try (Session s = HibernateUtil.getSessionFactory().openSession();) {
			r = s.get(Role.class, id);
		}

		return r;
	}
}
