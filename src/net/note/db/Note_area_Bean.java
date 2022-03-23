package net.note.db;

public class Note_area_Bean {
	private int travel_id;
	private String area;
	private String day;
	public Note_area_Bean(String area, String day) {
		this.area=area;
		this.day=day;
	}
	public int getTravel_id() {
		return travel_id;
	}
	public void setTravel_id(int travel_id) {
		this.travel_id = travel_id;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
}
