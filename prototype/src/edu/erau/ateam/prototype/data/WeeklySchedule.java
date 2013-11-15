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
}
