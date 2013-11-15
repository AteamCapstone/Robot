package edu.erau.ateam.prototype.data;


import java.sql.Timestamp;

public class Timeslot {
	private String title;
	private Timestamp startStamp;
	private Timestamp endStamp;
	private boolean available;
	
	public Timeslot(String title, Timestamp startStamp, Timestamp endStamp, boolean available) {
		this.title = title;
		this.startStamp = startStamp;
		this.endStamp = endStamp;
		this.available = available;
	}
	
	public String getTitle() {
		return title;
	}
	
	public Timestamp getStartStamp() {
		return startStamp;
	}

	public Timestamp getEndStamp() {
		return endStamp;
	}

	public boolean isAvailable() {
		return available;
	}
}
