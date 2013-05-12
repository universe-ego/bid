package edu.app.web.mb;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.push.PushContext;
import org.primefaces.push.PushContextFactory;

import edu.app.business.AuctionServiceLocal;
import edu.app.business.BidServiceLocal;
import edu.app.business.CustomerServiceLocal;
import edu.app.persistence.Auction;
import edu.app.persistence.Customer;

@ManagedBean
@ApplicationScoped
public class OwnershipBean implements Serializable{
	
	private static final long serialVersionUID = 3228872837205715979L;
	
	private final PushContext pushContext = PushContextFactory.getDefault().getPushContext();
	
	@EJB
	private AuctionServiceLocal auctionServiceLocal;
	
	@EJB
	private BidServiceLocal bidServiceLocal;
	
	@EJB
	private CustomerServiceLocal customerServiceLocal;

	public OwnershipBean() {
	}
	
	private List<Auction> liveAuctions;
	
	private Map<Integer, String> ownership;
	
	
	
	@PostConstruct
	public void resolve() {
		liveAuctions = auctionServiceLocal.findLiveAuctions();
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
	
	public synchronized void doBid(Auction auction){
		FacesContext context = FacesContext.getCurrentInstance();
		Integer userId = context.getApplication().evaluateExpressionGet(context, "#{authBean.user.id}", Integer.class);
		Customer customer = customerServiceLocal.findCustomerById(userId);
		bidServiceLocal.placeBid(customer, auction);
		ownership.put(auction.getId(), customer.getLogin());
		StringBuilder message = new StringBuilder(20);
		Double currentPrice = auctionServiceLocal.findCurrentPrice(auction.getId());
		message.append(currentPrice).append(",").append(customer.getLogin());
		pushContext.push("/live/" + auction.getId(), message.toString());
		
	}
	
	public Map<Integer, String> getOwnership() {
		return ownership;
	}

	public void setOwnership(Map<Integer, String> ownership) {
		this.ownership = ownership;
	}

	public List<Auction> getLiveAuctions() {
		return liveAuctions;
	}

	public void setLiveAuctions(List<Auction> liveAuctions) {
		this.liveAuctions = liveAuctions;
	}
	
	
	


}
