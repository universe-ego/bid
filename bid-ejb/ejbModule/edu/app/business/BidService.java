package edu.app.business;

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
		Bid bid = new Bid(auction, customer, highestRank(auction)+1);
		em.persist(bid);
		auction.setCurrentPrice(auction.getCurrentPrice()+auction.getBidPriceIncrement());
		em.merge(auction);
	}

	public Customer findOwner(Auction auction) {
		Customer owner = null;
		String jpql = "select c from Customer c join c.bids bid where bid.pk.rank = (select max(b.pk.rank) from Bid b where b.auction=:auc) and bid.auction=:auc";
		Query query = em.createQuery(jpql);
		query.setParameter("auc", auction);
		try{
			owner = (Customer)query.getSingleResult();
		}catch(Exception e){}
		return owner;
	}

	public Integer highestRank(Auction auction) {
		Integer highestRank = 0;
		highestRank = (Integer) em.createQuery("select max(b.pk.rank) from Bid b where b.auction=:auc").setParameter("auc", auction).getSingleResult();
		if(highestRank==null){
			highestRank = 0;
		}
		return highestRank;
	}
    
    

}
