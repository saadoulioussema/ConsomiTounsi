package tn.esprit.spring.sevice.interfece;

import java.util.List;
import tn.esprit.spring.entity.Notification;

public interface INotificationService {
	
	void notifyAllUser(String eventName, String eventGoal);
	List<Notification> myNotifications(); 
}
