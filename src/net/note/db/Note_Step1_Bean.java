package net.note.db;

public class Note_Step1_Bean {
	private int travel_id;
	private String email_id;
	private String note_name;
	private String travel_start_day;
	private String travel_day;
	private String tema;
	private String travel_people;
	public int getTravel_id() {
		return travel_id;
	}
	public void setTravel_id(int travel_id) {
		this.travel_id = travel_id;
	}
	public String getEmail_id() {
		return email_id;
	}
	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}
	public String getNote_name() {
		return note_name;
	}
	public void setNote_name(String note_name) {
		this.note_name = note_name;
	}
	public String getTravel_start_day() {
		return travel_start_day;
	}
	public void setTravel_start_day(String travel_start_day) {
		this.travel_start_day = travel_start_day;
	}
	public String getTravel_day() {
		return travel_day;
	}
	public void setTravel_day(String string) {
		this.travel_day = string;
	}
	public String getTema() {
		return tema;
	}
	public void setTema(String tema) {
		this.tema = tema;
	}
	public String getTravel_people() {
		return travel_people;
	}
	public void setTravel_people(String string) {
		this.travel_people = string;
	}
	public String toString() {
		return "이메일:"+this.email_id+" 노트명:"+this.note_name+" 출발일:"+this.travel_start_day+"여행일수 : "+this.travel_day+"여행테마:"+this.tema+"여행인원수:"+this.travel_people;
	}
}
