package com.bkm.shop.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bkm.shop.dao.UserDao;
import com.bkm.shop.model.User;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	private static int paginSize = 1;

	@Override
	public User createUser(User user) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			session.save(user);
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return user;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAllUsers() {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List<User> users = null;
		try {
			users = (List<User>) session.createCriteria(User.class).list();
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return users;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUsersPagin(int page) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List<User> users = null;
		try {
			// users = (List<User>) session.createCriteria(User.class).list();

			users = (List<User>) session.createCriteria(User.class)
					.setFirstResult(paginSize * (page - 1))
					.setMaxResults(paginSize).list();
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return users;
	}

	@Override
	public int updateUser(User user) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		int curId = 0;
		try {
			curId = user.getId();
			session.update(user);
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return curId;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean deleteUser(int id) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List<User> users = null;
		boolean result = false;
		try {
			users = (List<User>) session.createCriteria(User.class)
					.add(Restrictions.eq("id", id)).list();
			session.delete(users.get(0));
			tx.commit();
			result = true;
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean checkLogin(String login) {
		boolean result = false;
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List<User> users = null;
		try {
			users = (List<User>) session.createCriteria(User.class)
					.add(Restrictions.eq("login", login)).list();
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		if (users.size() > 0) {
			result = true;
		}
		return result;
	}
}