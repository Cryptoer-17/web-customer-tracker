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
		
		//create a query ... sort by lastName			order by lastName
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
		currentSession.saveOrUpdate(theCustomer);;
		
		
	}


	@Override
	public Customer getCustomer(int theId) {
			//get current hibernate session
			Session currentSession = sessionFactory.getCurrentSession();
			//now retrive/read from database using the primary key 
			Customer theCustomer = currentSession.get(Customer.class, theId);
			System.out.println(theCustomer);
			return theCustomer;
	}


	@Override
	public void deleteCustomer(int theId) {
		 
		//get current heibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//delete object with primary key
		Query theQuery = currentSession.createQuery("delete from Customer where id=:customerId");
		theQuery.setParameter("customerId", theId);
		//now delete customer
		theQuery.executeUpdate();
	}


	@Override
	public List<Customer> searchCustomers(String theSearchName) {
		  // get the current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();
        
        Query theQuery = null;
        
        //
        // only search by name if theSearchName is not empty
        //
        if (theSearchName != null && theSearchName.trim().length() > 0) {

            // search for firstName or lastName ... case insensitive
            theQuery =currentSession.createQuery("from Customer where lower(firstName) like :theName or lower(lastName) like :theName", Customer.class);
            theQuery.setParameter("theName", "%" + theSearchName.toLowerCase() + "%");

        }
        else {
            // theSearchName is empty ... so just get all customers
            theQuery =currentSession.createQuery("from Customer", Customer.class);            
        }
        
        // execute query and get result list
        List<Customer> customers = theQuery.getResultList();
                
        // return the results        
        return customers;
		
	}


	

}
