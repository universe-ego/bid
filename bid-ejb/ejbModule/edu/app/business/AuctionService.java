package edu.app.business;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import edu.app.persistence.Auction;


@Stateless
public class AuctionService implements AuctionServiceLocal{
	
	@PersistenceContext
	private EntityManager em;
	
	public AuctionService() {
	}

	public void saveOrUpdate(Auction auction) {
		em.merge(auction);
	}
	
	

}
