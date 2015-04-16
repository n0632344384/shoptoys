package com.bkm.shop.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bkm.shop.dao.CategoryDao;
import com.bkm.shop.model.Category;

@Repository
public class CategoryDaoImpl implements CategoryDao {

	@Autowired
	private SessionFactory sessionFactory;

	private static int paginSize = 1;

	@SuppressWarnings("unchecked")
	@Override
	public List<Category> getAllCategories() {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List<Category> categories = null;
		try {
			categories = (List<Category>) session
					.createCriteria(Category.class).list();
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return categories;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Category> getCategoriesPagin(int page) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List<Category> categories = null;
		try {
			categories = (List<Category>) session
					.createCriteria(Category.class)
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
		return categories;
	}
}