package com.bkm.shop.controller;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bkm.shop.model.Address;
import com.bkm.shop.model.Role;
import com.bkm.shop.model.User;
import com.bkm.shop.service.UserService;

@Controller
public class RegisterController {

	final static Logger logger = Logger.getLogger("info");

	@Autowired
	private UserService userService;

	/**
	 * postRegister registers users
	 * 
	 * @param model
	 * @param session
	 * @param newLogin
	 * @param newLoginPass
	 * @param newLoginPass2
	 * @param newFName
	 * @param newLName
	 * @param newEmail
	 * @param newAccount
	 * @param newZip
	 * @param newState
	 * @param newCity
	 * @param newStreet
	 * @param newPhone
	 * @param newAge
	 * @param newGender
	 * @return
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String postRegister(Model model, HttpSession session,
			@RequestParam(value = "login") String newLogin,
			@RequestParam(value = "pass1") String newLoginPass,
			@RequestParam(value = "pass2") String newLoginPass2,
			@RequestParam(value = "fname") String newFName,
			@RequestParam(value = "lname") String newLName,
			@RequestParam(value = "email") String newEmail,
			@RequestParam(value = "account") String newAccount,
			@RequestParam(value = "zip") String newZip,
			@RequestParam(value = "state") String newState,
			@RequestParam(value = "city") String newCity,
			@RequestParam(value = "street") String newStreet,
			@RequestParam(value = "phone") String newPhone,
			@RequestParam(value = "age") String newAge,
			@RequestParam(value = "gender") String newGender) {

		logger.info("RegisterController.postRegister() started");
		logger.info("PARAMETERS: login: " + newLogin + ", pass1" + newLoginPass
				+ ", pass2" + newLoginPass2 + ", fname" + newFName + ", lname"
				+ newLName + ", email" + newEmail + ", account" + newAccount
				+ ", zip" + newZip + ", state" + newState + ", city" + newCity
				+ ", street" + newStreet + ", phone" + newPhone + ", age"
				+ newAge + ", gender" + newGender);

		// check if login exists
		if (isDataValid(newLogin, newLoginPass, newLoginPass2, newFName,
				newLName, newEmail, newAccount, newZip, newState, newCity,
				newStreet, newPhone, model)) {

			Address newAddress = createAddress(newZip, newState, newCity,
					newStreet, newPhone);
			User newUser = createUser(newLogin, newLoginPass, newFName,
					newLName, newEmail, newAccount, newAge, newGender,
					newAddress);

			logger.info("newUser: " + newUser);
			userService.createUser(newUser);
			logger.info("registrationDone: User registered. You can log in now.");
			model.addAttribute("registrationDone",
					"User registered. You can log in now.");
		}

		logger.info("RegisterController.postRegister() completed");
		return "register";
	}

	/**
	 * isDataValid checks if data entered in form is valid
	 * 
	 * @param newLogin
	 * @param newLoginPass
	 * @param newLoginPass2
	 * @param newFName
	 * @param newLName
	 * @param newEmail
	 * @param newAccount
	 * @param newZip
	 * @param newState
	 * @param newCity
	 * @param newStreet
	 * @param newPhone
	 * @param model
	 * @return
	 */
	private boolean isDataValid(String newLogin, String newLoginPass,
			String newLoginPass2, String newFName, String newLName,
			String newEmail, String newAccount, String newZip, String newState,
			String newCity, String newStreet, String newPhone, Model model) {
		boolean validationResult = true;
		if (isDataNotEmpty(newLogin, newLoginPass, newLoginPass2, newFName,
				newLName, newEmail, newAccount, newZip, newState, newCity,
				newStreet, newPhone)) {
			logger.info("error: Some fields are empty. Fill in all fields please.");
			model.addAttribute("error",
					"Some fields are empty. Fill in all fields please.");
			validationResult = false;
		} else if (!newLoginPass.equals(newLoginPass2)) {
			logger.info("error: Passwords not the same. Please try again.");
			model.addAttribute("error",
					"Passwords not the same. Please try again.");
			validationResult = false;
		} else if (userService.checkLogin(newLogin)) {
			logger.info("error: Login already exists. Please invent another.");
			model.addAttribute("error",
					"Login already exists. Please invent another.");
			validationResult = false;
		}
		return validationResult;
	}

	/**
	 * isDataNotEmpty checks if data entered in form registration not empty
	 * 
	 * @param newLogin
	 * @param newLoginPass
	 * @param newLoginPass2
	 * @param newFName
	 * @param newLName
	 * @param newEmail
	 * @param newAccount
	 * @param newZip
	 * @param newState
	 * @param newCity
	 * @param newStreet
	 * @param newPhone
	 * @return
	 */
	private boolean isDataNotEmpty(String newLogin, String newLoginPass,
			String newLoginPass2, String newFName, String newLName,
			String newEmail, String newAccount, String newZip, String newState,
			String newCity, String newStreet, String newPhone) {
		return newLogin.equals("") | newLoginPass.equals("")
				| newLoginPass2.equals("") | newFName.equals("")
				| newLName.equals("") | newEmail.equals("")
				| newAccount.equals("") | newZip.equals("")
				| newState.equals("") | newCity.equals("")
				| newStreet.equals("") | newPhone.equals("");
	}

	/**
	 * createUser creates user object for registration
	 * 
	 * @param newLogin
	 * @param newLoginPass
	 * @param newFName
	 * @param newLName
	 * @param newEmail
	 * @param newAccount
	 * @param newAge
	 * @param newGender
	 * @param newAddress
	 * @return
	 */
	private User createUser(String newLogin, String newLoginPass,
			String newFName, String newLName, String newEmail,
			String newAccount, String newAge, String newGender,
			Address newAddress) {
		User newUser = new User();
		newUser.setLogin(newLogin);
		newUser.setPassword(newLoginPass);
		newUser.setFirstName(newFName);
		newUser.setLastName(newLName);
		newUser.setEmail(newEmail);
		newUser.setAccount(Integer.valueOf(newAccount));
		newUser.setAddress(newAddress);
		newUser.setEnabled(1); // by default registered user enabled
		newUser.setAge(newAge);
		newUser.setGender(Boolean.valueOf(newGender));
		Role newRole = new Role();
		newRole.setId(1);
		newUser.setRole(newRole); // by default registered user have
									// user rights
		return newUser;
	}

	/**
	 * createAddress creates address object for registration
	 * 
	 * @param newZip
	 * @param newState
	 * @param newCity
	 * @param newStreet
	 * @param newPhone
	 * @return
	 */
	private Address createAddress(String newZip, String newState,
			String newCity, String newStreet, String newPhone) {
		Address newAddress = new Address();
		newAddress.setZip(newZip);
		newAddress.setState(newState);
		newAddress.setCity(newCity);
		newAddress.setStreet(newStreet);
		newAddress.setPhone(newPhone);
		return newAddress;
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String getRegister() {
		logger.info("RegisterController.getRegister() started");
		logger.info("RegisterController.getRegister() completed");
		return "register";
	}
}