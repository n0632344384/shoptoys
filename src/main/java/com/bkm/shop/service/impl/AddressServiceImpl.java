package com.bkm.shop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bkm.shop.dao.AddressDao;
import com.bkm.shop.model.Address;
import com.bkm.shop.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressDao addressDao;

	@Override
	public List<Address> getAllAddresses() {
		return addressDao.getAllAddresses();
	}

	@Override
	public Address getAddressById(int id) {
		return addressDao.getAddressById(id);
	}

	@Override
	public int createAddressGetIdCreated(Address address) {
		return addressDao.createAddressGetIdCreated(address);
	}

	@Override
	public int updateAddress(Address address) {
		return addressDao.updateAddress(address);
	}
}