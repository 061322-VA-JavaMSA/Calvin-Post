package com.revature.daos;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import com.revature.models.User;
import com.revature.util.HibernateUtil;

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
		u.setId(-1);
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
			u = s.get(User.class, username);
		}

		return u;
	}

	@Override
	public User retrieveUserByEmail(String email) {
		User u = null;

		try (Session s = HibernateUtil.getSessionFactory().openSession();) {
			u = s.get(User.class, email);
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

}
