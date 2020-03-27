package tn.esprit.spring.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Panier implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPanier;
	
	@OneToMany
	private List<Product_Line> productlines ;
	
	private float prixTotal;
	
	@Temporal(TemporalType.DATE)
	private Date datePanier;
	
	private boolean isValid;
	
	private PaymentType typePaiement;

	private float remise;

	private int quantiteTotale;

	public Long getIdPanier() {
		return idPanier;
	}

	public void setIdPanier(Long idPanier) {
		this.idPanier = idPanier;
	}

	public List<Product_Line> getProductlines() {
		return productlines;
	}

	public void setProductlines(List<Product_Line> productlines) {
		this.productlines = productlines;
	}

	public float getPrixTotal() {
		return prixTotal;
	}

	public void setPrixTotal(float prixTotal) {
		this.prixTotal = prixTotal;
	}

	public Date getDatePanier() {
		return datePanier;
	}

	public void setDatePanier(Date datePanier) {
		this.datePanier = datePanier;
	}

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

	public PaymentType getTypePaiement() {
		return typePaiement;
	}

	public void setTypePaiement(PaymentType typePaiement) {
		this.typePaiement = typePaiement;
	}

	public float getRemise() {
		return remise;
	}

	public void setRemise(float remise) {
		this.remise = remise;
	}

	public int getQuantiteTotale() {
		return quantiteTotale;
	}

	public void setQuantiteTotale(int quantiteTotale) {
		this.quantiteTotale = quantiteTotale;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((datePanier == null) ? 0 : datePanier.hashCode());
		result = prime * result + ((idPanier == null) ? 0 : idPanier.hashCode());
		result = prime * result + (isValid ? 1231 : 1237);
		result = prime * result + Float.floatToIntBits(prixTotal);
		result = prime * result + ((productlines == null) ? 0 : productlines.hashCode());
		result = prime * result + quantiteTotale;
		result = prime * result + Float.floatToIntBits(remise);
		result = prime * result + ((typePaiement == null) ? 0 : typePaiement.hashCode());
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
		Panier other = (Panier) obj;
		if (datePanier == null) {
			if (other.datePanier != null)
				return false;
		} else if (!datePanier.equals(other.datePanier))
			return false;
		if (idPanier == null) {
			if (other.idPanier != null)
				return false;
		} else if (!idPanier.equals(other.idPanier))
			return false;
		if (isValid != other.isValid)
			return false;
		if (Float.floatToIntBits(prixTotal) != Float.floatToIntBits(other.prixTotal))
			return false;
		if (productlines == null) {
			if (other.productlines != null)
				return false;
		} else if (!productlines.equals(other.productlines))
			return false;
		if (quantiteTotale != other.quantiteTotale)
			return false;
		if (Float.floatToIntBits(remise) != Float.floatToIntBits(other.remise))
			return false;
		if (typePaiement != other.typePaiement)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Panier [idPanier=" + idPanier + ", productlines=" + productlines + ", prixTotal=" + prixTotal
				+ ", datePanier=" + datePanier + ", isValid=" + isValid + ", typePaiement=" + typePaiement + ", remise="
				+ remise + ", quantiteTotale=" + quantiteTotale + "]";
	}

	public Panier(Long idPanier, List<Product_Line> productlines, float prixTotal, Date datePanier, boolean isValid,
			PaymentType typePaiement, float remise, int quantiteTotale) {
		super();
		this.idPanier = idPanier;
		this.productlines = productlines;
		this.prixTotal = prixTotal;
		this.datePanier = datePanier;
		this.isValid = isValid;
		this.typePaiement = typePaiement;
		this.remise = remise;
		this.quantiteTotale = quantiteTotale;
	}

	public Panier() {
		super();
	}
	
	
	
}
