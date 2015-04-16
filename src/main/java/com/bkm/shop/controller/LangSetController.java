package com.bkm.shop.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LangSetController {

	final static Logger logger = Logger.getLogger("info");

	/**
	 * setLang sets current language at page
	 * 
	 * @param request
	 * @param newLang
	 * @param curUrl
	 * @param curUrlParam
	 * @return
	 */
	@RequestMapping(value = "/setLang", method = RequestMethod.POST)
	public String setLang(HttpServletRequest request,
			@RequestParam("selectedLang") String newLang,
			@RequestParam("curUrl") String curUrl,
			@RequestParam("curUrlParam") String curUrlParam) {

		logger.info("LangSetController.setLang() started");
		logger.info("PARAMS: curUlr: " + curUrl + ", curUrlParam: "
				+ curUrlParam + ", selectedLang: " + newLang);

		request.getSession().setAttribute("curShopLang", newLang);
		String result = saveCurUrlAndParam(curUrl, curUrlParam);

		logger.info("return: " + result);
		logger.info("LangSetController.setLang() completed");
		return result;
	}

	/**
	 * saveCurUrlAndParam saves current url and params
	 * 
	 * @param curUrl
	 * @param curUrlParam
	 * @return
	 */
	private String saveCurUrlAndParam(String curUrl, String curUrlParam) {
		if (curUrlParam.equals("/")) {
			curUrlParam = "";
		} else {
			curUrlParam = "?" + curUrlParam;
		}

		String result = "";
		switch (curUrl) {
		case "/":
			result = "redirect:/" + curUrlParam;
			break;
		case "/register":
			result = "redirect:/register";
			break;
		case "/mandator":
			result = "redirect:/mandator" + curUrlParam;
			break;
		case "/admin":
			result = "redirect:/admin" + curUrlParam;
			break;
		default:
			result = "redirect:/";
			break;
		}
		return result;
	}
}