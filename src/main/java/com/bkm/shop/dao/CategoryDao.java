package com.bkm.shop.dao;

import java.util.List;

import com.bkm.shop.model.Category;

public interface CategoryDao {

	// get all existed categories
	List<Category> getAllCategories();
	
	// get categories with pagination
	List<Category> getCategoriesPagin(int page);
}
