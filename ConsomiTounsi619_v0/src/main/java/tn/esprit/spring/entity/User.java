package tn.esprit.spring.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;



@Component
@Entity
@Table(name = "user")
public class User implements Serializable{

        private static final long serialVersionUID = -6236517548335858347L;
		@Id
	    @GeneratedValue(strategy=GenerationType.IDENTITY)
	    private Long id;
		private String username;
		@JsonIgnore
	    private String password;
	    private String firstName;
	    private String lastName;
	    private String email;
	    private String role;	    
		@OneToMany(mappedBy="user")
		private List <UserProductViews> UserProductsViews;
		@OneToMany(mappedBy="user")
		private List <UserProductCategoryViews> userProductCategoriesViews;
		@OneToMany(cascade=CascadeType.ALL, mappedBy="user")
		private List<Participation> participation;
	  
		@JsonBackReference
		@OneToMany(mappedBy="user" , cascade=CascadeType.REMOVE)
	    private List<Recherche> recherches;
		
		@JsonManagedReference
		@JsonIgnore
		@OneToMany(mappedBy="user",cascade=CascadeType.REMOVE)
		private List<Comment> comments;
		

		
		
		
		public User(Long id, String username, String password, String firstName, String lastName, String email,
				String role, List<UserProductViews> userProductsViews,
				List<UserProductCategoryViews> userProductCategoriesViews, List<Event> events,
				List<Recherche> recherches, List<Comment> comments) {
			super();
			this.id = id;
			this.username = username;
			this.password = password;
			this.firstName = firstName;
			this.lastName = lastName;
			this.email = email;
			this.role = role;
			UserProductsViews = userProductsViews;
			this.userProductCategoriesViews = userProductCategoriesViews;
			this.events = events;
			this.recherches = recherches;
			this.comments = comments;
		}

		public List<Recherche> getRecherches() {
			return recherches;
		}

		public void setRecherches(List<Recherche> recherches) {
			this.recherches = recherches;
		}

		public List<Comment> getComments() {
			return comments;
		}

		public void setComments(List<Comment> comments) {
			this.comments = comments;
		}

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
			return username;
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
		
		public List<Participation> getParticipation() {
			return participation;
		}

		public void setParticipations(List<Participation> participation) {
			this.participation = participation;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public User(){}

		public User(String username, String password) {
			super();
			this.username = username;
			this.password = password;
		}

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

		public User(Long id, String username, String password, String firstName, String lastName, String email,
				String role, List<UserProductViews> userProductsViews,
				List<UserProductCategoryViews> userProductCategoriesViews, List<Participation> participation) {
			super();
			this.id = id;
			this.username = username;
			this.password = password;
			this.firstName = firstName;
			this.lastName = lastName;
			this.email = email;
			this.role = role;
			UserProductsViews = userProductsViews;
			this.userProductCategoriesViews = userProductCategoriesViews;
			this.participation = participation;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((UserProductsViews == null) ? 0 : UserProductsViews.hashCode());
			result = prime * result + ((email == null) ? 0 : email.hashCode());
			result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
			result = prime * result + ((id == null) ? 0 : id.hashCode());
			result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
			result = prime * result + ((participation == null) ? 0 : participation.hashCode());
			result = prime * result + ((password == null) ? 0 : password.hashCode());
			result = prime * result + ((role == null) ? 0 : role.hashCode());
			result = prime * result
					+ ((userProductCategoriesViews == null) ? 0 : userProductCategoriesViews.hashCode());
			result = prime * result + ((username == null) ? 0 : username.hashCode());
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
			User other = (User) obj;
			if (UserProductsViews == null) {
				if (other.UserProductsViews != null)
					return false;
			} else if (!UserProductsViews.equals(other.UserProductsViews))
				return false;
			if (email == null) {
				if (other.email != null)
					return false;
			} else if (!email.equals(other.email))
				return false;
			if (firstName == null) {
				if (other.firstName != null)
					return false;
			} else if (!firstName.equals(other.firstName))
				return false;
			if (id == null) {
				if (other.id != null)
					return false;
			} else if (!id.equals(other.id))
				return false;
			if (lastName == null) {
				if (other.lastName != null)
					return false;
			} else if (!lastName.equals(other.lastName))
				return false;
			if (participation == null) {
				if (other.participation != null)
					return false;
			} else if (!participation.equals(other.participation))
				return false;
			if (password == null) {
				if (other.password != null)
					return false;
			} else if (!password.equals(other.password))
				return false;
			if (role == null) {
				if (other.role != null)
					return false;
			} else if (!role.equals(other.role))
				return false;
			if (userProductCategoriesViews == null) {
				if (other.userProductCategoriesViews != null)
					return false;
			} else if (!userProductCategoriesViews.equals(other.userProductCategoriesViews))
				return false;
			if (username == null) {
				if (other.username != null)
					return false;
			} else if (!username.equals(other.username))
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "User [id=" + id + ", username=" + username + ", password=" + password + ", firstName=" + firstName
					+ ", lastName=" + lastName + ", email=" + email + ", role=" + role + ", UserProductsViews="
					+ UserProductsViews + ", userProductCategoriesViews=" + userProductCategoriesViews
					+ ", participation=" + participation + "]";
		}	
	
	
}
