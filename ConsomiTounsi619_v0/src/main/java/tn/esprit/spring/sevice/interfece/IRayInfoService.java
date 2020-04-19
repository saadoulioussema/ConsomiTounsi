
package tn.esprit.spring.sevice.interfece;

import java.util.List;

import tn.esprit.spring.entity.Category;
import tn.esprit.spring.entity.Notif;
import tn.esprit.spring.entity.Notification;
import tn.esprit.spring.entity.Product;

import tn.esprit.spring.entity.Ray;


public interface IRayInfoService {
	public void addRay(Ray client);
	public void addProductAndAssignToRay(Product product, Long rayId);
	public Ray getRayById(Long clientId);////
	public void updateRayInfoById(Ray ray, Long rayId);
	public Product getProductById(Long productId);
	public List<Product> getAllProducts();
	public List<Ray> getAllRays();
	public void deleteRay(Ray ray);
	public Long countRays();
	public void deleteAllProducts();
	public void updateProduct(Long productId, Product product);
	
	public Long countRaysbyCategory(Category categoryray);
	public List<Ray> getRayByCat(Category categoryray);
	public Long countProductsInRays( Long idray);
	public List<Product> getProductExprdate();
	
	void notifyuser(Product product, Ray ray);
	List<Notif> myNotifications(); 
	public void deleteNotif(Notif notif);
}