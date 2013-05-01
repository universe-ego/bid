package edu.app.business;

import javax.ejb.Local;

import edu.app.persistence.Customer;

@Local
public interface CustomerServiceLocal {
	
	void saveOrUpdate(Customer customer);

}
