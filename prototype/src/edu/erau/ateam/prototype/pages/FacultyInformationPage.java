package edu.erau.ateam.prototype.pages;

import java.awt.BorderLayout;
import java.awt.Container;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import edu.erau.ateam.prototype.LinkedPage;
import edu.erau.ateam.prototype.Setting;
import edu.erau.ateam.prototype.data.DataStore;
import edu.erau.ateam.prototype.data.FacultyMember;

@SuppressWarnings("serial")
/** A page that shows faculty information.  Currently a placeholder to be fleshed out later*/
public class FacultyInformationPage extends LinkedPage{
	private final FacultyMember facultyMember;
	
	private String [] Header = {"Time","Monday","Tuesday","Wednesday","Thursday","Friday"}; 
    private String [] Time = {"8:00-8:30","8:30-9:00","9:00-9:30","9:30-10:00","10:00-10:30","10:30-11:00",
    		"11:00-11:30","11:30-12:00","12:00-12:30","12:30-13:00","13:00-13:30","13:30-14:00",
    		"14:00-14:30","14:30-15:00","15:00-15:30","15:30-16:00","16:00-16:30","16:30-17:00",
    		"17:00-17:30","17:30-18:00","18:00-18:30","18:30-19:00","19:00-19:30","19:30-20:00"};
    private String[][] Schedule = new String [24][6];
    private int [][] temp = new int [24][6];
    private String [] infoHeader = {"No.","Name", "Room No.","Status"};
    private String [][] info = new String [1][4];
    private String [][] nameRoom= {{"Bill","001"},{"Mark","002"},{"William","003"},{"James","004"},
    		{"Henry","005"},{"Vicent","006"},{"Fred","007"},{"John","008"},{"Michael","009"},{"Brooke","010"},
    		{"Karl","011"},{"Louis","012"},{"Kris","013"},{"Eddy","014"},{"Peter","015"},{"Thomas","016"},
    		{"Bruce","017"},{"David","018"},{"Richard","019"},{"Nick","020"},{"Sam","021"},{"Lewis","022"},
    		{"Neil","023"},{"Paul","001"},{"Andy","024"},{"Jeff","025"}}; 
    
	/** The constructor for configuring this panel */
	FacultyInformationPage(SelectFacultyPage listPage, FacultyMember facultyMember) {
		super(listPage);
		this.facultyMember = facultyMember;
		//populates general info JTable displaying id #, name, room #, and ***current status***
		info[0][0]=facultyMember.id+"";
		info[0][1]=nameRoom[facultyMember.id-1][0];
        info[0][2]=nameRoom[facultyMember.id-1][1];
		for(int i = 0; i < 24; i++){
			Schedule[i][0]= Time[i];
		}
		
		for(int j = 1; j < 6;j ++){
			for(int k = 0; k < 24; k ++){
				temp[k][j] = new Random().nextInt(2);
				if(temp[k][j]==0){
					Schedule[k][j]=" ";
				}
				else{Schedule[k][j]="Class";}
			}
		}
		setLayout(new BorderLayout(50,50));
		JTable scheduleTable =  new JTable(Schedule,Header);
		JScrollPane JS1=new JScrollPane();
		JS1.setViewportView(scheduleTable);
		add(JS1, BorderLayout.CENTER);
		JTable infoTable = new JTable(info,infoHeader);
		JScrollPane JS2 =new JScrollPane();
		JS2.setViewportView(infoTable);
		add(JS2, BorderLayout.SOUTH);
		//placeholder code below, @Jia feel free to delete this
		
		//JLabel placeholder = new JLabel(facultyMember.getName()+" information goes here");
		//placeholder.setAlignmentX(0.5f);
		//placeholder.setFont(Setting.LARGE_FONT);
		//placeholder.setHorizontalAlignment(JLabel.CENTER);
		//add(placeholder, BorderLayout.CENTER);
	}

	/** The name of this page is the facultyMember name */
	@Override
	protected String getPageName() {
		return facultyMember.getName();
	}
}
