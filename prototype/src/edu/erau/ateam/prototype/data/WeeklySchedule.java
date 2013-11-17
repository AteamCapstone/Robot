package edu.erau.ateam.prototype.data;

import java.util.EnumMap;
import java.util.Set;

/** Used to hold a professors parsed schedule */
public class WeeklySchedule {
	EnumMap<Weekday,DailySchedule> week;
	Set<Weekday> keySet;
	
	public WeeklySchedule() {
		week = new EnumMap<Weekday,DailySchedule>(Weekday.class);
        keySet = week.keySet();
	}

	public void addToWeeklySchedule(DailySchedule ds) {
		week.put(ds.getDay(), ds);
	}
	
	public DailySchedule getDailySchedule(Weekday day) {
		return week.get(day);
	}
	
	public void printWeeklySchedule() {
		for (Weekday day : keySet) {
			DailySchedule ds = week.get(day);
			for(int i = 0; i < ds.getList().size(); i++) {
	            Timeslot ts = ds.getList().get(i);
	            System.out.println(ts.getTitle());
	            System.out.println(ts.getStartStamp());
	            System.out.println(ts.getEndStamp());
	            System.out.println("------------------------------------------------------");
	        }
        }
	}
}
