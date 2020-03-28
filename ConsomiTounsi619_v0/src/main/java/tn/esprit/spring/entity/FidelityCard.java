package tn.esprit.spring.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class FidelityCard {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCard;
	
	@OneToOne
	private User UserDetail;
	
	private int points;

	public Long getIdCard() {
		return idCard;
	}

	public void setIdCard(Long idCard) {
		this.idCard = idCard;
	}

	public User getUserDetail() {
		return UserDetail;
	}

	public void setUserDetail(User userDetail) {
		UserDetail = userDetail;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((UserDetail == null) ? 0 : UserDetail.hashCode());
		result = prime * result + ((idCard == null) ? 0 : idCard.hashCode());
		result = prime * result + points;
		return result;
	}

	@Override
	public String toString() {
		return "FidelityCard [idCard=" + idCard + ", UserDetail=" + UserDetail + ", points=" + points + "]";
	}

	public FidelityCard() {
		super();
	}

	public FidelityCard(Long idCard, User userDetail, int points) {
		super();
		this.idCard = idCard;
		UserDetail = userDetail;
		this.points = points;
	}

	
	
}
