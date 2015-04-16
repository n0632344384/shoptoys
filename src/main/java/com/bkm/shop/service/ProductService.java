package com.bkm.shop.service;

import java.util.List;

import com.bkm.shop.model.Product;

public interface ProductService {

	Product createProduct(Product product);

	List<Product> getAllProducts();

	List<Product> getProductsPagin(int page, List<Integer> listIds);

	List<Product> getProductsPaginAll(List<Integer> listIds);

	int updateProduct(Product product);

	boolean deleteProduct(int id);
}