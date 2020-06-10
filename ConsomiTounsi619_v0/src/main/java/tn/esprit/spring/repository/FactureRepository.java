package tn.esprit.spring.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.Facture;
import tn.esprit.spring.entity.Panier;
import tn.esprit.spring.entity.User;

@Repository
public interface FactureRepository extends JpaRepository<Facture, Integer>{

	@Query("select f from Facture f where f.panierDetail=:panierId")
	public Facture findMyFactureJPQL(@Param("panierId")Panier panierId);
	
	@Query("Select "
			+ "DISTINCT f from Facture f "
			+ "join f.panierDetail pan "
			+ "join pan.user us "
			+ "where us=:userId")
    public List<Facture> getAllMyBills(@Param("userId") User userId);
	
	
	
}
