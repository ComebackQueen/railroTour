package net.note.db;

public class Note_All_Plans_Bean {
	int Content_ID;//DB
    int Content_Type_ID;//DB
    int sigungucode;//DB
    int areacode;//DB
    String areaname;
    String date;//DB
    String week;//DB
    String day;//DB
    String Title;
    String Kind1;//DB
    String Kind2;//DB
    String img;
    double lat;
    double lng;
    String memo;//DB
    int day_orders;//DB
    int orders;//DB
    String email_id;
    
	public String getEmail_id() {
		return email_id;
	}
	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}
	public int getContent_ID() {
		return Content_ID;
	}
	public void setContent_ID(int content_ID) {
		Content_ID = content_ID;
	}
	public int getContent_Type_ID() {
		return Content_Type_ID;
	}
	public void setContent_Type_ID(int content_Type_ID) {
		Content_Type_ID = content_Type_ID;
	}
	public int getSigungucode() {
		return sigungucode;
	}
	public void setSigungucode(int sigungucode) {
		this.sigungucode = sigungucode;
	}
	public int getAreacode() {
		return areacode;
	}
	public void setAreacode(int areacode) {
		this.areacode = areacode;
	}
	public String getAreaname() {
		return areaname;
	}
	public void setAreaname(String areaname) {
		this.areaname = areaname;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getWeek() {
		return week;
	}
	public void setWeek(String week) {
		this.week = week;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getKind1() {
		return Kind1;
	}
	public void setKind1(String kind1) {
		Kind1 = kind1;
	}
	public String getKind2() {
		return Kind2;
	}
	public void setKind2(String kind2) {
		Kind2 = kind2;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLng() {
		return lng;
	}
	public void setLng(double lng) {
		this.lng = lng;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public int getDay_orders() {
		return day_orders;
	}
	public void setDay_orders(int day_orders) {
		this.day_orders = day_orders;
	}
	public int getOrders() {
		return orders;
	}
	public void setOrders(int orders) {
		this.orders = orders;
	}
}
