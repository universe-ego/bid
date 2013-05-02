package edu.app.web.mb;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import edu.app.business.AuctionServiceLocal;
import edu.app.persistence.Auction;
import edu.app.persistence.Product;


@ManagedBean
@ViewScoped
public class AuctionizeBean {
	
	@EJB
	private AuctionServiceLocal  auctionServiceLocal;
	
	private Auction auction;
	
	private Product product;
	
	public AuctionizeBean() {
	}
	
	@PostConstruct
	public void init(){
		auction = new Auction();
		product = (Product) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("productToAuctionize");
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("productToAuctionize");
		product.setAuction(auction);
		auction.setProduct(product);
	}
	
	public String doSave(){
		String navigateTo = null;
		auctionServiceLocal.saveOrUpdate(auction);
		return navigateTo;
	}
	
	public String doCancel(){
		String navigateTo = null;
		navigateTo = "/pages/admin/product/manage";
		return navigateTo;
	}


	public Auction getAuction() {
		return auction;
	}


	public void setAuction(Auction auction) {
		this.auction = auction;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	
	

}
