package edu.erau.ateam.prototype.data;

public class Professor {
	private String name;

	public Professor(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) throws NullPointerException{
		if(name == null) throw new NullPointerException();
		this.name = name;
	}
	
}
