package edu.app.web.mb;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.push.PushContext;
import org.primefaces.push.PushContextFactory;

import edu.app.business.AuctionServiceLocal;
import edu.app.business.BidServiceLocal;
import edu.app.business.CustomerServiceLocal;
import edu.app.persistence.Auction;
import edu.app.persistence.Customer;

@ManagedBean
@ViewScoped
public class AuctionBean implements Serializable {

	private static final long serialVersionUID = 3384387217187855212L;

	@ManagedProperty("#{authBean.user.id}")
	private int userId;

	@EJB
	private AuctionServiceLocal auctionServiceLocal;

	@EJB
	private CustomerServiceLocal customerServiceLocal;

	@EJB
	private BidServiceLocal bidServiceLocal;

	private List<Auction> liveAuctions;

	private Customer customer;

	@ManagedProperty("#{ownershipBean}")
	private OwnershipBean ownershipBean;

	public AuctionBean() {
	}

	@PostConstruct
	public void init() {
		liveAuctions = auctionServiceLocal.findLiveAuctions();
		customer = customerServiceLocal.findCustomerById(userId);
		ownershipBean.resolve();
	}

	public void doBid(Auction auction) {
		bidServiceLocal.placeBid(customer, auction);
		ownershipBean.resolve();
		ownershipBean.push(auction);
	}

	public List<Auction> getLiveAuctions() {
		return liveAuctions;
	}

	public void setLiveAuctions(List<Auction> liveAuctions) {
		this.liveAuctions = liveAuctions;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public OwnershipBean getOwnershipBean() {
		return ownershipBean;
	}

	public void setOwnershipBean(OwnershipBean ownershipBean) {
		this.ownershipBean = ownershipBean;
	}
	
	

}
