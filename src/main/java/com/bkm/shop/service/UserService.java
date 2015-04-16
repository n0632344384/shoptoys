package com.bkm.shop.service;

import java.util.List;

import com.bkm.shop.model.User;

public interface UserService {

	User createUser(User user);
	
	List<User> getAllUsers();
	
	List<User> getUsersPagin(int page);
	
	int updateUser(User user);
	
	boolean checkLogin(String login);
	
	boolean deleteUser(int id);
}