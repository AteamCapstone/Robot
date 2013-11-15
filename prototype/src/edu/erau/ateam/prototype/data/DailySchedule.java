package edu.erau.ateam.prototype.data;


import java.util.LinkedList;
import java.util.List;

public class DailySchedule {
	private List<Timeslot> list;
	private Weekday day;
	
	public DailySchedule(Weekday day) {
		this.day = day;
		this.list = new LinkedList<Timeslot>();
	}

	public Weekday getDay() {
		return day;
	}

	public List<Timeslot> getList() {
		return list;
	}

	public void addToDailySchedule(Timeslot ts) {
		list.add(ts);
	}

	public List<Timeslot> getDailySchedule() {
		return list;
	}
}
