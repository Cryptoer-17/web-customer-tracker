package com.iaco2code.springdemo.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.iaco2code.springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	public List<Customer> getCustomers() {
		
		//get the curreent hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//create a query ... sort by lastName
		Query<Customer> theQuery =
				currentSession.createQuery("from Customer order by lastName",
											Customer.class);
		
		
		List<Customer> customers = theQuery.getResultList();
		
		//get the result list
		
		return customers;
	}


	@Override
	public void saveCustomer(Customer theCustomer) {
		
		
		//get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// save the customer ... finally LOL
		currentSession.save(theCustomer);
		
		
	}

}
