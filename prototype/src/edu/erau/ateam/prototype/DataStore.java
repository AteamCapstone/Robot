package edu.erau.ateam.prototype;

import java.util.Arrays;
import java.util.Collection;
import edu.erau.ateam.prototype.data.Professor;

/** hardcode the data however you like */
public final class DataStore{
	private static final DataStore instance = new DataStore();
	
	static final int examples = 25;
	
	private final Professor[] professors = new Professor[examples];//10 is an arbitrary number;
	
	private DataStore(){
		//hardcode data here. feel free to change this up
		for(int i = 0; i<examples;i++) professors[i] = new Professor("Professor "+(i+1));
	}
	
	void updateData(){		
		//NICK add parsing data here.
	}
	
	public static DataStore getInstance(){
		return instance;
	}
	
	/** choose collection as it is easy to support for later changes.  an array may not be the most ideal way 
	 * @author Chris Kestler*/
	public Collection<Professor> getProfessors(){
		return Arrays.asList(professors);
	}
}
