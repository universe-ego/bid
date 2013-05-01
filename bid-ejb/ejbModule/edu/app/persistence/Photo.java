package edu.app.persistence;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="t_photo")
public class Photo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String name;
	private byte[] data;
	
	private Product product;

	public Photo() {
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Lob
	public byte[] getData() {
		return this.data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}
	@OneToOne(mappedBy = "photo")
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
   
}
