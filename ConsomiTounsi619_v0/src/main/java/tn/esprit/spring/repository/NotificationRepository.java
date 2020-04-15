package tn.esprit.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.Notification;
import tn.esprit.spring.entity.User;

 @Repository
public interface NotificationRepository extends CrudRepository<Notification, Long>,JpaRepository<Notification, Long> {

	@Query("SELECT n FROM Notification n WHERE n.user=:userid")
	List<Notification> myNotifications(@Param("userid") User userid);
	
}
