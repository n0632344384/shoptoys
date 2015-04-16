package com.bkm.shop.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bkm.shop.dao.AddressDao;
import com.bkm.shop.model.Address;

@Repository
public class AddressDaoImpl implements AddressDao {

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<Address> getAllAddresses() {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List<Address> addresses = null;
		try {
			addresses = (List<Address>) session.createCriteria(Address.class)
					.list();
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return addresses;
	}

	@Override
	public Address getAddressById(int id) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Address address = null;
		try {
			address = (Address) session.createCriteria(Address.class)
					.add(Restrictions.eq("id", id)).uniqueResult();
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return address;
	}

	@Override
	public int updateAddress(Address address) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		int curId = 0;
		try {
			curId = address.getId();
			session.update(address);
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

	@Override
	public int createAddressGetIdCreated(Address address) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		int curId = 0;
		try {
			session.save(address);
			curId = address.getId();
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
}