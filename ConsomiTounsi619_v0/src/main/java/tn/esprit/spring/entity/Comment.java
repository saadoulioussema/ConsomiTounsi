package tn.esprit.spring.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
	
	@JsonBackReference
	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="idSubject",referencedColumnName="id")
	private Subject subject;
	
	@JsonBackReference
	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="idUser",referencedColumnName="id")
	private User user;

	public Comment(long id, String mot, Subject subject, User user) {
		super();
		this.id = id;
		this.mot = mot;
		this.subject = subject;
		this.user = user;
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

	

	@Override
	public String toString() {
		return "Comment [id=" + id + ", mot=" + mot + ", subject=" + subject + ", user=" + user + "]";
	}
	
	
	
	
	
}
