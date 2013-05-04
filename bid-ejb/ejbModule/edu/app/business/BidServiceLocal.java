package edu.app.business;

import javax.ejb.Local;

import edu.app.persistence.Auction;
import edu.app.persistence.Customer;

@Local
public interface BidServiceLocal {
	
	void placeBid(Customer customer, Auction auction);
	Customer findOwner(Auction auction);
	

}
