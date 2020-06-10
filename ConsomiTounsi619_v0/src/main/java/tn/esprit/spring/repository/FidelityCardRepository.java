package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.FidelityCard;
import tn.esprit.spring.entity.User;

@Repository
public interface FidelityCardRepository extends JpaRepository<FidelityCard,Integer>{
	
	@Query("select f from FidelityCard f where f.UserDetail=:user")
	public FidelityCard getFidelityCardByUser(@Param("user") User user);

}
