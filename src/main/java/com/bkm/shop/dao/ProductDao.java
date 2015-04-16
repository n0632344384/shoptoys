package com.bkm.shop.dao;

import java.util.List;

import com.bkm.shop.model.Product;

public interface ProductDao {

	// creating new product
	Product createProduct(Product product);

	// get all existing products
	List<Product> getAllProducts();
	
	// get products with pagination 
	// which contains only list of id passed
	List<Product> getProductsPagin(int page, List<Integer> listIds);
	
	// count all pagin products
	List<Product> getProductsPaginAll(List<Integer> listIds);

	// update product by product and returns updated product id
	int updateProduct(Product product);

	// delete product by id and return true if product deleted
	boolean deleteProduct(int id);
}