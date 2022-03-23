package net.note.db;

public class Note_Basic_Info_Bean {
	String Email_ID, Nikname, ProfileImg, BGImg, Date, Tema;
	//아이디, 닉네임, 이미지경로, 날짜, 테마
	int View, Follow_YN, Like_YN, Like, travel_day, days; 
	//조회수, 팔로워 유무, 좋아요 유무, 좋아요 수
	String note_name, End_Date;
	
	public String getEnd_Date() {
		return End_Date;
	}
	public void setEnd_Date(String end_Date) {
		End_Date = end_Date;
	}
	public int getDays() {
		return days;
	}
	public void setDays(int days) {
		this.days = days;
	}
	public String getEmail_ID() {
		return Email_ID;
	}
	public String getNote_name() {
		return note_name;
	}
	public void setNote_name(String string) {
		this.note_name = string;
	}
	public int getFollow_YN() {
		return Follow_YN;
	}
	public void setFollow_YN(int follow_YN) {
		Follow_YN = follow_YN;
	}
	public int getTravel_day() {
		return travel_day;
	}
	public void setTravel_day(int travel_day) {
		this.travel_day = travel_day;
	}
	public String getProfileImg() {
		return ProfileImg;
	}
	public void setProfileImg(String profileImg) {
		ProfileImg = profileImg;
	}
	public String getBGImg() {
		return BGImg;
	}
	public void setBGImg(String bGImg) {
		BGImg = bGImg;
	}
	public void setEmail_ID(String email_ID) {
		Email_ID = email_ID;
	}
	public String getNikname() {
		return Nikname;
	}
	public void setNikname(String nikname) {
		Nikname = nikname;
	}
	public String getDate() {
		return Date;
	}
	public void setDate(String date) {
		Date = date;
	}
	public String getTema() {
		return Tema;
	}
	public void setTema(String tema) {
		Tema = tema;
	}
	public int getView() {
		return View;
	}
	public void setView(int view) {
		View = view;
	}
	public int getFllow_YN() {
		return Follow_YN;
	}
	public void setFllow_YN(int fllow_YN) {
		Follow_YN = fllow_YN;
	}
	public int getLike_YN() {
		return Like_YN;
	}
	public void setLike_YN(int like_YN) {
		Like_YN = like_YN;
	}
	public int getLike() {
		return Like;
	}
	public void setLike(int like) {
		Like = like;
	}
}
