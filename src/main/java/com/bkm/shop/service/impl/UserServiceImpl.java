package com.bkm.shop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bkm.shop.model.User;
import com.bkm.shop.service.UserService;
import com.bkm.shop.dao.UserDao;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Override
	public User createUser(User user) {
		return userDao.createUser(user);
	}

	@Override
	public List<User> getAllUsers() {
		return userDao.getAllUsers();
	}
	
	@Override
	public List<User> getUsersPagin(int page) {
		return userDao.getUsersPagin(page);
	}
	
	@Override
	public int updateUser(User user) {
		return userDao.updateUser(user);
	}

	@Override
	public boolean checkLogin(String login) {
		return userDao.checkLogin(login);
	}

	@Override
	public boolean deleteUser(int id) {
		return userDao.deleteUser(id);
	}
}