package edu.app.persistence;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class BidPK  implements Serializable {   
   
	private static final long serialVersionUID = 1L;
	         
	private int auctionId;         
	private int customerId;         
	private int rank;

	public BidPK() {
	}

	

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

	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}



	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + auctionId;
		result = prime * result + customerId;
		result = prime * result + rank;
		return result;
	}



	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BidPK other = (BidPK) obj;
		if (auctionId != other.auctionId)
			return false;
		if (customerId != other.customerId)
			return false;
		if (rank != other.rank)
			return false;
		return true;
	}
   
	
	
	
   
   
}
