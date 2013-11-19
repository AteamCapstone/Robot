package edu.erau.ateam.prototype.pages;

import java.awt.GridBagConstraints;
import static java.awt.GridBagConstraints.*;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import edu.erau.ateam.prototype.LinkedPage;
import static edu.erau.ateam.prototype.Setting.*;
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
    private int [] timeNum ={800,830,900,930,1000,1030,1100,1130,1200,1230,1300,1330,1400,1430,
    		1500,1530,1600,1630,1700,1730,1800,1830,1900,1930,2000};
    private String[][] Schedule = new String [25][6];
    private String status="";

	/** The constructor for configuring this panel */
	FacultyInformationPage(SelectFacultyPage listPage, FacultyMember facultyMember) {
		super(listPage);
		this.facultyMember = facultyMember;
		WeeklySchedule ws = facultyMember.getSchedule();
		
		//initialize 2d array Schedule
		for(int i =0;i<25;i++){
			for(int j=0; j<6;j++){
				Schedule[i][j]="";
			}
		}
        //populates Time column with specified time range
		for(int i = 0; i < Time.length; i++){
			Schedule[i][0]= Time[i];
		}
		
		//get current time
		SimpleDateFormat df = new SimpleDateFormat("HHmm");
		String current = df.format(new Date());
		int currentTime = Integer.parseInt(current);
		System.out.println(currentTime);
		//get current day of week
		Calendar cal = Calendar.getInstance();
		int w = cal.get(Calendar.DAY_OF_WEEK)-1;
		System.out.println(w);
				
		//Get WeeklySchedule from faculty member object and populate the JTable with the correpsonding information
		//Go to the following URL to check if JTable displays correctly
		//https://www.google.com/calendar/embed?src=nickvic1707%40gmail.com&ctz=America/New_York
		if(ws != null) {
			int column = 1;
			for(Weekday day : Weekday.values()) {
				DailySchedule ds = ws.getDailySchedule(day);
				for(int i = 0; i < ds.getList().size(); i++) {
					Timeslot ts = ds.getList().get(i);
					int duration = ts.getDuration();
					String temp = ts.formatTimestamp();
					//find matches and print title to proper Schedule index
					for(int row = 0; row < Time.length; row++) {
						if(Time[row].equals(temp)) {
							while(duration > 0){
								Schedule[row][column] = ts.getTitle();
								row++;
								duration -= 30;
							}
						}
					}	
				}
				column++;
			}
		}
		
		//during work time
		if(2000>=currentTime&&currentTime>=800){
			int n=0;
			while(!(currentTime<timeNum[n+1]&&currentTime>=timeNum[n])){
				n++;		
			}
			System.out.println(Time[n]);
			System.out.println(w);
			if(Schedule[n][w].equals("Office Hours")) {
				status = "Available";
			}
			else if (!Schedule[n][w].equals("")) { //current activity i.e. class
				status = Schedule[n][w];
			}
			else {
				status = "Not available";
			}
			//System.out.println(current.getDayNum()]);
			n=0;
			}
		else {	//out of work time
			status = "Off campus";
		}
		
		setLayout(new GridBagLayout());
		makeLabels("Name:", facultyMember.getName(), 0);
		makeLabels("Room #:", facultyMember.getRoomNum(), 1);
		makeLabels("Email:", facultyMember.getEmail(), 2);
		makeLabels("Status:  ", status, 3);
		
		GridBagConstraints gbc = makeGbc(0, 4);
		JTable scheduleTable = new JTable(Schedule, Header);
		JScrollPane JS1= new JScrollPane();
		JS1.setViewportView(scheduleTable);
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(83,0,0,0);  //top padding
		gbc.gridwidth =2;
		gbc.weightx = 1;
		gbc.weighty = 1;
		add(JS1, gbc);
	}
	
	/** 
	 * Makes GridbagConstraints for placing a component with
	 * the given grid coordinates
	 */
	private GridBagConstraints makeGbc(int gridx, int gridy){
		final GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = gridx;
		gbc.gridy = gridy;
		return gbc;
	}
	
	/**
	 * Makes a row of JLabels with the given data
	 */
	private void makeLabels(String key, String value, int y){
		makeLabel(key,0,y);
		makeLabel(value,1,y);
	}
	
	/** Makes a label and places it at the x and y coords */
	private void makeLabel(String text, int x, int y){
		JLabel label = new JLabel(text,SwingConstants.LEFT);
		label.setFont(LARGE_FONT);
		
		GridBagConstraints gbc = makeGbc(x,y);
		gbc.anchor = WEST;
		add(label,gbc);
	}

	/** The name of this page is the facultyMember name */
	@Override
	protected String getPageName() {
		return facultyMember.getName();
	}
}
