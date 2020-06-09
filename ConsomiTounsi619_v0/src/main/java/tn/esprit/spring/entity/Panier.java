package tn.esprit.spring.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	private int idPanier;
	
	@OneToMany(cascade = CascadeType.REMOVE,
			fetch=FetchType.EAGER)
	private List<Product_Line> productlines ;
	
	@ManyToOne
	@JoinColumn(name="FK_USER_ID")
	User user;
	
	private float prixTotal;
	
	@Temporal(TemporalType.DATE)
	private Date datePanier;
	
	private boolean isValid;
	
	@Enumerated(EnumType.STRING)
	private PaymentType typePaiement;

	private float remise;

	private int quantiteTotale;
	
	private float prixApayer;

	public int getIdPanier() {
		return idPanier;
	}

	public void setIdPanier(int idPanier) {
		this.idPanier = idPanier;
	}

	public List<Product_Line> getProductlines() {
		return productlines;
	}

	public void setProductlines(List<Product_Line> productlines) {
		this.productlines = productlines;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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
	
	

	public float getPrixApayer() {
		return prixApayer;
	}

	public void setPrixApayer(float prixApayer) {
		this.prixApayer = prixApayer;
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
		
		result = prime * result + Float.floatToIntBits(prixTotal);
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		
		if (Float.floatToIntBits(prixTotal) != Float.floatToIntBits(other.prixTotal))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Panier [idPanier=" + idPanier + ", productlines=" + productlines + ", user=" + user + ", prixTotal="
				+ prixTotal + ", datePanier=" + datePanier + ", isValid=" + isValid + ", typePaiement=" + typePaiement
				+ ", remise=" + remise + ", quantiteTotale=" + quantiteTotale + "]";
	}

	public Panier(int idPanier, List<Product_Line> productlines, User user, float prixTotal, Date datePanier,
			boolean isValid, PaymentType typePaiement, float remise, int quantiteTotale) {
		super();
		this.idPanier = idPanier;
		this.productlines = productlines;
		this.user = user;
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

	public Panier(User user, float prixTotal, Date datePanier, boolean isValid, float remise, int quantiteTotale) {
		super();
		this.user = user;
		this.prixTotal = prixTotal;
		this.datePanier = datePanier;
		this.isValid = isValid;
		this.remise = remise;
		this.quantiteTotale = quantiteTotale;
	}

	public Panier(List<Product_Line> productlines, User user, float prixTotal, Date datePanier, boolean isValid,
			int quantiteTotale, float prixApayer) {
		super();
		this.productlines = productlines;
		this.user = user;
		this.prixTotal = prixTotal;
		this.datePanier = datePanier;
		this.isValid = isValid;
		this.quantiteTotale = quantiteTotale;
		this.prixApayer = prixApayer;
	}

	
	
	
}
