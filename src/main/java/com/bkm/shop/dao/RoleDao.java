package com.bkm.shop.dao;

import java.util.List;

import com.bkm.shop.model.Role;

public interface RoleDao {

	// get all existing roles
	List<Role> getAllRoles();
}