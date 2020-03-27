package tn.esprit.spring.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class Product_LinePK implements Serializable {

	private static final long serialVersionUID = 5377539445871317492L;

	private int idUser;
	
	private int idProduit;

	public Product_LinePK() {
		super();
	}

	public Product_LinePK(int idUser, int idProduit) {
		super();
		this.idUser = idUser;
		this.idProduit = idProduit;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public int getIdProduit() {
		return idProduit;
	}

	public void setIdProduit(int idProduit) {
		this.idProduit = idProduit;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idProduit;
		result = prime * result + idUser;
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
		Product_LinePK other = (Product_LinePK) obj;
		if (idProduit != other.idProduit)
			return false;
		if (idUser != other.idUser)
			return false;
		return true;
	}
	
	
	
}
