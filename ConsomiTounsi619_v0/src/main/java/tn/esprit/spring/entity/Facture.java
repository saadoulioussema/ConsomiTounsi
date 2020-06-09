package tn.esprit.spring.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Facture implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idFacture;
	
	
	@OneToOne(cascade = CascadeType.REMOVE)
	private Panier panierDetail;
	
	private float fraisDeliv;
	


	


	public float getFraisDeliv() {
		return fraisDeliv;
	}


	public void setFraisDeliv(float fraisDeliv) {
		this.fraisDeliv = fraisDeliv;
	}


	public int getIdFacture() {
		return idFacture;
	}


	public void setIdFacture(int idFacture) {
		this.idFacture = idFacture;
	}


	public Panier getPanierDetail() {
		return panierDetail;
	}


	public void setPanierDetail(Panier panierDetail) {
		this.panierDetail = panierDetail;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(fraisDeliv);
		result = prime * result + idFacture;
		result = prime * result + ((panierDetail == null) ? 0 : panierDetail.hashCode());
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
		Facture other = (Facture) obj;
		if (Float.floatToIntBits(fraisDeliv) != Float.floatToIntBits(other.fraisDeliv))
			return false;
		if (idFacture != other.idFacture)
			return false;
		if (panierDetail == null) {
			if (other.panierDetail != null)
				return false;
		} else if (!panierDetail.equals(other.panierDetail))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Facture [idFacture=" + idFacture + ", panierDetail=" + panierDetail + "]";
	}


	


	public Facture() {
		super();
	}


	public Facture(Panier panierDetail, float fraisLivraison) {
		super();
		this.panierDetail = panierDetail;
		this.fraisDeliv = fraisLivraison;
	}


	public Facture(int idFacture, Panier panierDetail, float fraisLivraison) {
		super();
		this.idFacture = idFacture;
		this.panierDetail = panierDetail;
		this.fraisDeliv = fraisLivraison;
	}


	
	
	
	
	
	
}
