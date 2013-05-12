package edu.app.web.mb;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import edu.app.business.AuctionServiceLocal;
import edu.app.business.BidServiceLocal;
import edu.app.business.CustomerServiceLocal;
import edu.app.persistence.Auction;
import edu.app.persistence.Customer;

@ManagedBean
@RequestScoped
public class BidBean implements Serializable {

	private static final long serialVersionUID = -5451731180116426289L;

	@EJB
	private AuctionServiceLocal auctionServiceLocal;

	@EJB
	private BidServiceLocal bidServiceLocal;

	@EJB
	private CustomerServiceLocal customerServiceLocal;

	private List<Auction> liveAuctions;

	@ManagedProperty("#{ownershipBean}")
	private OwnershipBean ownershipBean;

	@ManagedProperty("#{authBean.user.id}")
	private int userId;

	private Customer customer;
	
	private String handlersScript;
	
	
	public String getHandlersScript() {
		return handlersScript;
	}

	public void setHandlersScript(String handlersScript) {
		this.handlersScript = handlersScript;
	}

	public BidBean() {
	}

	@PostConstruct
	public void init() {
		
		customer = customerServiceLocal.findCustomerById(userId);
		liveAuctions = auctionServiceLocal.findLiveAuctions();
		handlersScript =  generateHandlersScript();
	}

	private String generateHandlersScript() {
		StringBuilder script = new StringBuilder(50);
		for(Auction auction: liveAuctions){
			script.
			append("function handleMessage"+auction.getId()+"(data) {").append("\n").		
			append("var info = data.split(',');").append("\n").   		
			append("var currentPrice = $(PrimeFaces.escapeClientId('auctions:"+(auction.getId() -1 )+":form:currentPrice'));").append("\n").     		
			append("var owner = $(PrimeFaces.escapeClientId('auctions:"+(auction.getId() -1 )+":form:owner'));").append("\n").    		
			append("").append("\n"). 
			append("owner.html(info[0]);").append("\n").     		
			append("currentPrice.html(info[1]);").append("\n"). 			
	    		
			append("}").append("\n"); 	
		}
		return script.toString();
		
	}

	public void doBid(Auction auction) {
		bidServiceLocal.placeBid(customer, auction);
		Customer owner = bidServiceLocal.findOwner(auction);
		ownershipBean.updateOwnership(auction.getId(), owner.getLogin());
	}
	

	public List<Auction> getLiveAuctions() {
		return liveAuctions;
	}

	public void setLiveAuctions(List<Auction> liveAuctions) {
		this.liveAuctions = liveAuctions;
	}

	public OwnershipBean getOwnershipBean() {
		return ownershipBean;
	}

	public void setOwnershipBean(OwnershipBean ownershipBean) {
		this.ownershipBean = ownershipBean;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	
	

}
