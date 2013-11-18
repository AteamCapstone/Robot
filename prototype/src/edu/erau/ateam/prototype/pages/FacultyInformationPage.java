package edu.erau.ateam.prototype.pages;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import edu.erau.ateam.prototype.LinkedPage;
import edu.erau.ateam.prototype.data.DailySchedule;
import edu.erau.ateam.prototype.data.FacultyMember;
import edu.erau.ateam.prototype.data.Timeslot;
import edu.erau.ateam.prototype.data.Weekday;
import edu.erau.ateam.prototype.data.WeeklySchedule;

@SuppressWarnings("serial")
/** A page that shows faculty information.  Currently a placeholder to be fleshed out later*/
public class FacultyInformationPage extends LinkedPage{
	private final FacultyMember facultyMember;
	
	private String [] Header = {"Time","Monday","Tuesday","Wednesday","Thursday","Friday"}; 
    private String [] Time = {"8:00","8:30","9:00","9:30","10:00","10:30","11:00",
    		"11:30","12:00","12:30","13:00","13:30","14:00","14:30","15:00","15:30",
    		"16:00","16:30","17:00","17:30","18:00","18:30","19:00","19:30","20:00"};
    private String[][] Schedule = new String [25][6];
    private String [][] info = new String [1][4];
    //based on hardcoded sample size in Datastore
    private String [][] nameRoom= {{"Bill","001"},{"Mark","002"},{"William","003"},{"James","004"},
    		{"Henry","005"},{"Vicent","006"},{"Fred","007"},{"John","008"},{"Michael","009"},
    		{"Brooke","010"},{"Karl","011"},{"Louis","012"},{"Kris","013"},{"Eddy","014"},
    		{"Peter","015"},{"Thomas","016"},{"Bruce","017"},{"David","018"},{"Richard","019"},
    		{"Nick","020"},{"Sam","021"},{"Lewis","022"},{"Neil","023"},{"Paul","001"},{"Andy","024"},{"Jeff","025"}}; 
    
	/** The constructor for configuring this panel */
	FacultyInformationPage(SelectFacultyPage listPage, FacultyMember facultyMember) {
		super(listPage);
		this.facultyMember = facultyMember;
		WeeklySchedule ws = facultyMember.getSchedule();
		//populates general info
		info[0][0]=facultyMember.id+"";
		info[0][1]=nameRoom[facultyMember.id-1][0];
        info[0][2]=nameRoom[facultyMember.id-1][1];
        //populates Time column with specified time range
		for(int i = 0; i < Time.length; i++){
			Schedule[i][0]= Time[i];
		}
		
		//Get WeeklySchedule from faculty member object and populate the JTable with the correpsonding information
		//Go to the following URL to check if JTable displays correctly
		//https://www.google.com/calendar/embed?src=nickvic1707%40gmail.com&ctz=America/New_York
		if(ws != null) {
			int column = 1;
			for(Weekday day : Weekday.values()) {
				DailySchedule ds = ws.getDailySchedule(day);
				for(int i = 0; i < ds.getList().size(); i++) {
					Timeslot ts = ds.getList().get(i);
					String temp = ts.formatTimestamp();
					//find matches and print title to proper Schedule index
					for(int row = 0; row < Time.length; row++) {
						if(Time[row].equals(temp)) {
							Schedule[row][column] = ts.getTitle();
						}
					}	
				}
				column++;
			}
		}
		
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		JLabel name = new JLabel("Name:    " + info[0][1]);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(40,0,0,0);  //top padding
		c.gridx = 0;
		c.gridy = 0;
		add(name, c);
		JLabel roomNum = new JLabel("Room #: " + info[0][2]);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,0,0,0);  //reset top padding
		c.gridx = 0;
		c.gridy = 1;
		add(roomNum, c);
		JLabel email = new JLabel("Email:     " + "N/A");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 2;
		add(email, c);
		JLabel status = new JLabel("Status:  " + " N/A");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 3;
		add(status, c);
		JTable scheduleTable = new JTable(Schedule,Header);
		JScrollPane JS1= new JScrollPane();
		JS1.setViewportView(scheduleTable);
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(95,0,0,0);  //top padding
		c.weightx = 1;
		c.weighty = 1;
		c.gridx = 0;
		c.gridy = 4;
		add(JS1, c);
	}

	/** The name of this page is the facultyMember name */
	@Override
	protected String getPageName() {
		return facultyMember.getName();
	}
}
