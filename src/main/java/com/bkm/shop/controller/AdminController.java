package com.bkm.shop.controller;

//import java.util.List;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

//import com.bkm.shop.dao.impl.ProductDaoImpl;
import com.bkm.shop.model.Address;
//import com.bkm.shop.model.Product;
import com.bkm.shop.model.Role;
import com.bkm.shop.model.User;
import com.bkm.shop.service.RoleService;
//import com.bkm.shop.dao.impl.CategoryDaoImpl;
//import com.bkm.shop.dao.impl.ProductDaoImpl;
//import com.bkm.shop.model.Category;
//import com.bkm.shop.model.Product;
import com.bkm.shop.service.UserService;

@Controller
public class AdminController {

	final static Logger logger = Logger.getLogger("info");

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	/**
	 * addPushButton opens add user page by click on button
	 * 
	 * @param request
	 * @param curTask
	 * @return
	 */
	@RequestMapping(value = "/admin", method = RequestMethod.POST)
	public String addPushButton(HttpServletRequest request,
			@RequestParam(value = "task", required = false) String curTask) {
		logger.info("AdminController.addPushButton() started");
		logger.info("PARAMS: task: " + curTask);

		if (curTask != null) {
			request.setAttribute("task", curTask);
		}
		request.setAttribute("roles", roleService.getAllRoles());

		logger.info("AdminController.addPushButton() completed");
		return "/admin";
	}

	/**
	 * openAdmint prints users
	 * 
	 * @param request
	 * @param curTask
	 * @param curPage
	 * @return
	 */
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String openAdmin(HttpServletRequest request,
			@RequestParam(value = "task", required = false) String curTask,
			@RequestParam(value = "page", required = false) String curPage) {
		logger.info("\nAdminController.openAdmin() started");
		logger.info("PARAMS: task: " + curTask + ", page: " + curPage);

		if (curTask != null) {
			request.setAttribute("task", curTask);
			prepareUsersToPrint(request, curPage);
			fillInRolesCombobox(request);
		}
		logger.info("AdminController.openAdmin() completed");
		return "/admin";
	}

	/**
	 * addUser adds users
	 * 
	 * @param model
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
	 * @param userEnabled
	 * @param newAge
	 * @param newGender
	 * @param newUserRole
	 * @return
	 */
	@RequestMapping(value = "/admin/addUser", method = RequestMethod.POST)
	public String addUser(Model model,
			@RequestParam(value = "login") String newLogin,
			@RequestParam(value = "loginpass") String newLoginPass,
			@RequestParam(value = "loginpass2") String newLoginPass2,
			@RequestParam(value = "fname") String newFName,
			@RequestParam(value = "lname") String newLName,
			@RequestParam(value = "email") String newEmail,
			@RequestParam(value = "account") String newAccount,
			@RequestParam(value = "zip") String newZip,
			@RequestParam(value = "state") String newState,
			@RequestParam(value = "city") String newCity,
			@RequestParam(value = "street") String newStreet,
			@RequestParam(value = "phone") String newPhone,
			@RequestParam(value = "userEnabled") String userEnabled,
			@RequestParam(value = "age") String newAge,
			@RequestParam(value = "gender") String newGender,
			@RequestParam(value = "userRole") String newUserRole) {
		logger.info("\nAdminController.addUser() started");
		logger.info("PARAMS: login: " + newLogin + ", loginpass: "
				+ newLoginPass + ", loginpass2: " + newLoginPass2 + ", fname: "
				+ newFName + ", lname: " + newLName + ", email: " + newEmail
				+ ", account: " + newAccount + ", zip: " + newZip + ", state: "
				+ newState + ", city: " + newCity + ", street: " + newStreet
				+ ", phone: " + newPhone + ", userEnabled: " + userEnabled
				+ ", age: " + newAge + ", gender: " + newGender
				+ ", userRole: " + newUserRole);

		if (isDataValid(newLogin, newLoginPass, newLoginPass2, newFName,
				newLName, newEmail, newAccount, newZip, newState, newCity,
				newStreet, newPhone, model)) {
			Address newAddress = createAddress(newZip, newState, newCity,
					newStreet, newPhone);
			User newUser = createUser(newLogin, newLoginPass, newFName,
					newLName, newEmail, newAccount, newAge, newGender,
					newAddress, userEnabled, newUserRole);

			userService.createUser(newUser);

			logger.info("addUserDone: User added. You can add more.");
			model.addAttribute("addUserDone", "User added. You can add more.");
		}
		return "redirect:/admin?task=addUser";
	}

