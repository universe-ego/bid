package edu.app.business;

import java.util.Date;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import edu.app.persistence.Auction;
import edu.app.persistence.Bid;
import edu.app.persistence.Customer;

@Stateless
public class BidService implements BidServiceLocal {
	
	@PersistenceContext
	private EntityManager em;

    public BidService() {
    }

	public void placeBid(Customer customer, Auction auction) {
		Bid bid = new Bid(auction, customer, new Date());
		em.persist(bid);
		auction = em.merge(auction);
		auction.setCurrentPrice(auction.getCurrentPrice()+auction.getBidPriceIncrement());
	}

	public Customer findOwner(Auction auction) {
		Customer owner = null;
		String jpql = "select c from Customer c join c.bids bid where bid.pk.date = (select max(b.pk.date) from Bid b where b.auction=:auc)";
		Query query = em.createQuery(jpql);
		query.setParameter("auc", auction);
		try{
			owner = (Customer)query.getSingleResult();
		}catch(Exception e){}
		return owner;
	}
    
    

}
