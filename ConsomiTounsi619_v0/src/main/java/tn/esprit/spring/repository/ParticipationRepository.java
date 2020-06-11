package tn.esprit.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.Event;
import tn.esprit.spring.entity.Participation;
import tn.esprit.spring.entity.User;

@Repository
public interface ParticipationRepository extends CrudRepository<Participation,Long>, JpaRepository<Participation,Long> {
	
	@Query("SELECT p FROM Participation p WHERE p.user=:user")
	List<Participation> myParticipations(@Param ("user") User user);
	
	@Query("SELECT p FROM Participation p WHERE p.event=:event")
	List<Participation> Participations(@Param ("event") Event event);

	@Query("SELECT p FROM Participation p WHERE p.event=:event and p.user=:user")
	List<Participation> particip(@Param ("event") Event event, @Param ("user") User user);
}
