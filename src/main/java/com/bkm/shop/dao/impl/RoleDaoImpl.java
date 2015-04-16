package com.bkm.shop.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bkm.shop.dao.RoleDao;
import com.bkm.shop.model.Role;

@Repository
public class RoleDaoImpl implements RoleDao {

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<Role> getAllRoles() {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List<Role> roles = null;
		try {
			roles = (List<Role>) session.createCriteria(Role.class).list();
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return roles;
	}
}