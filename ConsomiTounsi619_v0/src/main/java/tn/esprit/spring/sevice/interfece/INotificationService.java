package tn.esprit.spring.sevice.interfece;

import java.util.List;

import tn.esprit.spring.entity.Event;
import tn.esprit.spring.entity.Notification;

public interface INotificationService {
	
	public void notifyAllUser(Event ev);
	public List<Notification> myNotifications(); 
}
