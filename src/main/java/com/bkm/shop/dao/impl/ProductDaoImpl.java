package com.bkm.shop.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bkm.shop.dao.ProductDao;
import com.bkm.shop.model.Product;

@Repository
public class ProductDaoImpl implements ProductDao {

	@Autowired
	private SessionFactory sessionFactory;

	private int numOfRecords;
	private static int paginSize = 2;

	@Override
	public Product createProduct(Product product) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			session.save(product);
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return product;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> getAllProducts() {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List<Product> products = null;
		try {
			products = (List<Product>) session.createCriteria(Product.class)
					.list();
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return products;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> getProductsPagin(int page, List<Integer> listIds) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List<Product> products = null;
		try {
			if (listIds.size() == 0) {
				products = (List<Product>) session
						.createCriteria(Product.class)
						.setFirstResult(paginSize * (page - 1))
						.setMaxResults(paginSize).list();
			} else {
				products = (List<Product>) session
						.createCriteria(Product.class)
						.add(Restrictions.in("category_id", listIds))
						.setFirstResult(paginSize * (page - 1))
						.setMaxResults(paginSize).list();
			}
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return products;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> getProductsPaginAll(List<Integer> listIds) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List<Product> products = null;
		try {
			if (listIds.size() == 0) {
				products = (List<Product>) session
						.createCriteria(Product.class).list();
			} else {
				products = (List<Product>) session
						.createCriteria(Product.class)
						.add(Restrictions.in("category_id", listIds)).list();
			}
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return products;
	}

	public int getNumOfRecords() {
		return numOfRecords;
	}

	@Override
	public int updateProduct(Product product) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		int curId = 0;
		try {
			curId = product.getId();
			session.update(product);
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
	public boolean deleteProduct(int id) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List<Product> products = null;
		boolean result = false;
		try {
			products = (List<Product>) session.createCriteria(Product.class)
					.add(Restrictions.eq("id", id)).list();
			session.delete(products.get(0));
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
}