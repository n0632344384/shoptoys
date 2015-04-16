package com.bkm.shop.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bkm.shop.model.Basket;

@Controller
public class BasketController {

	final static Logger logger = Logger.getLogger("info");

	/**
	 * addToBasket adds products to basket
	 * 
	 * @param request
	 * @param curPaginationCurPage
	 * @param productId
	 * @param productName
	 * @param productQuantity
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/addToBasket", method = RequestMethod.POST)
	public String addToBasket(HttpServletRequest request,
			@RequestParam(value = "productId") String productId,
			@RequestParam(value = "productName") String productName,
			@RequestParam(value = "productQuantity") String productQuantity) {

		logger.info("BasketController.addToBasket() started");
		logger.info("PARAMS: productId: " + productId + ", productName: "
				+ productName + ", productQuantity: " + productQuantity);

		List<Basket> basket = null;
		// if choosed some quantity of products
		if (Integer.valueOf(productQuantity) > 0) {
			Basket basketProduct = productAddToBasket(productId, productName,
					productQuantity);

			List<Basket> oldBasket = (List<Basket>) request.getSession()
					.getAttribute("basket");
			logger.info("BASKET: " + oldBasket);

			// if basket already created
			if (oldBasket != null) {
				basket = oldBasket;
				addProductToBasket(basket, basketProduct);
				request.getSession().removeAttribute("basket");
				request.getSession().setAttribute("basket", basket);
			} else {
				basket = new ArrayList<Basket>();
				basket.add(basketProduct);
				request.getSession().setAttribute("basket", basket);
			}
		}

		logger.info("NEW BASKET: " + basket);
		logger.info("BasketController.addToBasket() completed");
		return "printBasket";
	}

	/**
	 * productAddToBasket prepare product that we need to add to basket
	 * 
	 * @param productId
	 * @param productName
	 * @param productQuantity
	 * @return
	 */
	private Basket productAddToBasket(String productId, String productName,
			String productQuantity) {
		Basket basketProduct = new Basket();
		basketProduct.setProductId(productId);
		basketProduct.setProductName(productName);
		basketProduct.setProductQuantity(productQuantity);
		return basketProduct;
	}

	/**
	 * addProductToBasket add product to basket
	 * 
	 * @param basket
	 * @param basketProduct
	 */
	private void addProductToBasket(List<Basket> basket, Basket basketProduct) {
		// add product to basket, and if product already exist sum
		// products
		boolean addedProductToBusket = true;
		ListIterator<Basket> litr = basket.listIterator();
		while (litr.hasNext()) {
			Basket curBasket = litr.next();

			if (curBasket.getProductId().equals(basketProduct.getProductId())) {
				int newQuantity = Integer.valueOf(basketProduct
						.getProductQuantity())
						+ Integer.valueOf(curBasket.getProductQuantity());
				basketProduct.setProductQuantity(String.valueOf(newQuantity));
				litr.remove();
				litr.add(basketProduct);
				addedProductToBusket = false;
			}
		}
		if (addedProductToBusket) {
			basket.add(basketProduct);
		}
	}

	/**
	 * delFromBasket removes products from basket
	 * 
	 * @param productId
	 * @param page
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/delFromBasket", method = RequestMethod.GET)
	public String delFromBasket(HttpServletRequest request,
			@RequestParam(value = "productId") String productId) {
		logger.info("BasketController.delFromBasket() started");
		logger.info("PARAMS: productId: " + productId);

		delFromBasketProduct(request.getSession().getAttribute("basket"),
				productId);

		logger.info("BasketController.delFromBasket() completed");
		return "printBasket";
	}

	/**
	 * delFromBasketProduct removes from basket current product by id
	 * 
	 * @param basket
	 * @param productId
	 */
	@SuppressWarnings("unchecked")
	private void delFromBasketProduct(Object basket, String productId) {
		List<Basket> newBasket = (List<Basket>) basket;
		ListIterator<Basket> litr = newBasket.listIterator();
		while (litr.hasNext()) {
			Basket curBasket = litr.next();
			if (curBasket.getProductId().equals(productId)) {
				litr.remove();
			}
		}
	}

	/**
	 * printBasket reprints printBasket.jsp
	 * 
	 * @return
	 */
	@RequestMapping(value = "/printBasket", method = RequestMethod.GET)
	public String printBasket() {
		logger.info("BasketController.delFromBasket() started");
		logger.info("BasketController.delFromBasket() completed");
		return null;
	}
}