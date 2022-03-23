package net.note.db;

import java.util.Date;

public class Note_Step2_Select_Bean {
	int Travel_ID;
	String Email_ID;
	String Note_Name;
	Date Travel_Start_Day;
	int Travel_Day;
	String Travel_Tema;
	int Travel_People;;
	public int getTravel_ID() {
		return Travel_ID;
	}
	public void setTravel_ID(int travel_ID) {
		Travel_ID = travel_ID;
	}
	public String getEmail_ID() {
		return Email_ID;
	}
	public void setEmail_ID(String email_ID) {
		Email_ID = email_ID;
	}
	public String getNote_Name() {
		return Note_Name;
	}
	public void setNote_Name(String note_Name) {
		Note_Name = note_Name;
	}
	public Date getTravel_Start_Day() {
		return Travel_Start_Day;
	}
	public void setTravel_Start_Day(Date travel_Start_Day) {
		Travel_Start_Day = travel_Start_Day;
	}
	public int getTravel_Day() {
		return Travel_Day;
	}
	public void setTravel_Day(int travel_Day) {
		Travel_Day = travel_Day;
	}
	public String getTravel_Tema() {
		return Travel_Tema;
	}
	public void setTravel_Tema(String travel_Tema) {
		Travel_Tema = travel_Tema;
	}
	public int getTravel_People() {
		return Travel_People;
	}
	public void setTravel_People(int travel_People) {
		Travel_People = travel_People;
	}
}
