package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.Participation;

@Repository
public interface ParticipationRepository extends CrudRepository<Participation,Long>, JpaRepository<Participation,Long> {

}
