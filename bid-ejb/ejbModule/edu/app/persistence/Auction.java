package edu.app.persistence;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Auction
 *
 */
@Entity
@Table(name="t_auction")

public class Auction implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id;
	private double startingPrice;
	private double bidPriceIncrement;
	private Date startDate;
	private Date endDate;
	private int period;
	private boolean active;
	private double currentPrice;
	
	private Product product;
	
	private List<Bid> bids;

	public Auction() {
		super();
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}
	@Column(name="starting_price")
	public double getStartingPrice() {
		return this.startingPrice;
	}

	public void setStartingPrice(double startingPrice) {
		this.startingPrice = startingPrice;
	}
	@Column(name="bid_price_increment")
	public double getBidPriceIncrement() {
		return this.bidPriceIncrement;
	}

	public void setBidPriceIncrement(double bidPriceIncrement) {
		this.bidPriceIncrement = bidPriceIncrement;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="start_date")
	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="end_date")
	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}   
	public int getPeriod() {
		return this.period;
	}

	public void setPeriod(int period) {
		this.period = period;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	
	@Column(name="current_price")
	public double getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(double currentPrice) {
		this.currentPrice = currentPrice;
	}

	@OneToOne
	@JoinColumn(name="product_fk")
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@OneToMany(mappedBy="auction", cascade=CascadeType.REMOVE)
	public List<Bid> getBids() {
		if (bids == null) {
			bids = new ArrayList<Bid>();
		}
		return bids;
	}

	public void setBids(List<Bid> bids) {
		this.bids = bids;
	}
   
}
