package net.note.db;

public class Note_Travel_Area_Bean {
	String travel_area_name, Date; //Date는 일단 보류
	int travel_area_day;
	
	public String getDate() {
		return Date;
	}
	public void setDate(String date) {
		Date = date;
	}
	public Note_Travel_Area_Bean(String area_name, int day) {
		travel_area_name=area_name;
		travel_area_day=day;
	}
	public String getTravel_area_name() {
		return travel_area_name;
	}
	public void setTravel_area_name(String travel_area_name) {
		this.travel_area_name = travel_area_name;
	}
	public int getTravel_area_day() {
		return travel_area_day;
	}
	public void setTravel_area_day(int travel_area_day) {
		this.travel_area_day = travel_area_day;
	}
	
	
}
