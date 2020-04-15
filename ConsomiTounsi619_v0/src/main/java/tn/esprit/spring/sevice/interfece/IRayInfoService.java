
package tn.esprit.spring.sevice.interfece;

import java.util.List;

import tn.esprit.spring.entity.Product;

import tn.esprit.spring.entity.Ray;


public interface IRayInfoService {
	public void addRay(Ray client);
	public void addProductAndAssignToRay(Product product, Long rayId);
	public Ray getRayById(Long clientId);////
	public void updateRayInfoById(Ray ray, Long rayId);
	public Product getProductById(Long productId);
	public List<Product> getAllProducts();
	public void deleteRay(Ray ray);
	public Long countRays();
	public void deleteAllProducts();
	public void updateProduct(Long productId, Product product);
}