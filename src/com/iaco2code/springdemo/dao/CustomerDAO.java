package com.iaco2code.springdemo.dao;

import java.util.List;

import com.iaco2code.springdemo.entity.Customer;

public interface CustomerDAO {

	public List<Customer> getCustomers();
}