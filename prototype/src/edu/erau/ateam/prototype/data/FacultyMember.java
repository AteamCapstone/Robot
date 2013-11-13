package edu.erau.ateam.prototype.data;

/** A class that holds information about a professor */
public final class FacultyMember {
	/** the name of the faculty member */
	private String name;
	public int id = 2;
	
	private Scheduler scheduler;
	
	public Scheduler getScheduler() {
		return scheduler;
	}

	public void setScheduler(Scheduler scheduler) {
		this.scheduler = scheduler;
	}

	private Schedule schedule = null;

	/** Constructs a class with the given name 
	 * @param i */
	public FacultyMember(int id, String name, Scheduler scheduler) {
		this.id = id;
		this.name = name;
		this.scheduler = scheduler;
	}
	
	/** A getter for the professor's name */
	public String getName() {
		return name;
	}

	/** A setter to change the professor's name, cannot be null */
	public void setName(String name) throws NullPointerException{
		if(name == null) throw new NullPointerException();
		this.name = name;
	}
	
	/** Updates the schedule for this professor */
	public void updateSchedule(){
		if(scheduler!=null) setSchedule(scheduler.getSchedule());
	}

	public Schedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule schedule) throws NullPointerException{
		if(schedule == null) throw new NullPointerException();
		this.schedule = schedule;
	}
}
