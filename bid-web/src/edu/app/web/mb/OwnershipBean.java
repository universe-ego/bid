package edu.app.web.mb;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import org.primefaces.push.PushContext;
import org.primefaces.push.PushContextFactory;

import edu.app.business.AuctionServiceLocal;
import edu.app.business.BidServiceLocal;
import edu.app.persistence.Auction;
import edu.app.persistence.Customer;

@ManagedBean
@ApplicationScoped
public class OwnershipBean implements Serializable{
	
	private static final long serialVersionUID = 3228872837205715979L;
	
	@EJB
	private AuctionServiceLocal auctionServiceLocal;
	
	@EJB
	private BidServiceLocal bidServiceLocal;

	public OwnershipBean() {
	}
	
	private Map<Integer, String> ownership;
	
	@PostConstruct
	public void resolve() {
		List<Auction> liveAuctions = auctionServiceLocal.findLiveAuctions();
		ownership = new HashMap<Integer, String>();
		for(Auction auction : liveAuctions){
			Customer owner = bidServiceLocal.findOwner(auction);
			if (owner!=null) {
				
				ownership.put(auction.getId(), owner.getLogin());
			} else {
				ownership.put(auction.getId(), "no one!");
			}
		}
	}
	
	public synchronized void push(Auction auction){
		PushContext context = PushContextFactory.getDefault().getPushContext();
		context.push("/live/"+ auction.getId(),"kl");
	}
	
	public Map<Integer, String> getOwnership() {
		return ownership;
	}

	public void setOwnership(Map<Integer, String> ownership) {
		this.ownership = ownership;
	}
	
	


}
