package tn.esprit.spring.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Product_Line implements Serializable {

	private static final long serialVersionUID = 3876346912862238239L;

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int plId;
	
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="FK_PR_ID")
	Product  produit;
	
	@Temporal(TemporalType.DATE)
	private Date dateprodline;
	 
	private float prix;
	
	private int quantité;

	
	public Date getDateprodline() {
		return dateprodline;
	}

	public void setDateprodline(Date dateprodline) {
		this.dateprodline = dateprodline;
	}

	
	public Product getProduit() {
		return produit;
	}

	
	public void setProduit(Product produit) {
		this.produit = produit;
	}

	
	public int getPlId() {
		return plId;
	}

	public void setPlId(int plId) {
		this.plId = plId;
	}

	public float getPrix() {
		return prix;
	}

	public void setPrix(float prix) {
		this.prix = prix;
	}

	public int getQuantité() {
		return quantité;
	}

	public void setQuantité(int quantité) {
		this.quantité = quantité;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + plId;
		result = prime * result + Float.floatToIntBits(prix);
		result = prime * result + ((produit == null) ? 0 : produit.hashCode());
		result = prime * result + quantité;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product_Line other = (Product_Line) obj;
		if (plId != other.plId)
			return false;
		if (Float.floatToIntBits(prix) != Float.floatToIntBits(other.prix))
			return false;
		if (produit == null) {
			if (other.produit != null)
				return false;
		} else if (!produit.equals(other.produit))
			return false;
		if (quantité != other.quantité)
			return false;
		return true;
	}

	

	
	public Product_Line(int plId, Product produit, Date dateprodline, float prix, int quantité) {
		super();
		this.plId = plId;
		this.produit = produit;
		this.dateprodline = dateprodline;
		this.prix = prix;
		this.quantité = quantité;
	}

	@Override
	public String toString() {
		return "Product_Line [plId=" + plId + ", produit=" + produit + ", dateprodline=" + dateprodline + ", prix="
				+ prix + ", quantité=" + quantité + "]";
	}

	public Product_Line() {
		super();
	}

	public Product_Line(Product produit, int quantité) {
		super();
		this.produit = produit;
		this.quantité = quantité;
	}

	
	
	

	
	
	
	
}