	/**
	 * updateUser updates users data
	 * 
	 * @param model
	 * @param loginOrig
	 * @param newLogin
	 * @param newLoginPass
	 * @param newFName
	 * @param newLName
	 * @param newEmail
	 * @param newAccount
	 * @param newZip
	 * @param newState
	 * @param newCity
	 * @param newStreet
	 * @param newPhone
	 * @param userEnabled
	 * @param newAge
	 * @param newGender
	 * @param newUserRole
	 * @param userId
	 * @param addressId
	 * @param paginationCurPage
	 * @return
	 */
	@RequestMapping(value = "/admin/updateUser", method = RequestMethod.POST)
	public String updateUser(
			Model model,
			@RequestParam(value = "loginOrig") String loginOrig,
			@RequestParam(value = "login") String newLogin,
			@RequestParam(value = "loginpass") String newLoginPass,
			@RequestParam(value = "fname") String newFName,
			@RequestParam(value = "lname") String newLName,
			@RequestParam(value = "email") String newEmail,
			@RequestParam(value = "account") String newAccount,
			@RequestParam(value = "zip") String newZip,
			@RequestParam(value = "state") String newState,
			@RequestParam(value = "city") String newCity,
			@RequestParam(value = "street") String newStreet,
			@RequestParam(value = "phone") String newPhone,
			@RequestParam(value = "userEnabled", required = false) String userEnabled,
			@RequestParam(value = "age") String newAge,
			@RequestParam(value = "gender") String newGender,
			@RequestParam(value = "userRole") String newUserRole,
			@RequestParam(value = "userId") String userId,
			@RequestParam(value = "addressId") String addressId,
			@RequestParam(value = "paginationCurPage") String paginationCurPage) {
		logger.info("\nAdminController.updateUser() started");
		logger.info("PARAMS: loginOrig: " + loginOrig + ", login: " + newLogin
				+ ", loginpass: " + newLoginPass + ", fname: " + newFName
				+ ", lname: " + newLName + ", email: " + newEmail
				+ ", account: " + newAccount + ", zip: " + newZip + ", state: "
				+ newState + ", city: " + newCity + ", street: " + newStreet
				+ ", phone: " + newPhone + ", userEnabled: " + userEnabled
				+ ", age: " + newAge + ", gender: " + newGender
				+ ", userRole: " + newUserRole + ", userId: " + userId
				+ ", addressId: " + addressId + ", paginationCurPage: "
				+ paginationCurPage);

		if (isDataValidUpdate(loginOrig, newLogin, newLoginPass, newFName,
				newLName, newEmail, newAccount, newZip, newState, newCity,
				newStreet, newPhone, model)) {

			Address newAddress = createAddress(newZip, newState, newCity,
					newStreet, newPhone);
			newAddress.setId(Integer.valueOf(addressId));

			if (userEnabled == null) {
				userEnabled = "false";
			}

			System.out.println("\nuserEnabled: " + userEnabled);

			User newUser = createUser(newLogin, newLoginPass, newFName,
					newLName, newEmail, newAccount, newAge, newGender,
					newAddress, userEnabled, newUserRole);
			newUser.setId(Integer.valueOf(userId));

			userService.updateUser(newUser);

			model.addAttribute("updateUserDone", "User with id='" + userId
					+ "' updated. You can update another.");
			logger.info("updateUserDone: User with id='" + userId
					+ "' updated. You can update another.");
		}

		String curPage = "";
		if (paginationCurPage != null) {
			curPage = "&page=" + paginationCurPage;
		}

		logger.info("AdminController.updateUser() completed");
		return "redirect:/admin?task=users" + curPage;
	}

	/**
	 * delUser removes users
	 * 
	 * @param model
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/admin/delUser", method = RequestMethod.POST)
	public String delUser(Model model,
			@RequestParam(value = "userId") int userId) {
		logger.info("AdminController.delUser() started");
		logger.info("PARAMS: userId: " + userId);

		userService.deleteUser(userId);

		model.addAttribute("delUserDone", "User with id='" + userId
				+ "' removed. You can remove another.");
		logger.info("delUserDone: User with id='" + userId
				+ "' removed. You can remove another.");

		logger.info("AdminController.delUser() completed");
		return "redirect:/admin?task=users";
	}

	/**
	 * prepareUsersToPrint prepares users to print on page
	 * 
	 * @param request
	 * @param curPage
	 */
	private void prepareUsersToPrint(HttpServletRequest request, String curPage) {
		int page = 1;
		if (curPage != null) {
			page = Integer.parseInt(curPage);
		}
		List<User> users = userService.getAllUsers();
		int numOfRecords = users.size();
		users = userService.getUsersPagin(page);
		int recordsPerPage = 1;
		int numOfPages = (int) Math.ceil(numOfRecords * 1.0 / recordsPerPage);

		logger.info("VARS: numOfPages: " + numOfPages + ", currentPage: "
				+ page + ", numOfRecords: " + numOfRecords + ", users: "
				+ users);
		request.setAttribute("users", users);
		request.setAttribute("numOfPages", numOfPages);
		request.setAttribute("currentPage", page);
		request.setAttribute("numOfRecords", numOfRecords);
	}

