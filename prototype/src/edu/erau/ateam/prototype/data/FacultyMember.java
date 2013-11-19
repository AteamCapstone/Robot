package edu.erau.ateam.prototype.data;

import java.net.MalformedURLException;
import java.net.URL;

/** A class that holds information about a professor */
public final class FacultyMember {
	/** the name of the faculty member */
	private String name;
	private String roomNum = "###";
	private String email = "myname@my.erau.edu";
	public int id = 2;
	private URL feedURL;
	private WeeklySchedule schedule = null;
	private Scheduler scheduler;
	
	public Scheduler getScheduler() {
		return scheduler;
	}

	public void setScheduler(Scheduler scheduler) {
		this.scheduler = scheduler;
	}

	/** Constructs a class with the given name 
	 * @param i */
	public FacultyMember(int id, String name, Scheduler scheduler) {
		this.id = id;
		this.name = name;
		this.scheduler = scheduler;
		try {
			this.feedURL = new URL("https://www.google.com/calendar/feeds/nickvic1707%40gmail.com/public/full");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}	
	}
	
	/** A getter for the professor's name */
	public String getName() {
		return name;
	}

	public String getRoomNum() {
		return roomNum;
	}
	
	public String getEmail() {
		return email;
	}

	/** A setter to change the professor's name, cannot be null */
	public void setName(String name) throws NullPointerException{
		if(name == null) throw new NullPointerException();
		this.name = name;
	}
	
	/** Updates the schedule for this professor */
	public void updateSchedule(){
		if(scheduler!=null) setSchedule(scheduler.getSchedule(feedURL));
	}

	public WeeklySchedule getSchedule() {
		return schedule;
	}

	public void setSchedule(WeeklySchedule schedule) throws NullPointerException{
		this.schedule = schedule;
	}
}