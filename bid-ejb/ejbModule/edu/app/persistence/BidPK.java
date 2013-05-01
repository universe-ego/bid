package edu.app.persistence;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class BidPK  implements Serializable {   
   
	         
	private int auctionId;         
	private int customerId;         
	private Date date;
	private static final long serialVersionUID = 1L;

	public BidPK() {}

	

	@Column(name="auction_fk")
	public int getAuctionId() {
		return this.auctionId;
	}

	public void setAuctionId(int auctionId) {
		this.auctionId = auctionId;
	}
	
	@Column(name="customer_fk")
	public int getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
   
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (!(o instanceof BidPK)) {
			return false;
		}
		BidPK other = (BidPK) o;
		return true
			&& getAuctionId() == other.getAuctionId()
			&& getCustomerId() == other.getCustomerId()
			&& (getDate() == null ? other.getDate() == null : getDate().equals(other.getDate()));
	}
	
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + getAuctionId();
		result = prime * result + getCustomerId();
		result = prime * result + (getDate() == null ? 0 : getDate().hashCode());
		return result;
	}
   
   
}
