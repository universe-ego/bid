package edu.app.web.mb;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import edu.app.business.AuthenticationServiceLocal;
import edu.app.business.CustomerServiceLocal;
import edu.app.persistence.Customer;

@ManagedBean
@ViewScoped
public class ProfileBean implements Serializable{
	
	private static final long serialVersionUID = -1895346853573478117L;

	@EJB
	private CustomerServiceLocal customerServiceLocal;
	
	@EJB
	private AuthenticationServiceLocal authenticationServiceLocal;

	@ManagedProperty("#{authBean.user.id}")
	private int userId;
	
	private Customer customer;
	
	private boolean editMode;

	@PostConstruct
	public void init(){
		customer = customerServiceLocal.findCustomerById(userId);
	}
	
	public ProfileBean() {
	}
	
	public String doSave(){
		String navigateTo = null;
		customerServiceLocal.saveOrUpdate(customer);
		editMode = false;
		return navigateTo;
	}
	
	public String doCancel(){
		String navigateTo = null;
		editMode = false;
		return navigateTo;
	}
	
	public String enableEdition(){
		String navigateTo = null;
		editMode = true;
		return navigateTo;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public boolean isEditMode() {
		return editMode;
	}

	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
	}
	
	public void validateLogin(FacesContext context, UIComponent component, Object toValidate)
			throws ValidatorException {
		String login = null;
		if(toValidate instanceof String){
			login = (String) toValidate;
		}
		if (login.isEmpty() || login == null || login.equals(customer.getLogin())) {
			return;
		}
		boolean loginAlreadyInUse = authenticationServiceLocal.loginExists(login);
		if(loginAlreadyInUse){
			throw new ValidatorException(new FacesMessage("login already in use!"));
		}
	}
	
	
}
