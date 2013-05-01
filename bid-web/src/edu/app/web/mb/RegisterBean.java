package edu.app.web.mb;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import edu.app.business.AuthenticationServiceLocal;
import edu.app.business.CustomerServiceLocal;
import edu.app.persistence.Customer;


@ManagedBean
@RequestScoped
public class RegisterBean{
	
	@EJB
	private AuthenticationServiceLocal authenticationServiceLocal;
	
	@EJB
	private CustomerServiceLocal customerServiceLocal;
	
	@ManagedProperty("#{authBean}")
	private AuthenticationBean authenticationBean;
	
	private Customer customer = new Customer();
	
	
	public RegisterBean() {
	}

	public String doRegiter(){
		String navigateTo = null;
		customerServiceLocal.saveOrUpdate(customer);
		authenticationBean.setUser(customer);
		navigateTo = authenticationBean.login();
		return navigateTo;
	}
	
	public String doCancel(){
		String navigateTo = null;
		navigateTo = "/welcome";
		return navigateTo;
	}

	
	

	public void validateLogin(FacesContext context, UIComponent component, Object toValidate)
			throws ValidatorException {
		String login = null;
		if(toValidate instanceof String){
			login = (String) toValidate;
		}
		if (login.isEmpty() || login == null) {
			return;
		}
		boolean loginAlreadyInUse = authenticationServiceLocal.loginExists(login);
		if(loginAlreadyInUse){
			throw new ValidatorException(new FacesMessage("login already in use!"));
		}
	}

	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public AuthenticationBean getAuthenticationBean() {
		return authenticationBean;
	}

	public void setAuthenticationBean(AuthenticationBean authenticationBean) {
		this.authenticationBean = authenticationBean;
	}
	
	

	
}
