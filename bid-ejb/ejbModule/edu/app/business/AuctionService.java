package edu.app.business;

import java.util.List;

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

	public List<Auction> findLiveAuctions() {
		return em.createQuery("select a from Auction a where a.active=true").getResultList();
	}

	public Double findCurrentPrice(int id) {
		return (Double) em.createQuery("select auc.currentPrice from Auction auc where auc.id=:id").setParameter("id", id).getSingleResult();
	}
	
	

}
