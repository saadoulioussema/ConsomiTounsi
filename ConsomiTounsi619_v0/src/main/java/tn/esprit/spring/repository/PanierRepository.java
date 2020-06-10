package tn.esprit.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.Panier;
import tn.esprit.spring.entity.Product_Line;
import tn.esprit.spring.entity.User;

@Repository
public interface PanierRepository extends CrudRepository<Panier, Integer>{
	
	@Query("select p from Panier p where p.isValid=false and p.user=:user ")
	public List<Panier> findMyChartJPQL(@Param("user")User user);
	
	@Query("select p.productlines from Panier p where p.idPanier=:id ")
	public List<Product_Line> findProdLinesChart(@Param("id")int id);
	
	@Query("select p.productlines from Panier p where p.isValid=true ")
	public List<Product_Line> getAllProdLinesOfValidChart();
	
	@Query("select p.productlines from Panier p where p.isValid=false and p.user=:user ")
	public List<Product_Line> findMyChartProdLines(@Param("user")User user);

	@Query("select p from Panier p where p.isValid=true and p.user=:user ")
	public List<Panier> findMyValidChartJPQL(@Param("user")User user);
	
	
}
