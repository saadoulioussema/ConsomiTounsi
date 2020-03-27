package tn.esprit.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import tn.esprit.spring.entity.Recherche;


public interface RechercheRepository extends CrudRepository<Recherche,Long>, JpaRepository<Recherche,Long>{
	
	///list of subjects adeq to profile  
	
	 @Query("select s.type from Recherche s join s.user u where u.id=:id_user order by s.type ")
	 public List<String> extract(@Param("id_user") Long id_user);

	 
	 
	 
	 
	 
	 
}
