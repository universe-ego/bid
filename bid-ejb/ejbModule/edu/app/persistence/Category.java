package edu.app.persistence;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.lang.String;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Category
 *
 */
@Entity
@Table(name="t_category")
public class Category implements Serializable {
	
	private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

	
	private int id;
	private String name;
	
	private List<Product> products;
	
	
	private static final long serialVersionUID = 1L;

	public Category() {
	}
	
	
	
	public Category(int id, String name) {
		this.id = id;
		this.name = name;
	}

	@Id    
	public int getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
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

	@OneToMany(mappedBy = "category")
	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}


	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + "]";
	}


	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}



	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Category other = (Category) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	/**
	 * gestion des deux bouts de l'association
	 */
	public void addProduct(Product product){
		this.getProducts().add(product);
		product.setCategory(this);
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
	
	@PreRemove
	public void preRemove(){
		for(Product p:products)
			p.setCategory(null);
	}
	
	
   
}
