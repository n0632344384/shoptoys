package com.bkm.shop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bkm.shop.dao.ProductDao;
import com.bkm.shop.model.Product;
import com.bkm.shop.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao productDao;

	@Override
	public Product createProduct(Product product) {
		return productDao.createProduct(product);
	}

	@Override
	public List<Product> getAllProducts() {
		return productDao.getAllProducts();
	}

	public List<Product> getProductsPagin(int page, List<Integer> listIds) {
		return productDao.getProductsPagin(page, listIds);
	}

	public List<Product> getProductsPaginAll(List<Integer> listIds) {
		return productDao.getProductsPaginAll(listIds);
	}
	
	@Override
	public int updateProduct(Product product) {
		return productDao.updateProduct(product);
	}
	
	@Override
	public boolean deleteProduct(int id) {
		return productDao.deleteProduct(id);
	}
}