package edu.app.persistence;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Product
 *
 */
@Entity
@Table(name="t_product")

public class Product implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
	
	private int id;
	private String name;
	private float unitCost;

	private Photo photo;
	
	private Category category;
	
	private Auction auction;
	
	
	

	public Product() {
	}
	
	public Product(int id, String name, float unitCost) {
		this.id = id;
		this.name = name;
		this.unitCost = unitCost;
	}

	@Id    
	public int getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	@Column( name  = "unit_cost")
	public float getUnitCost() {
		return this.unitCost;
	}

	@ManyToOne
	@JoinColumn( name = "category_fk" , nullable = true)
	public Category getCategory() {
		return category;
	}
	
	public void setId(int id) {
		int oldId = this.id;
		this.id = id;
		changeSupport.firePropertyChange("id", oldId, id);
	}
	public void setName(String name) {
		String oldName = this.name;
		this.name = name;
		changeSupport.firePropertyChange("name", oldName, name);
	}
	public void setUnitCost(float unitCost) {
		double oldUnitCost = this.unitCost;
		this.unitCost = unitCost;
		changeSupport.firePropertyChange("unitCost", oldUnitCost, unitCost);
		
	}

	@OneToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
	@JoinColumn( name = "photo_fk")
	public Photo getPhoto() {
		if( photo == null)
			photo = new Photo();
		return photo;
	}

	public void setPhoto(Photo photo) {
		this.photo = photo;
	}

	public void setCategory(Category category) {
		Category oldCategory = this.category;
		this.category = category;
		changeSupport.firePropertyChange("category", oldCategory, category);
	}
	
	public void addPhoto(Photo photo){
		this.setPhoto(photo);
		photo.setProduct(this);
	}

	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", unitCost="
				+ unitCost + "]";
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + Float.floatToIntBits(unitCost);
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (Float.floatToIntBits(unitCost) != Float
				.floatToIntBits(other.unitCost))
			return false;
		return true;
	}

	@Transient
	public PropertyChangeSupport getChangeSupport() {
		return changeSupport;
	}

	public void setChangeSupport(PropertyChangeSupport changeSupport) {
		this.changeSupport = changeSupport;
	}
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		changeSupport.addPropertyChangeListener(listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		changeSupport.removePropertyChangeListener(listener);
	}

	@OneToOne(mappedBy="product")
	public Auction getAuction() {
		return auction;
	}

	public void setAuction(Auction auction) {
		this.auction = auction;
	}
	
	
	
	
   
}
