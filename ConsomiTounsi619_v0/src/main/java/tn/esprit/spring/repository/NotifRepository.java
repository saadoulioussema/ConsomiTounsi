package tn.esprit.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.Notif;
import tn.esprit.spring.entity.Notification;
import tn.esprit.spring.entity.User;

 @Repository
public interface NotifRepository extends CrudRepository<Notif, Long>,JpaRepository<Notif, Long> {

	@Query("SELECT n FROM Notif n WHERE n.user=:userid")
	List<Notif> myNotifications(@Param("userid") User userid);
	
}
