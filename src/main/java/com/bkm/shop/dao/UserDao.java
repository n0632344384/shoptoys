package com.bkm.shop.dao;

import java.util.List;

import com.bkm.shop.model.User;

public interface UserDao {

	// create new user
	User createUser(User user);

	// get all existing users
	List<User> getAllUsers();
	
	// get users with pagination
	List<User> getUsersPagin(int page);

	// update user by user and return updated user id 
	int updateUser(User user);

	// delete user by id and return true if user deleted
	boolean deleteUser(int id);

	// check if login exists and return if login exists
	boolean checkLogin(String login);
}