package edu.erau.ateam.prototype.data;

import java.util.Collections;
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
	
	public void sortDailyScheduler() {
		Collections.sort(list, new sortTimeslots());
	}
	
	public class sortTimeslots implements java.util.Comparator<Timeslot> {
		public int compare(Timeslot ts1, Timeslot ts2)  {
			return ts1.getStartStamp().compareTo(ts2.getStartStamp());
		}
	}
}
