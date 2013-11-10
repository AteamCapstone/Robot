package edu.erau.ateam.prototype.data;

/** A class that holds information about a professor */
public class Professor {
	/** the name of the faculty member */
	private String name;

	/** Constructs a class with the given name */
	public Professor(String name) {
		this.name = name;
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
	
}
