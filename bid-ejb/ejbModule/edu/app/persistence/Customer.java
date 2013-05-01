package edu.app.persistence;

import edu.app.persistence.User;
import java.io.Serializable;
import java.lang.String;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Customer
 *
 */
@Entity
public class Customer extends User implements Serializable {

	
	private String firstname;
	private String lastname;
	private String phoneNumber;
	private Date birthDate;
	
	private List<Bid> bids;
	
	private static final long serialVersionUID = 1L;

	public Customer() {
	} 
	
	
	public Customer(String login, String password, String email,String firstname, String lastname, String phoneNumber, Date birthDate) {
		super(login, password, email);
		this.firstname = firstname;
		this.lastname = lastname;
		this.phoneNumber = phoneNumber;
		this.birthDate = birthDate;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}   
	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	@Column(name = "phone_number")
	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "birth_date")
	public Date getBirthDate() {
		return this.birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}


	@OneToMany(mappedBy="customer", cascade=CascadeType.REMOVE)
	public List<Bid> getBids() {
		if (bids == null) {
			bids = new ArrayList<Bid>();
		}
		return bids;
	}


	public void setBids(List<Bid> bids) {
		this.bids = bids;
	}
   
}
