package tn.esprit.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.Event;
import tn.esprit.spring.entity.EventCategory;

@Repository
public interface EventRepository extends CrudRepository<Event,Long>, JpaRepository<Event,Long>{

	@Query("SELECT ev FROM Event ev WHERE ev.name=:name")
	Event findByName(@Param ("name") String name);
	
	@Query("SELECT ev FROM Event ev WHERE ev.category=:category")
	List<Event> filterByCategory(@Param ("category") EventCategory category);
	
//	@Modifying
//	@Query(value="INSERT INTO Participation (users_id,events_id) VALUES (:uid,:eid)", nativeQuery = true)
//	@Transactional
//	void participate(@Param ("uid") Long uid,@Param ("eid") Long eid);
}
