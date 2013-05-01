package edu.app.business;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import edu.app.persistence.Customer;

@Stateless
public class CustomerService implements CustomerServiceLocal {
    
	@PersistenceContext
	private EntityManager em;

	public CustomerService() {
    }

	public void saveOrUpdate(Customer customer) {
		em.merge(customer);
	}
	
	

}
