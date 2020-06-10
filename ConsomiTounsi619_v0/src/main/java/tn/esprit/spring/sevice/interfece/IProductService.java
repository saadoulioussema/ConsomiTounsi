package tn.esprit.spring.sevice.interfece;

import java.util.List;
import java.util.Optional;

import tn.esprit.spring.entity.Product;



public interface IProductService {
	
	public Product addProduct(Product product);

	public Product updateProduct(Product product,Long barCode);

	public  List<Product> findAll();

	public Product getProductBybarCode(Long barCode);

	public boolean removeProduct(Long barCode);

	public List<Product> searchProducts(String productName, String categoryName);

	public boolean existsById(Long id);

	public Optional<Product> findById(Long id);

	public List<Product> findProductByName(String name);

	//List<Product> getProductsByCategory(Long idCategory);
	
	public List<Product> getProducts();

}
