package net.note.db;

public class Note_Plans_List_Bean {
	int Note_ID, View, Like, day; //노트 번호, 조회수, 좋아요 수, 일 수
	String Travel_Day, Note_Name, Tema_Name, Area, Name, img, email_id, profileimg, totalcount; //출발일, 노트명, 테마명, 지역, 작성자명, 썸네일
	
	public String getTotalcount() {
		return totalcount;
	}
	public void setTotalcount(String totalcount) {
		this.totalcount = totalcount;
	}
	public String getProfileimg() {
		return profileimg;
	}
	public void setProfileimg(String profileimg) {
		this.profileimg = profileimg;
	}
	public String getEmail_id() {
		return email_id;
	}
	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public int getNote_ID() {
		return Note_ID;
	}
	public void setNote_ID(int note_ID) {
		Note_ID = note_ID;
	}
	public int getView() {
		return View;
	}
	public void setView(int view) {
		View = view;
	}
	public int getLike() {
		return Like;
	}
	public void setLike(int like) {
		Like = like;
	}
	public String getTravel_Day() {
		return Travel_Day;
	}
	public void setTravel_Day(String travel_Day) {
		Travel_Day = travel_Day;
	}
	public String getNote_Name() {
		return Note_Name;
	}
	public void setNote_Name(String note_Name) {
		Note_Name = note_Name;
	}
	public String getTema_Name() {
		return Tema_Name;
	}
	public void setTema_Name(String tema_Name) {
		Tema_Name = tema_Name;
	}
	public String getArea() {
		return Area;
	}
	public void setArea(String area) {
		Area = area;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public void PlusArea(String area) {
		Area+=area;
	}
}
