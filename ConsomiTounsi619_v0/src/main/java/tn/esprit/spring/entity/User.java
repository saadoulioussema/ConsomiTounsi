package tn.esprit.spring.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
public class User implements Serializable{
		/**
	 * 
	 */
	private static final long serialVersionUID = -6236517548335858347L;
		@Id
	    @GeneratedValue(strategy=GenerationType.IDENTITY)
	    private Long id;
		private String username;
	    private String firstName;
	    private String lastName;
	    private String email;
	    private String role;
		@OneToMany(mappedBy="user")
		private List <UserProductViews> UserProductsViews;
		@OneToMany(mappedBy="user")
		private List <UserProductCategoryViews> userProductCategoriesViews;
		@ManyToMany
		private List <Event> events;
	  
		@OneToMany(mappedBy="user" , cascade=CascadeType.REMOVE)
	    private List<Recherche> recherches;
		
		@JsonIgnore
		//@JsonBackReference
		@OneToMany(mappedBy="user")
		private List<Product_Line> productlines;
		

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		
		public String getUsername() {
			return firstName;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}
		public List<UserProductCategoryViews> getUserProductCategoriesViews() {
			return userProductCategoriesViews;
		}

		public void setUserProductCategoriesViews(List<UserProductCategoryViews> userProductCategoriesViews) {
			this.userProductCategoriesViews = userProductCategoriesViews;
		}
		
		public List<UserProductViews> getUserProductsViews() {
			return UserProductsViews;
		}
		public void setUserProductsViews(List<UserProductViews> userProductsViews) {
			UserProductsViews = userProductsViews;
		}
		
		
		public String getRole() {
			return role;
		}

		public void setRole(String role) {
			this.role = role;
		}

		public List<Event> getEvents() {
			return events;
		}

		public void setEvents(List<Event> events) {
			this.events = events;
		}
		
		
		
		public List<Recherche> getRecherches() {
			return recherches;
		}

		public void setRecherches(List<Recherche> recherches) {
			this.recherches = recherches;
		}

		public User(){}

		

		

		public User(Long id, String username, String firstName, String lastName, String email, String role,
				List<UserProductViews> userProductsViews, List<UserProductCategoryViews> userProductCategoriesViews) {
			super();
			this.id = id;
			this.username = username;
			this.firstName = firstName;
			this.lastName = lastName;
			this.email = email;
			this.role = role;
			UserProductsViews = userProductsViews;
			this.userProductCategoriesViews = userProductCategoriesViews;
		}

		
		
		
		
		
		public User(Long id, String username, String firstName, String lastName, String email, String role) {
			super();
			this.id = id;
			this.username = username;
			this.firstName = firstName;
			this.lastName = lastName;
			this.email = email;
			this.role = role;
		}

		
		public User(Long id, String username, String firstName, String lastName, String email, String role,
				List<UserProductViews> userProductsViews, List<UserProductCategoryViews> userProductCategoriesViews,
				List<Event> events) {
			super();
			this.id = id;
			this.username = username;
			this.firstName = firstName;
			this.lastName = lastName;
			this.email = email;
			this.role = role;
			UserProductsViews = userProductsViews;
			this.userProductCategoriesViews = userProductCategoriesViews;
			this.events = events;
		}

		@Override
		public String toString() {
			return "User [id=" + id + ", username=" + username + ", firstName=" + firstName + ", lastName=" + lastName
					+ ", email=" + email + ", role=" + role + ", UserProductsViews=" + UserProductsViews
					+ ", userProductCategoriesViews=" + userProductCategoriesViews + ", events=" + events + "]";
		}

		

		
}
