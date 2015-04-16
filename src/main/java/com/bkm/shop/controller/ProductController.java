package com.bkm.shop.controller;

import java.util.ArrayList;
import java.util.Enumeration;
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
public class ProductController {

	final static Logger logger = Logger.getLogger("info");

	@Autowired
	private ProductService productService;

	@Autowired
	private CategoryService categoryService;

	/**
	 * printProducts prints products on main page
	 * 
	 * @param request
	 * @param curPage
	 * @return
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String printProducts(HttpServletRequest request,
			@RequestParam(value = "page", required = false) String curPage) {
		logger.info("ProductController.printProducts() started");
		logger.info("PARAMS: page: " + curPage);

		List<Integer> listIds = new ArrayList<>();
		prepareProductsToPrint(request, curPage, listIds, true);

		List<Category> categories = categoryService.getAllCategories();
		logger.info("VARS: categories: " + categories);
		request.setAttribute("categories", categories);

		logger.info("ProductController.printProducts() completed");
		return "/index";
	}

	/**
	 * postPrintProducts prints products by post request
	 * 
	 * @param request
	 * @param curPage
	 * @return
	 */
	@RequestMapping(value = "/printProducts", method = RequestMethod.POST)
	public String postPrintProducts(HttpServletRequest request,
			@RequestParam(value = "page", required = false) String curPage) {
		logger.info("ProductController.postPrintProducts() started");

		List<Integer> listIds = new ArrayList<>();
		listIds = setFilterCategory(request, listIds);
		prepareProductsToPrint(request, curPage, listIds, false);

		logger.info("nProductController.postPrintProducts() completed");
		return "printProducts";
	}

	/**
	 * prepareProductsToPrint prepares products to print
	 * 
	 * @param request
	 * @param curPage
	 * @param listIds
	 * @param ifHome
	 * @return
	 */
	private HttpServletRequest prepareProductsToPrint(
			HttpServletRequest request, String curPage, List<Integer> listIds,
			boolean ifHome) {
		int page = 1;
		if (curPage != null) {
			page = Integer.parseInt(curPage);
		}
		int recordsPerPage = 2;
		int numOfRecords = 0;
		int numOfPages = 0;

		List<Product> productsFiltered = null;
		productsFiltered = productService.getProductsPagin(page, listIds);
		numOfRecords = productService.getProductsPaginAll(listIds).size();
		numOfPages = (int) Math.ceil(numOfRecords * 1.0 / recordsPerPage);

		logger.info("VARS: numOfPages: " + numOfPages + ", currentPage" + page
				+ ", numOfRecords" + numOfRecords + ", products"
				+ productsFiltered);

		if (ifHome) {
			request.setAttribute("numOfPages", numOfPages);
			request.setAttribute("currentPage", page);
			request.setAttribute("numOfRecords", numOfRecords);
			request.setAttribute("products", productsFiltered);
		} else {
			request.getSession().setAttribute("numOfPages", numOfPages);
			request.getSession().setAttribute("currentPage", page);
			request.getSession().setAttribute("numOfRecords", numOfRecords);
			request.getSession().setAttribute("products", productsFiltered);
		}
		return request;
	}

	/**
	 * setFilterCategory returns filters of categories
	 * 
	 * @param request
	 * @param listIds
	 * @return
	 */
	private List<Integer> setFilterCategory(HttpServletRequest request,
			List<Integer> listIds) {
		String filterCategory = "";
		Enumeration<String> parameterNames = request.getParameterNames();
		while (parameterNames.hasMoreElements()) {
			String paramName = parameterNames.nextElement();
			if (paramName.equals("categoryId")) {
				String[] paramValues = request.getParameterValues(paramName);
				for (int i = 0; i < paramValues.length; i++) {
					String paramValue = paramValues[i];
					listIds.add(Integer.valueOf(paramValue));
					filterCategory = filterCategory + "&categoryId="
							+ paramValue;
				}
			}
		}
		request.getSession().setAttribute("filterCategory", filterCategory);
		logger.info("VARS: filterCategory: " + filterCategory + ", listIds"
				+ listIds.toString());
		return listIds;
	}

	/**
	 * getPrintProducts loads printProducts.jsp
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/printProducts", method = RequestMethod.GET)
	public String getPrintProducts(HttpServletRequest request) {
		logger.info("ProductController.getPrintProducts() started");

		request.setAttribute("products",
				request.getSession().getAttribute("products"));
		request.setAttribute("numOfPages",
				request.getSession().getAttribute("numOfPages"));
		request.setAttribute("currentPage",
				request.getSession().getAttribute("currentPage"));
		request.setAttribute("numOfRecords",
				request.getSession().getAttribute("numOfRecords"));
		request.setAttribute("filterCategory", request.getSession()
				.getAttribute("filterCategory"));

		request.getSession().removeAttribute("products");
		request.getSession().removeAttribute("numOfPages");
		request.getSession().removeAttribute("currentPage");
		request.getSession().removeAttribute("numOfRecords");
		request.getSession().removeAttribute("filterCategory");

		logger.info("ProductController.getPrintProducts() completed");
		return null;
	}
}