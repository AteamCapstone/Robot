package edu.erau.ateam.prototype;

import java.util.Arrays;
import java.util.Collection;
import edu.erau.ateam.prototype.data.Professor;

/** A class used to hardcode the faculty data and store parsed calendar information. */
public final class DataStore{
	/** The singleton instance of this class */
	private static final DataStore instance = new Database();
	
	/** THe number of fake professors, feel free to remove when adding more complex data */
	static final int examples = 25;
	
	/** An array of all professors stored in the system */
	private final Professor[] professors = new Professor[examples];//10 is an arbitrary number;
	
	/** A constructor that populates the data */
	private DataStore(){
		//hardcode data here. feel free to change this up
		for(int i = 0; i<examples;i++) professors[i] = new Professor("Professor "+(i+1));
	}
	
	void updateData(){		
		//NICK add parsing data here.
	}
	
	/** gets the singleton instance of the Datastore */
	public static DataStoregetInstance(){
		return instance;
	}
	
	/** returns a collection of all professors in the system.
	 * choose collection as it is easy to support for later changes.  
	 * It is currently an array, but this may not be the most ideal way */
	public Collection<Professor> getProfessors(){
		return Arrays.asList(professors);
	}
}
