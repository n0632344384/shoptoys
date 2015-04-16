package com.bkm.shop.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bkm.shop.model.Category;
import com.bkm.shop.model.Product;
import com.bkm.shop.service.CategoryService;
import com.bkm.shop.service.ProductService;

@Controller
public class MandatorController {

	final static Logger logger = Logger.getLogger("info");

	@Autowired
	private ProductService productService;

	@Autowired
	private CategoryService categoryService;

	/**
	 * openHome opens current mandator page
	 * 
	 * @param request
	 * @param curOperation
	 * @return
	 */
	@RequestMapping(value = "/mandator", method = RequestMethod.GET)
	public String openHome(
			HttpServletRequest request,
			@RequestParam(value = "mandator", required = false) String curOperation) {
		logger.info("MandatorController.openHome() started");
		logger.info("PARAMS: mandator: " + curOperation);
		if (curOperation != null) {
			request.setAttribute("mandator", curOperation);
			getCategories(request);
			if (curOperation.equals("updateProduct")) {
				getProducts(request);
			}
		}
		logger.info("MandatorController.openHome() completed");
		return "/mandator";
	}

	/**
	 * getCategories gets all categories
	 * 
	 * @param request
	 */
	private void getCategories(HttpServletRequest request) {
		List<Category> categories = categoryService.getAllCategories();
		logger.info("categories: " + categories);
		request.setAttribute("categories", categories);
	}

	/**
	 * getProducts gets all products
	 * 
	 * @param request
	 */
	private void getProducts(HttpServletRequest request) {
		List<Product> products = productService.getAllProducts();
		logger.info("products: " + products);
		request.setAttribute("products", products);
	}

	/**
	 * addProduct adds product
	 * 
	 * @param request
	 * @param newName
	 * @param newDescription
	 * @param price
	 * @param newImage
	 * @param newCategory
	 * @return
	 */
	@RequestMapping(value = "/mandator/addProduct", method = RequestMethod.POST)
	public String addProduct(HttpServletRequest request,
			@RequestParam(value = "name") String newName,
			@RequestParam(value = "description") String newDescription,
			@RequestParam(value = "price") String price,
			@RequestParam(value = "image") String newImage,
			@RequestParam(value = "category") String newCategory) {
		logger.info("MandatorController.addProduct() started");
		logger.info("PARAMS: name: " + newName + ", description: "
				+ newDescription + ", price: " + price + ", image: " + newImage
				+ ", category: " + newCategory);

		int newPrice = 0;
		if (!price.equals("")) {
			newPrice = Integer.valueOf(price);
		}

		// check if all fields are filled in add product
		if (isDataValid(request, newName, newDescription, newPrice, newImage)) {
			Product newProduct = createProduct(newName, newDescription,
					newPrice, newImage, newCategory);
			productService.createProduct(newProduct);

			logger.info("addProductDone: Product added. You can add more.");
			request.getSession().setAttribute("addProductDone",
					"Product added. You can add more.");
		}
		logger.info("MandatorController.openHome() completed");
		return "redirect:/mandator?mandator=addProduct";
	}

	/**
	 * isDataValid checks if data valid
	 * 
	 * @param request
	 * @param newName
	 * @param newDescription
	 * @param newPrice
	 * @param newImage
	 * @return
	 */
	private boolean isDataValid(HttpServletRequest request, String newName,
			String newDescription, int newPrice, String newImage) {
		boolean validationResult = true;
		if (newName.equals("") | newDescription.equals("") | newPrice == 0
				| newImage.equals("")) {
			validationResult = false;
			logger.info("error: Some fields are empty. Fill in all fields please.");
			request.getSession().setAttribute("error",
					"Some fields are empty. Fill in all fields please.");
		}
		return validationResult;
	}

	/**
	 * createProduct creates product
	 * 
	 * @param newName
	 * @param newDescription
	 * @param newPrice
	 * @param newImage
	 * @param newCategory
	 * @return
	 */
	private Product createProduct(String newName, String newDescription,
			int newPrice, String newImage, String newCategory) {
		Product newProduct = new Product();
		newProduct.setName(newName);
		newProduct.setDescription(newDescription);
		newProduct.setPrice(newPrice);
		newProduct.setImage(newImage);
		newProduct.setCategory_id(Integer.valueOf(newCategory));
		logger.info("newProduct: " + newProduct);
		return newProduct;
	}

	/**
	 * updateProduct updates current product by id
	 * 
	 * @param request
	 * @param productId
	 * @param newName
	 * @param newDescription
	 * @param newPrice
	 * @param newImage
	 * @param newCategory
	 * @return
	 */
	@RequestMapping(value = "/mandator/updateProduct", method = RequestMethod.POST)
	public String updateProduct(HttpServletRequest request,
			@RequestParam(value = "productId") String productId,
			@RequestParam(value = "productName") String newName,
			@RequestParam(value = "productDescription") String newDescription,
			@RequestParam(value = "productPrice") int newPrice,
			@RequestParam(value = "productImage") String newImage,
			@RequestParam(value = "productCategory") String newCategory) {
		logger.info("MandatorController.updateProduct() started");
		logger.info("PARAMS: productId: " + productId + ", productName"
				+ newPrice + ", productImage" + newDescription
				+ ", productPrice" + newName + ", productName" + newImage
				+ ", productCategory" + newCategory);

		// check if all fields are filled in update product
		if (isDataValid(request, newName, newDescription, newPrice, newImage)) {
			Product updatedProduct = createProduct(newName, newDescription,
					newPrice, newImage, newCategory);
			updatedProduct.setId(Integer.valueOf(productId));
			productService.updateProduct(updatedProduct);
			logger.info("updateProductDone: Product with id = '" + productId
					+ "' updated.");
			request.getSession().setAttribute("updateProductDone",
					"Product with id = '" + productId + "' updated.");
		}
		logger.info("MandatorController.updateProduct() completed");
		return "redirect:/mandator?mandator=updateProduct";
	}

	/**
	 * delProduct removes current product by id
	 * 
	 * @param request
	 * @param productId
	 * @return
	 */
	@RequestMapping(value = "/mandator/delProduct", method = RequestMethod.POST)
	public String delProduct(HttpServletRequest request,
			@RequestParam(value = "productId") String productId) {
		logger.info("MandatorController.delProduct() started");
		logger.info("PARAMS: productId: " + productId);

		productService.deleteProduct(Integer.valueOf(productId));

		logger.info("delProductDone: Product with id = '" + productId
				+ "' removed.");
		request.getSession().setAttribute("delProductDone",
				"Product with id = '" + productId + "' removed.");

		logger.info("nMandatorController.delProduct() completed");
		return "redirect:/mandator?mandator=updateProduct";
	}
}