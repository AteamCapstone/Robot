
package edu.erau.ateam.prototype.data;

import java.sql.Timestamp;

/** A class that stores parsed information from Google Calendar feed query */
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
	
	/**
	 * gets duration of Timeslot in minutes
	 */
	@SuppressWarnings("deprecation")
	public int getDuration() {
		int hour = getEndStamp().getHours() - getStartStamp().getHours();
		int min = getEndStamp().getMinutes() - getStartStamp().getMinutes();
		return (hour*60) + min;
	}
	
	/**
	 * 	build time string for comparison
	 */
	@SuppressWarnings("deprecation")
	public String formatTimestamp() {
		StringBuilder sb = new StringBuilder();
		sb.append(getStartStamp().getHours());
		sb.append(":");
		if(getStartStamp().getMinutes() < 10) {
			sb.append("0" + getStartStamp().getMinutes());
		} else {
			sb.append(getStartStamp().getMinutes());
		}
		return sb.toString();
	}
}
