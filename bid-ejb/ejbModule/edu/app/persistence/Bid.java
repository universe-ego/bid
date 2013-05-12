package edu.app.persistence;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: Bid
 *
 */
@Entity
@Table(name="t_bid")

public class Bid implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	private BidPK pk;
	private Auction auction;
	private Customer customer;
	private Date date;
	
	

	public Bid(Auction auction, Customer customer, int rank) {
		this.getPk().setAuctionId(auction.getId());
		this.getPk().setCustomerId(customer.getId());
		this.getPk().setRank(rank);
		this.auction = auction;
		this.customer = customer;
		this.date = new Date();
	}

	public Bid() {
	}

	@EmbeddedId
	public BidPK getPk() {
		if(pk == null)
			pk = new BidPK();
		return pk;
	}

	public void setPk(BidPK pk) {
		this.pk = pk;
	}

	@ManyToOne
	@JoinColumn(name="auction_fk", insertable=false, updatable=false)
	public Auction getAuction() {
		return auction;
	}

	public void setAuction(Auction auction) {
		this.auction = auction;
	}

	@ManyToOne
	@JoinColumn(name="customer_fk", insertable=false, updatable=false)
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	
	
	

   
	
}
