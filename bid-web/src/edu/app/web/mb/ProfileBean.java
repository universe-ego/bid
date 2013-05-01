package edu.app.web.mb;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import edu.app.business.CustomerServiceLocal;
import edu.app.persistence.Customer;
import edu.app.persistence.User;

@ManagedBean
@RequestScoped
public class ProfileBean {
	
	@EJB
	private CustomerServiceLocal customerServiceLocal;

	@ManagedProperty("#{authBean.user}")
	private User user;
	
	private Customer customer;

	private boolean detailsFormDisplayed = false;
	
	@PostConstruct
	public void init(){
		customer = new Customer();
		customer.setId(user.getId());
		customer.setLogin(user.getLogin());
		customer.setPassword(user.getPassword());
		customer.setEmail(user.getEmail());
	}
	
	public ProfileBean() {
	}
	
	public String doSave(){
		String navigateTo = null;
		customerServiceLocal.saveOrUpdate(customer);
		return navigateTo;
	}
	
	public String doMore(){
		String navigateTo = null;
		detailsFormDisplayed  = true;
		return navigateTo;
	}

	

	public boolean isDetailsFormDisplayed() {
		return detailsFormDisplayed;
	}

	public void setDetailsFormDisplayed(boolean detailsFormDisplayed) {
		this.detailsFormDisplayed = detailsFormDisplayed;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	
	
	
	
}
