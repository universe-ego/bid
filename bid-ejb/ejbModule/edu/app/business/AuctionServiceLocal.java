package edu.app.business;

import java.util.List;

import javax.ejb.Local;

import edu.app.persistence.Auction;

@Local
public interface AuctionServiceLocal {
	
	void saveOrUpdate(Auction auction);
	List<Auction> findLiveAuctions();
}
