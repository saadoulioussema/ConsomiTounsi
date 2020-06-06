package tn.esprit.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.Recherche;
import tn.esprit.spring.entity.User;




@Repository
public interface RechercheRepository extends CrudRepository<Recherche,Long>, JpaRepository<Recherche,Long>{
	
	///list of subjects adeq to profile 
	//
	//
	Recherche findByTypeAndUser(String type, User user);
	 
	@Query("select s.type from Recherche s where s.nbr=:max")
	public String extract(@Param("max") Long max);
	
	
	@Query("select max(s.nbr) from Recherche s join s.user u where u.id=:id_user")
	public Long lemax(@Param("id_user") Long id_user);
	 
	 
	
	 
	 
	 
	 
	 
	 
}
