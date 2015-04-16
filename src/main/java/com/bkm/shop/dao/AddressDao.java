package com.bkm.shop.dao;

import java.util.List;

import com.bkm.shop.model.Address;

public interface AddressDao {

	// get all existing addresses
	List<Address> getAllAddresses();

	// get address by id
	Address getAddressById(int id);

	// updates address by address and returns updated address id
	int updateAddress(Address address);

	// create new address and return new created id
	int createAddressGetIdCreated(Address address);
}