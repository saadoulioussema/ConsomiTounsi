package tn.esprit.spring.entity;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Product_Line implements Serializable {

	private static final long serialVersionUID = 3876346912862238239L;

	@EmbeddedId
	private Product_LinePK product_linePK;
	
	@ManyToOne
    @JoinColumn(name = "barCode", referencedColumnName = "barCode", insertable=false, updatable=false)
	private Product product;
	
	@ManyToOne
    @JoinColumn(name = "id", referencedColumnName = "id", insertable=false, updatable=false)
	private User user;
	
	@ManyToOne
	private Panier panier ;
	
	
	private int quantité;
	
	private float prix;

	public Product_LinePK getProduct_linePK() {
		return product_linePK;
	}

	public void setProduct_linePK(Product_LinePK product_linePK) {
		this.product_linePK = product_linePK;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
	

	public int getQuantité() {
		return quantité;
	}

	public void setQuantité(int quantité) {
		this.quantité = quantité;
	}

	public float getPrix() {
		return prix;
	}

	public void setPrix(float prix) {
		this.prix = prix;
	}

	public Product_Line(Product_LinePK product_linePK, Product product, User user, boolean isValid, int quantité,
			float prix) {
		super();
		this.product_linePK = product_linePK;
		this.product = product;
		this.user = user;
		this.quantité = quantité;
		this.prix = prix;
	}

	public Product_Line() {
		super();
	}

	@Override
	public String toString() {
		return "Product_Line [product_linePK=" + product_linePK + ", product=" + product + ", user=" + user
				 + ", quantité=" + quantité + ", prix=" + prix + "]";
	}

	
	
	
	
}
