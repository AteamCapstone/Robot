package edu.erau.ateam.robot.data;

import java.util.Arrays;
import java.util.Collection;

/** A class used to hardcode the faculty data and store parsed calendar information. */
public final class DataStore{
	/** The singleton instance of this class */
	private static final DataStore instance = new DataStore();
	
	/** THe number of fake facultyMembers, feel free to remove when adding more complex data */
	static final int examples = 6;
	
	/** An array of all facultyMembers stored in the system */
	private final FacultyMember[] facultyMembers = new FacultyMember[examples];//10 is an arbitrary number;
	
	/** A constructor that populates the data */
	private DataStore(){
		//hardcode data here. feel free to change this up
		for(int i = 0; i<examples;i++) facultyMembers[i] = new FacultyMember(i+1,"FacultyMember "+(i+1), new GoogleCalendarScheduler());
	}
	
	/** Updates all the google calendar information */
	public void updateScheduleData(){		
		for(FacultyMember facultyMember : facultyMembers)facultyMember.updateSchedule();
	}
	
	/** gets the singleton instance of the Datastore */
	public static DataStore getInstance(){
		return instance;
	}
	
	/** returns a collection of all facultyMembers in the system.
	 * choose collection as it is easy to support for later changes.  
	 * It is currently an array, but this may not be the most ideal way */
	public Collection<FacultyMember> getProfessors(){
		return Arrays.asList(facultyMembers);
	}
}
