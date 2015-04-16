package com.bkm.shop.service;

import java.util.List;

import com.bkm.shop.model.Address;

public interface AddressService {

	List<Address> getAllAddresses();

	Address getAddressById(int id);
	
	int updateAddress(Address address);
	
	int createAddressGetIdCreated(Address address);
}