package tn.esprit.spring.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Type;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.annotation.JsonIgnore;



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
	    private float accBalance;
	    
	    @Type(type="true_false")
	    private Boolean online;
	    
	    
	    
	    @LazyCollection(LazyCollectionOption.FALSE)
	    @OneToMany(mappedBy="user")
		private List <UserProductCategoryViews> userProductCategoriesViews;
	    @LazyCollection(LazyCollectionOption.FALSE)
		@OneToMany(cascade=CascadeType.ALL, mappedBy="user")
		private List<Participation> participation;
	    @LazyCollection(LazyCollectionOption.FALSE)
		@OneToMany(cascade=CascadeType.ALL, mappedBy="user")
		private List<Notification> notification;
	    @LazyCollection(LazyCollectionOption.FALSE)
		@OneToMany(cascade=CascadeType.ALL, mappedBy="user")
		private List<Contribution> contribution;
	  
		//@JsonBackReference
	    @LazyCollection(LazyCollectionOption.FALSE)
		@OneToMany(mappedBy="user" , cascade=CascadeType.MERGE)
	    private List<Recherche> recherches;
		
		//@JsonManagedReference
		//@JsonIgnore
	    @LazyCollection(LazyCollectionOption.FALSE)
		@OneToMany(mappedBy="user",cascade=CascadeType.MERGE)
		private List<Comment> comments;
		
	    @LazyCollection(LazyCollectionOption.FALSE)
		  @OneToMany(mappedBy="user"/*,cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch=FetchType.EAGER*/)
		    private List<Ray> rays = new ArrayList<>();
	    @LazyCollection(LazyCollectionOption.FALSE)
		  @OneToMany(mappedBy="user"/*,cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch=FetchType.EAGER*/)
		    private List<Notif> notifs = new ArrayList<>();
	    @LazyCollection(LazyCollectionOption.FALSE)
		  @OneToMany(mappedBy="user")
			private List <UserProductViews> UserProductsViews;
		  
		  @JsonIgnore
			//@JsonBackReference
		  @LazyCollection(LazyCollectionOption.FALSE)
			@OneToMany(mappedBy="user")
			private List<Panier> panierId;
		  
		
		public User(Long id, String username, String password, String firstName, String lastName, String email,
				String role, List<UserProductViews> userProductsViews,
				List<UserProductCategoryViews> userProductCategoriesViews, List<Event> events,
				List<Recherche> recherches) {
			super();
			this.id = id;
			this.username = username;
			this.password = password;
			this.firstName = firstName;
			this.lastName = lastName;
			this.email = email;
			this.role = role;
			this.UserProductsViews = userProductsViews;
			this.userProductCategoriesViews = userProductCategoriesViews;
			//this.events = events;
			this.recherches = recherches;
			//this.comments = comments; , List<Comment> comments
		}

		
		
		public User(Long id, String username, String password, String firstName, String lastName, String email,
				String role, float accBalance, Boolean online) {
			super();
			this.id = id;
			this.username = username;
			this.password = password;
			this.firstName = firstName;
			this.lastName = lastName;
			this.email = email;
			this.role = role;
			this.accBalance = accBalance;
			this.online = online;
		}



		public Boolean getOnline() {
			return online;
		}



		public void setOnline(Boolean online) {
			this.online = online;
		}



		public List<Notif> getNotifs() {
			return notifs;
		}



		public void setNotifs(List<Notif> notifs) {
			this.notifs = notifs;
		}



		public List<Ray> getRays() {
			return rays;
		}

		public void setRays(List<Ray> rays) {
			this.rays = rays;
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

		public void setParticipation(List<Participation> participation) {
			this.participation = participation;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public List<Notification> getNotification() {
			return notification;
		}

		public void setNotification(List<Notification> notification) {
			this.notification = notification;
		}

		public float getAccBalance() {
			return accBalance;
		}

		public void setAccBalance(float accBalance) {
			this.accBalance = accBalance;
		}

		public List<Contribution> getContribution() {
			return contribution;
		}

		public void setContribution(List<Contribution> contribution) {
			this.contribution = contribution;
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
			this.UserProductsViews = userProductsViews;
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
				List<UserProductCategoryViews> userProductCategoriesViews, List<Participation> participation,
				List<Notification> notification, List<Recherche> recherches, List<Comment> comments) {
			super();
			this.id = id;
			this.username = username;
			this.password = password;
			this.firstName = firstName;
			this.lastName = lastName;
			this.email = email;
			this.role = role;
			this.UserProductsViews = userProductsViews;
			this.userProductCategoriesViews = userProductCategoriesViews;
			this.participation = participation;
			this.notification = notification;
			this.recherches = recherches;
			this.comments = comments;
		}

		public User(Long id, String username, String password, String firstName, String lastName, String email,
				String role, float accBalance, List<UserProductViews> userProductsViews,
				List<UserProductCategoryViews> userProductCategoriesViews, List<Participation> participation,
				List<Notification> notification, List<Contribution> contribution, List<Recherche> recherches,
				List<Comment> comments) {
			super();
			this.id = id;
			this.username = username;
			this.password = password;
			this.firstName = firstName;
			this.lastName = lastName;
			this.email = email;
			this.role = role;
			this.accBalance = accBalance;
			this.UserProductsViews = userProductsViews;
			this.userProductCategoriesViews = userProductCategoriesViews;
			this.participation = participation;
			this.notification = notification;
			this.contribution = contribution;
			this.recherches = recherches;
			this.comments = comments;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((UserProductsViews == null) ? 0 : UserProductsViews.hashCode());
			result = prime * result + Float.floatToIntBits(accBalance);
			result = prime * result + ((comments == null) ? 0 : comments.hashCode());
			result = prime * result + ((contribution == null) ? 0 : contribution.hashCode());
			result = prime * result + ((email == null) ? 0 : email.hashCode());
			result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
			result = prime * result + ((id == null) ? 0 : id.hashCode());
			result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
			result = prime * result + ((notification == null) ? 0 : notification.hashCode());
			result = prime * result + ((participation == null) ? 0 : participation.hashCode());
			result = prime * result + ((password == null) ? 0 : password.hashCode());
			result = prime * result + ((recherches == null) ? 0 : recherches.hashCode());
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
			if (Float.floatToIntBits(accBalance) != Float.floatToIntBits(other.accBalance))
				return false;
			if (comments == null) {
				if (other.comments != null)
					return false;
			} else if (!comments.equals(other.comments))
				return false;
			if (contribution == null) {
				if (other.contribution != null)
					return false;
			} else if (!contribution.equals(other.contribution))
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
			if (notification == null) {
				if (other.notification != null)
					return false;
			} else if (!notification.equals(other.notification))
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
			if (recherches == null) {
				if (other.recherches != null)
					return false;
			} else if (!recherches.equals(other.recherches))
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
					+ ", lastName=" + lastName + ", email=" + email + ", role=" + role + ", accBalance=" + accBalance+"]";
					
		}
		}
		

		
	

