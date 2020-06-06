package tn.esprit.spring.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
public class Comment implements Serializable{
	
	

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column
	private long id;
	
	@Column
	 private String mot;
	
	//@JsonBackReference
	@ManyToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name="idSubject",referencedColumnName="id")
	private Subject subject;
	
	//@JsonBackReference
	@ManyToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name="idUser",referencedColumnName="id")
	private User user;
	
	
	///evaluation
	
	@OneToMany(mappedBy="comment" , cascade=CascadeType.MERGE)
	private List<Comment_evaluation> ratings;
	
	

	public Comment(long id, String mot, Subject subject, User user) {
		super();
		this.id = id;
		this.mot = mot;
		this.subject = subject;
		this.user = user;
	}
	
	

	public Comment(long id, String mot) {
		super();
		this.id = id;
		this.mot = mot;
	}



	public List<Comment_evaluation> getRatings() {
		return ratings;
	}



	public void setRatings(List<Comment_evaluation> ratings) {
		this.ratings = ratings;
	}



	public Comment() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMot() {
		return mot;
	}

	public void setMot(String mot) {
		this.mot = mot;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	

	public Comment(String mot) {
		super();
		this.mot = mot;
	}



	@Override
	public String toString() {
		return "Comment [id=" + id + ", mot=" + mot + ", subject=" + subject + ", user=" + user + "]";
	}
	
	
	
	
	
}
