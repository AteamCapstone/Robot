package edu.erau.ateam.prototype.data;


import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

import com.google.gdata.client.calendar.CalendarQuery;
import com.google.gdata.client.calendar.CalendarService;
import com.google.gdata.data.DateTime;
import com.google.gdata.data.calendar.CalendarEventEntry;
import com.google.gdata.data.calendar.CalendarEventFeed;
import com.google.gdata.util.ServiceException;

/**
 * A class that contains the credentials to parse a GoogleCalender into a
 * Schedule class
 */
public class GoogleCalendarScheduler extends Scheduler {
	// *NOTE* URL must end in "full" to retrieve all information, i.e event times

	@Override
	public WeeklySchedule getSchedule(URL feedURL) {
		CalendarService myService = new CalendarService("Faculty Schedule");
		Calendar calendar = new GregorianCalendar();
		CalendarEventFeed myResultsFeed = null;
		
		// Create a new query object and set the parameters
		CalendarQuery myQuery = new CalendarQuery(feedURL);
		myQuery.setFullTextQuery(null);
		// required to standardize event type
		myQuery.setStringCustomParameter("singleevents", "true");
		
		// specify time range for events
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		calendar.set(Calendar.AM_PM, Calendar.AM);
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		DateTime startOfWeek = new DateTime(calendar.getTime(), TimeZone.getDefault());
		// System.out.println("Monday: " + startOfWeek);
		myQuery.setMinimumStartTime(startOfWeek);

		calendar.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
		calendar.set(Calendar.AM_PM, Calendar.PM);
		calendar.set(Calendar.HOUR, 11);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		DateTime endOfWeek = new DateTime(calendar.getTime(), TimeZone.getDefault());
		// System.out.println("Friday: " + endOfWeek + "\n");
		myQuery.setMaximumStartTime(endOfWeek);

		// Send the request with the built query URL
		try {
			myResultsFeed = myService.query(myQuery, CalendarEventFeed.class);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		
		WeeklySchedule ws = new WeeklySchedule();
        Calendar tempCalendar = new GregorianCalendar();
		for(Weekday day: Weekday.values()){
			DailySchedule ds = new DailySchedule(day);
			for(int i = 0; i < myResultsFeed.getEntries().size(); i++) {
				CalendarEventEntry entry = (CalendarEventEntry) myResultsFeed.getEntries().get(i);
				Timestamp startStamp = new Timestamp(entry.getTimes().get(0).getStartTime().getValue());
				tempCalendar.setTime(startStamp);
				if(day.name().equalsIgnoreCase(tempCalendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault()))) { //if timeslot is for the current weekday iteration add to dailyschedule
					String title = entry.getTitle().getPlainText();
					Timestamp endStamp = new Timestamp(entry.getTimes().get(0).getEndTime().getValue());
					boolean available = false;
					if(title.equalsIgnoreCase("Office Hours")){
						available = true;
					}
					Timeslot  ts = new Timeslot(title, startStamp, endStamp, available);
					ds.addToDailySchedule(ts);
				}
				
			}
			ws.addToWeeklySchedule(ds);
		}
		
		//Take entries in specified time range and print relevant info
		
		
		//go through feed, create timeslot, insert timeslot in order for the appropriate day
		
		// TODO PARSING CODE GOES HERE
		return null;
	}
}