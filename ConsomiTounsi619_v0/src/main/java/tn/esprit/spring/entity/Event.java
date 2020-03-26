package tn.esprit.spring.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Event implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long id;
	@Enumerated(EnumType.STRING)
	private EventCategory category;
	private String name;
	private int placesNbr;
	@Temporal(TemporalType.DATE)
	private Date date;
	private String hour;
	private String location;
	private String poster;
	private int ticketPrice;
	private int eventCost;
	private boolean status;
	@OneToOne(mappedBy="event")
	private List <Jackpot> jackpot;
	@OneToMany(mappedBy="event")
	private List <Publicity> publicity;
	
	public Event() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Event(Long id, EventCategory category, String name, int placesNbr, Date date, String hour, String location,
			String poster, int ticketPrice, int eventCost, boolean status, List<Jackpot> jackpot,
			List<Publicity> publicity) {
		super();
		this.id = id;
		this.category = category;
		this.name = name;
		this.placesNbr = placesNbr;
		this.date = date;
		this.hour = hour;
		this.location = location;
		this.poster = poster;
		this.ticketPrice = ticketPrice;
		this.eventCost = eventCost;
		this.status = status;
		this.jackpot = jackpot;
		this.publicity = publicity;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EventCategory getCategory() {
		return category;
	}

	public void setCategory(EventCategory category) {
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPlacesNbr() {
		return placesNbr;
	}

	public void setPlacesNbr(int placesNbr) {
		this.placesNbr = placesNbr;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getHour() {
		return hour;
	}

	public void setHour(String hour) {
		this.hour = hour;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public int getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(int ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	public int getEventCost() {
		return eventCost;
	}

	public void setEventCost(int eventCost) {
		this.eventCost = eventCost;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public List<Jackpot> getJackpot() {
		return jackpot;
	}

	public void setJackpot(List<Jackpot> jackpot) {
		this.jackpot = jackpot;
	}

	public List<Publicity> getPublicity() {
		return publicity;
	}

	public void setPublicity(List<Publicity> publicity) {
		this.publicity = publicity;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + eventCost;
		result = prime * result + ((hour == null) ? 0 : hour.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((jackpot == null) ? 0 : jackpot.hashCode());
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + placesNbr;
		result = prime * result + ((poster == null) ? 0 : poster.hashCode());
		result = prime * result + ((publicity == null) ? 0 : publicity.hashCode());
		result = prime * result + (status ? 1231 : 1237);
		result = prime * result + ticketPrice;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Event other = (Event) obj;
		if (category != other.category)
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (eventCost != other.eventCost)
			return false;
		if (hour == null) {
			if (other.hour != null)
				return false;
		} else if (!hour.equals(other.hour))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (jackpot == null) {
			if (other.jackpot != null)
				return false;
		} else if (!jackpot.equals(other.jackpot))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (placesNbr != other.placesNbr)
			return false;
		if (poster == null) {
			if (other.poster != null)
				return false;
		} else if (!poster.equals(other.poster))
			return false;
		if (publicity == null) {
			if (other.publicity != null)
				return false;
		} else if (!publicity.equals(other.publicity))
			return false;
		if (status != other.status)
			return false;
		if (ticketPrice != other.ticketPrice)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Event [id=" + id + ", category=" + category + ", name=" + name + ", placesNbr=" + placesNbr + ", date="
				+ date + ", hour=" + hour + ", location=" + location + ", poster=" + poster + ", ticketPrice="
				+ ticketPrice + ", eventCost=" + eventCost + ", status=" + status + ", jackpot=" + jackpot
				+ ", publicity=" + publicity + "]";
	}

	
	
}
