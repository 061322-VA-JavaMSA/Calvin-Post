package com.revature.daos;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import com.revature.models.Role;
import com.revature.models.User;
import com.revature.util.HibernateUtil;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class UserHibernate implements UserDAO {

	@Override
	public User retrieveUserById(int id) {
		User u = null;

		try (Session s = HibernateUtil.getSessionFactory().openSession();) {
			u = s.get(User.class, id);
		}

		return u;
	}

	@Override
	public User insertUser(User u) {
		try (Session s = HibernateUtil.getSessionFactory().openSession()) {
			Transaction tx = s.beginTransaction();
			int id = (int) s.save(u);
			u.setId(id);
			tx.commit();
		} catch (ConstraintViolationException e) {
			//log it
		}
		return u;
	}

	@Override
	public User retrieveUserByUsername(String username) {
		User u = null;

		try (Session s = HibernateUtil.getSessionFactory().openSession();) {
			CriteriaBuilder cb = s.getCriteriaBuilder();
			CriteriaQuery<User> cq = cb.createQuery(User.class);
			Root<User> root = cq.from(User.class);
			
			Predicate pUname = cb.equal(root.get("username"), username);
			cq.select(root).where(pUname);
			
			u = (User) s.createQuery(cq).getSingleResult();
		}

		return u;
	}

	@Override
	public User retrieveUserByEmail(String email) {
		User u = null;

		try (Session s = HibernateUtil.getSessionFactory().openSession();) {
			CriteriaBuilder cb = s.getCriteriaBuilder();
			CriteriaQuery<User> cq = cb.createQuery(User.class);
			Root<User> root = cq.from(User.class);
			
			Predicate pEmail = cb.equal(root.get("email"), email);
			cq.select(root).where(pEmail);
			
			u = s.createQuery(cq).getSingleResult();
		}

		return u;
	}

	@Override
	public List<User> retrieveUsers() {
		List<User> users = null;

		try (Session s = HibernateUtil.getSessionFactory().openSession();) {
			users = s.createQuery("from User", User.class).list();
		}

		return users;
	}
	
	@Override
	public List<User> retrieveUsersByRole(Role r) {
		List<User> users = null;

		try (Session s = HibernateUtil.getSessionFactory().openSession()) {
			CriteriaBuilder cb = s.getCriteriaBuilder();
			CriteriaQuery<User> cq = cb.createQuery(User.class);
			Root<User> root = cq.from(User.class);

			Predicate pFromStatus = cb.equal(root.get("role"), r);
			cq.select(root).where(pFromStatus);

			users = s.createQuery(cq).list();
		}
		return users;
	}

	@Override
	public User updateUser(User u) {
		try (Session s = HibernateUtil.getSessionFactory().openSession()) {
			Transaction tx = s.beginTransaction();
			s.update("User", u);
			tx.commit();
		} catch (ConstraintViolationException e) {
			// log it
		}
		return u;
	}

}
