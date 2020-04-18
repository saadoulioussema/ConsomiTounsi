/*
 * Copyright 2017 by Walid YAICH <walid.yaich@esprit.tn>
 * This is an Open Source Software
 * License: http://www.gnu.org/licenses/gpl.html GPL version 3
 */

package tn.esprit.spring.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.aspectj.weaver.tools.cache.AsynchronousFileCacheBacking.RemoveCommand;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Ray {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Type typeray;
    @Enumerated(EnumType.STRING)
    private Category categoryray ;
    private int capacity;
   
    
    @OneToMany(mappedBy="ray"/*,cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch=FetchType.EAGER*/)
    private List<Product> products = new ArrayList<>();


    
    @JsonIgnore 
    @ManyToOne
    private User user;
    
    
	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Type getTyperay() {
		return typeray;
	}


	public void setTyperay(Type typeray) {
		this.typeray = typeray;
	}


	public Category getCategoryray() {
		return categoryray;
	}


	public void setCategoryray(Category categoryray) {
		this.categoryray = categoryray;
	}


	public int getCapacity() {
		return capacity;
	}


	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}


	public List<Product> getProducts() {
		return products;
	}


	public void setProducts(List<Product> products) {
		this.products = products;
	}


	public Ray() {
		super();
	}


	public Ray(Long id, Type typeray, Category categoryray, int capacity) {
		super();
		this.id = id;
		this.typeray = typeray;
		this.categoryray = categoryray;
		this.capacity = capacity;
	}
	public Ray(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Ray [id=" + id + ", typeray=" + typeray + ", categoryray=" + categoryray + ", capacity=" + capacity
				+ "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + capacity;
		result = prime * result + ((categoryray == null) ? 0 : categoryray.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((typeray == null) ? 0 : typeray.hashCode());
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
		Ray other = (Ray) obj;
		if (capacity != other.capacity)
			return false;
		if (categoryray != other.categoryray)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (typeray != other.typeray)
			return false;
		return true;
	}
    
    
    
	
    
}