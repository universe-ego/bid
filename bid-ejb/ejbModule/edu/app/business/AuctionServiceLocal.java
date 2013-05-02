package edu.app.business;

import javax.ejb.Local;

import edu.app.persistence.Auction;

@Local
public interface AuctionServiceLocal {
	
	void saveOrUpdate(Auction auction);
}
