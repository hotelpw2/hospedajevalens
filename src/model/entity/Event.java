package model.entity;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Event {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long id;
	@Persistent
	private String eventName;
	@Persistent
	private String eventLocation;
	@Persistent
	private String eventSchedule;
	@Persistent
	private String eventDate;
	@Persistent
	private int eventCapacity;
	@Persistent
	private boolean eventStatus;
	@Persistent
	private String created;
	
	public Event(String name, String location, String schedule, String date, int capacity, boolean status, String created){
		this.eventName = name;
		this.eventLocation = location;
		this.eventSchedule = schedule;
		this.eventDate = date;
		this.eventCapacity = capacity;
		this.eventStatus = status;
		this.created = created;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return eventName;
	}

	public void setName(String name) {
		this.eventName = name;
	}

	public String getLocation() {
		return eventLocation;
	}

	public void setLocation(String location) {
		this.eventLocation = location;
	}

	public String getSchedule() {
		return eventSchedule;
	}

	public void setSchedule(String schedule) {
		this.eventSchedule = schedule;
	}

	public String getDate() {
		return eventDate;
	}

	public void setDate(String date) {
		this.eventDate = date;
	}

	public int getCapacity() {
		return eventCapacity;
	}

	public void setCapacity(int capacity) {
		this.eventCapacity = capacity;
	}

	public boolean getStatus() {
		return eventStatus;
	}

	public void setStatus(boolean status) {
		this.eventStatus = status;
	}
	
	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}
}