	/**
	 * fillInRolesCombobox fills in roles combobox with existing roles
	 * 
	 * @param request
	 */
	private void fillInRolesCombobox(HttpServletRequest request) {
		List<Role> roles = roleService.getAllRoles();
		request.setAttribute("roles", roles);
		logger.info("VARS: roles: " + roles);
	}

	/**
	 * isDataValid checks if data entered to create user is valid
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
			model.addAttribute("error",
					"Some fields are empty. Fill in all fields please.");
			logger.info("error: Some fields are empty. Fill in all fields please.");
			validationResult = false;
		} else if (!newLoginPass.equals(newLoginPass2)) {
			model.addAttribute("error",
					"Passwords not the same. Please try again.");
			logger.info("error: Passwords not the same. Please try again.");
			validationResult = false;
		} else if (userService.checkLogin(newLogin)) {
			model.addAttribute("error",
					"Login already exists. Please invent another.");
			logger.info("error: Login already exists. Please invent another.");
			validationResult = false;
		}
		return validationResult;
	}

	/**
	 * isDataValidUpdate checks if data entered for updating is valid
	 * 
	 * @param loginOrig
	 * @param newLogin
	 * @param newLoginPass
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
	private boolean isDataValidUpdate(String loginOrig, String newLogin,
			String newLoginPass, String newFName, String newLName,
			String newEmail, String newAccount, String newZip, String newState,
			String newCity, String newStreet, String newPhone, Model model) {
		boolean validationResult = true;
		if (isDataNotEmptyUpdate(newLogin, newLoginPass, newFName, newLName,
				newEmail, newAccount, newZip, newState, newCity, newStreet,
				newPhone)) {
			model.addAttribute("error",
					"Some fields are empty. Fill in all fields please.");
			logger.info("error: Some fields are empty. Fill in all fields please.");
			validationResult = false;
		} else if (userService.checkLogin(newLogin)
				&& !newLogin.equals(loginOrig)) {
			model.addAttribute("error",
					"Login already exists. Please invent another.");
			logger.info("error: Login already exists. Please invent another.");
			validationResult = false;
		}
		return validationResult;
	}

	/**
	 * isDataNotEmpty checks if data entered in create user form not empty
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
	 * isDataNotEmptyUpdate checks if data modified in update form is not empty
	 * 
	 * @param newLogin
	 * @param newLoginPass
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
	private boolean isDataNotEmptyUpdate(String newLogin, String newLoginPass,
			String newFName, String newLName, String newEmail,
			String newAccount, String newZip, String newState, String newCity,
			String newStreet, String newPhone) {
		return newLogin.equals("") | newLoginPass.equals("")
				| newFName.equals("") | newLName.equals("")
				| newEmail.equals("") | newAccount.equals("")
				| newZip.equals("") | newState.equals("") | newCity.equals("")
				| newStreet.equals("") | newPhone.equals("");
	}

	/**
	 * createAddress creates address object
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
		logger.info("newAddress: " + newAddress);
		return newAddress;
	}

	/**
	 * createUser creates user object
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
	 * @param userEnabled
	 * @param newUserRole
	 * @return
	 */
	private User createUser(String newLogin, String newLoginPass,
			String newFName, String newLName, String newEmail,
			String newAccount, String newAge, String newGender,
			Address newAddress, String userEnabled, String newUserRole) {
		User newUser = new User();
		newUser.setLogin(newLogin);
		newUser.setPassword(newLoginPass);
		newUser.setFirstName(newFName);
		newUser.setLastName(newLName);
		newUser.setEmail(newEmail);
		newUser.setAccount(Integer.valueOf(newAccount));
		newUser.setAddress(newAddress);
		newUser.setEnabled((userEnabled.equals("true")) ? 1 : 0);
		newUser.setAge(newAge);
		newUser.setGender(Boolean.valueOf(newGender));
		Role newRole = new Role();
		newRole.setId(Integer.valueOf(newUserRole));
		newUser.setRole(newRole);
		logger.info("newUser: " + newUser);
		return newUser;
	}
}