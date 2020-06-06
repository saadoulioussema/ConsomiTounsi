package tn.esprit.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.Contribution;
import tn.esprit.spring.entity.Event;
import tn.esprit.spring.entity.User;

@Repository
public interface ContributionRepository extends CrudRepository<Contribution, Long>,JpaRepository<Contribution, Long> {
	
	@Query("SELECT c FROM Contribution c WHERE c.event=:event ")
	List<Contribution> contributionOfEvent(@Param("event") Event event);
	
	@Query("SELECT c FROM Contribution c WHERE c.user=:user ")
	List<Contribution> contributionOfUser(@Param("user") User user);

}
