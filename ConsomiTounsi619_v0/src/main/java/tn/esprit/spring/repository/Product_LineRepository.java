package tn.esprit.spring.repository;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.Product;
import tn.esprit.spring.entity.Product_Line;

@Repository
public interface Product_LineRepository extends JpaRepository<Product_Line, Integer>{

	
	
	
	@Query("select DISTINCT p from Product_Line p join p.produit u on u.barCode=:prodId where p.quantité=:quantity ")
	public List<Product_Line> findProductlinesByProductAndQuantityJPQL(@Param("prodId")int prodId,@Param("quantity")int quantity);
	
	
	@Query("Select p from Product_Line p "
			+ "where p.produit=:prod and "
			+ "p.dateprodline=:date ")
public Product_Line getProductLineByUserProductAndDate(@Param("prod")Product prod, @Param("date")Date date);
	

	@Query("select m.price from Product m where m.barCode=:prodId")
	public int getPriceProdJPQL(@Param("prodId")long prodId);
	
	@Query("select p from Product_Line p")
	public List<Product_Line> getAllProdlines();

	@Modifying
    @Transactional
    @Query("UPDATE Product_Line e SET e.quantité=:quant1 , e.prix=:prix1 where e.plId=:plId1")
    public void mettreAjourProductLineByIdJPQL(@Param("quant1")int quant1,@Param("prix1")float prix1, @Param("plId1")int plId1);

	@Query("select count(*) from Product_Line m")
	public int getLastProdLineJPQL();
}
