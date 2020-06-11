package tn.esprit.spring.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
	private String description;
	private float goal;
	private float collAmount;
	private int placesNbr;
	private int participantsNbr;
	private boolean EarlyBirdOpt;
	private int NbrEarlyBirdTickets;
	private int discountPercentage;
	private float ticketPrice;
	@Temporal(TemporalType.DATE)
	private Date date;
	@Temporal(TemporalType.TIME)
	private Date hour;
	private String location;
	private String poster;
	private int views;
	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="jackpot_id")
	private Jackpot jackpot;
	@OneToMany(cascade=CascadeType.ALL, mappedBy="event")
	private List<Notification> notification;
	@OneToMany(cascade=CascadeType.ALL, mappedBy="event")
	private List<Participation> participation;
	@OneToMany(cascade=CascadeType.ALL, mappedBy="event")
	private List<Contribution> contribution;
	
	public Event() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Event(Long id, EventCategory category, String name, String description, float goal, float collAmount,
			int placesNbr, int participantsNbr, boolean earlyBirdOpt, int nbrEarlyBirdTickets, int discountPercentage,
			float ticketPrice, Date date, Date hour, String location, String poster, int views, Jackpot jackpot,
			List<Notification> notification, List<Participation> participation, List<Contribution> contribution) {
		super();
		this.id = id;
		this.category = category;
		this.name = name;
		this.description = description;
		this.goal = goal;
		this.collAmount = collAmount;
		this.placesNbr = placesNbr;
		this.participantsNbr = participantsNbr;
		EarlyBirdOpt = earlyBirdOpt;
		NbrEarlyBirdTickets = nbrEarlyBirdTickets;
		this.discountPercentage = discountPercentage;
		this.ticketPrice = ticketPrice;
		this.date = date;
		this.hour = hour;
		this.location = location;
		this.poster = poster;
		this.views = views;
		this.jackpot = jackpot;
		this.notification = notification;
		this.participation = participation;
		this.contribution = contribution;
	}

	public Event(EventCategory category, String name, String description, Date date, Date hour, String poster,
			boolean earlyBirdOpt, int nbrEarlyBirdTickets, int discountPercentage, float ticketPrice,
			int placesNbr, String location, float goal) {
		this.category = category;
		this.name = name;
		this.description = description;
		this.goal = goal;
		this.placesNbr = placesNbr;
		EarlyBirdOpt = earlyBirdOpt;
		NbrEarlyBirdTickets = nbrEarlyBirdTickets;
		this.discountPercentage = discountPercentage;
		this.ticketPrice = ticketPrice;
		this.date = date;
		this.hour = hour;
		this.location = location;
		this.poster = poster;
		}

	public Event(Long id, EventCategory category, String name, String description, Date date, Date hour,
			String poster, boolean earlyBirdOpt, int nbrEarlyBirdTickets, int discountPercentage,
			float ticketPrice, int placesNbr, String location, float goal) {
		this.id = id;
		this.category = category;
		this.name = name;
		this.description = description;
		this.goal = goal;
		this.placesNbr = placesNbr;
		EarlyBirdOpt = earlyBirdOpt;
		NbrEarlyBirdTickets = nbrEarlyBirdTickets;
		this.discountPercentage = discountPercentage;
		this.ticketPrice = ticketPrice;
		this.date = date;
		this.hour = hour;
		this.location = location;
		this.poster = poster;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getGoal() {
		return goal;
	}

	public void setGoal(float goal) {
		this.goal = goal;
	}

	public float getCollAmount() {
		return collAmount;
	}

	public void setCollAmount(float collAmount) {
		this.collAmount = collAmount;
	}

	public int getPlacesNbr() {
		return placesNbr;
	}

	public void setPlacesNbr(int placesNbr) {
		this.placesNbr = placesNbr;
	}

	public int getParticipantsNbr() {
		return participantsNbr;
	}

	public void setParticipantsNbr(int participantsNbr) {
		this.participantsNbr = participantsNbr;
	}

	public boolean isEarlyBirdOpt() {
		return EarlyBirdOpt;
	}

	public void setEarlyBirdOpt(boolean earlyBirdOpt) {
		EarlyBirdOpt = earlyBirdOpt;
	}

	public int getNbrEarlyBirdTickets() {
		return NbrEarlyBirdTickets;
	}

	public void setNbrEarlyBirdTickets(int nbrEarlyBirdTickets) {
		NbrEarlyBirdTickets = nbrEarlyBirdTickets;
	}

	public int getDiscountPercentage() {
		return discountPercentage;
	}

	public void setDiscountPercentage(int discountPercentage) {
		this.discountPercentage = discountPercentage;
	}

	public float getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(float ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getHour() {
		return hour;
	}

	public void setHour(Date hour) {
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

	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
	}

	public Jackpot getJackpot() {
		return jackpot;
	}

	public void setJackpot(Jackpot jackpot) {
		this.jackpot = jackpot;
	}

	public List<Notification> getNotification() {
		return notification;
	}

	public void setNotification(List<Notification> notification) {
		this.notification = notification;
	}

	public List<Participation> getParticipation() {
		return participation;
	}

	public void setParticipation(List<Participation> participation) {
		this.participation = participation;
	}

	public List<Contribution> getContribution() {
		return contribution;
	}

	public void setContribution(List<Contribution> contribution) {
		this.contribution = contribution;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (EarlyBirdOpt ? 1231 : 1237);
		result = prime * result + NbrEarlyBirdTickets;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + Float.floatToIntBits(collAmount);
		result = prime * result + ((contribution == null) ? 0 : contribution.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + discountPercentage;
		result = prime * result + Float.floatToIntBits(goal);
		result = prime * result + ((hour == null) ? 0 : hour.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((jackpot == null) ? 0 : jackpot.hashCode());
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((notification == null) ? 0 : notification.hashCode());
		result = prime * result + participantsNbr;
		result = prime * result + ((participation == null) ? 0 : participation.hashCode());
		result = prime * result + placesNbr;
		result = prime * result + ((poster == null) ? 0 : poster.hashCode());
		result = prime * result + Float.floatToIntBits(ticketPrice);
		result = prime * result + views;
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
		if (EarlyBirdOpt != other.EarlyBirdOpt)
			return false;
		if (NbrEarlyBirdTickets != other.NbrEarlyBirdTickets)
			return false;
		if (category != other.category)
			return false;
		if (Float.floatToIntBits(collAmount) != Float.floatToIntBits(other.collAmount))
			return false;
		if (contribution == null) {
			if (other.contribution != null)
				return false;
		} else if (!contribution.equals(other.contribution))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (discountPercentage != other.discountPercentage)
			return false;
		if (Float.floatToIntBits(goal) != Float.floatToIntBits(other.goal))
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
		if (notification == null) {
			if (other.notification != null)
				return false;
		} else if (!notification.equals(other.notification))
			return false;
		if (participantsNbr != other.participantsNbr)
			return false;
		if (participation == null) {
			if (other.participation != null)
				return false;
		} else if (!participation.equals(other.participation))
			return false;
		if (placesNbr != other.placesNbr)
			return false;
		if (poster == null) {
			if (other.poster != null)
				return false;
		} else if (!poster.equals(other.poster))
			return false;
		if (Float.floatToIntBits(ticketPrice) != Float.floatToIntBits(other.ticketPrice))
			return false;
		if (views != other.views)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Event [id=" + id + ", category=" + category + ", name=" + name + ", description=" + description
				+ ", goal=" + goal + ", collAmount=" + collAmount + ", placesNbr=" + placesNbr + ", participantsNbr="
				+ participantsNbr + ", EarlyBirdOpt=" + EarlyBirdOpt + ", NbrEarlyBirdTickets=" + NbrEarlyBirdTickets
				+ ", discountPercentage=" + discountPercentage + ", ticketPrice=" + ticketPrice + ", date=" + date
				+ ", hour=" + hour + ", location=" + location + ", poster=" + poster + ", views=" + views + ", jackpot="
				+ jackpot + ", notification=" + notification + ", participation=" + participation + ", contribution="
				+ contribution + "]";
	}
	
	
}
