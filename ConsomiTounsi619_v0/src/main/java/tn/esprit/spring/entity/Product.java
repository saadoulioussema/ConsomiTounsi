package tn.esprit.spring.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class Product implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7950217207447904668L;
	@Id
	private Long barCode;
	@NotNull (message="product name is null")
	@Column(unique=true)
	private String name;
	@NotNull
	@Positive(message="The price should be positive number ")
	private float price;
	
	@Temporal(TemporalType.DATE)
    private Date exprdate;
	
	private int quantity;

	
	@ManyToOne
	private ProductCategory category;
	
	@OneToMany(mappedBy="product")
	private List <Ad> ads;
	
	@OneToMany(mappedBy="product")
	private List <UserProductViews> productUsersViews;
	
	@OneToMany
	//(mappedBy="product")
	private  List<Product_Line> productlines;
	
	@JsonIgnore 
    @ManyToOne
    private Ray ray;
	
	
	  @OneToOne(mappedBy="product"/*,cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch=FetchType.EAGER*/)
	    private Notif notif;
	
	
	
	
	
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public List<Product_Line> getProductlines() {
		return productlines;
	}
	public void setProductlines(List<Product_Line> productlines) {
		this.productlines = productlines;
	}
	public Long getBarCode() {
		return barCode;
		
		
	}
	public void setBarCode(Long barCode) {
		if (!isValidBarCode(barCode)) {
			throw new IllegalArgumentException("Invalid Barcode, barcode should be a number with '13' digits and starts with '619'");
		}
		this.barCode = barCode;
	}
	boolean isValidBarCode(Long code ) {
		if((code.toString().indexOf("619")!=0)||(code.toString().length()!=13)) return false;
		return true;}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
	
	public Ray getRay() {
		return ray;
	}
	public void setRay(Ray ray) {
		this.ray = ray;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public Date getExprdate() {
		return exprdate;
	}
	public void setExprdate(Date exprdate) {
		this.exprdate = exprdate;
	}
	public ProductCategory getCategory() {
		return category;
	}
	public void setCategory(ProductCategory category) {
		this.category = category;
	}
	public List<Ad> getAds() {
		return ads;
	}
	public void setAds(List<Ad> ads) {
		this.ads = ads;
	}	
	public List<UserProductViews> getProductUsersViews() {
		return productUsersViews;
	}
	public void setProductUsersViews(List<UserProductViews> productUsersViews) {
		this.productUsersViews = productUsersViews;
	}
	public Product() {
		super();
	}

	
	
	
	
	
	public Notif getNotif() {
		return notif;
	}
	public void setNotif(Notif notif) {
		this.notif = notif;
	}
	@Override
	public String toString() {
		return "Product [barCode=" + barCode + ", name=" + name + ", price=" + price + ", exprdate=" + exprdate + "]";
	}
	public Product(Long barCode, @NotNull(message = "product name is null") String name,
			@NotNull @Positive(message = "The price should be positive number ") float price, ProductCategory category, Date exprdate) {
		super();
		this.barCode = barCode;
		this.name = name;
		this.price = price;
		this.category = category;
		this.exprdate = exprdate;
		
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((barCode == null) ? 0 : barCode.hashCode());
		result = prime * result + ((exprdate == null) ? 0 : exprdate.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + Float.floatToIntBits(price);
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
		Product other = (Product) obj;
		if (barCode == null) {
			if (other.barCode != null)
				return false;
		} else if (!barCode.equals(other.barCode))
			return false;
		if (exprdate == null) {
			if (other.exprdate != null)
				return false;
		} else if (!exprdate.equals(other.exprdate))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (Float.floatToIntBits(price) != Float.floatToIntBits(other.price))
			return false;
		return true;
	}	
	
	
	
}
