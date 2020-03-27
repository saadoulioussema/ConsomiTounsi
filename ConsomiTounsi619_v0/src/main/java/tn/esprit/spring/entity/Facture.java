package tn.esprit.spring.entity;

import java.io.Serializable;

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
	private Long idFacture;
	
	
	@OneToOne
	private Panier panierDetail;


	public Long getIdFacture() {
		return idFacture;
	}


	public void setIdFacture(Long idFacture) {
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
		result = prime * result + ((idFacture == null) ? 0 : idFacture.hashCode());
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
		if (idFacture == null) {
			if (other.idFacture != null)
				return false;
		} else if (!idFacture.equals(other.idFacture))
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


	public Facture(Long idFacture, Panier panierDetail) {
		super();
		this.idFacture = idFacture;
		this.panierDetail = panierDetail;
	}


	public Facture() {
		super();
	}
	
	
	
	
}
