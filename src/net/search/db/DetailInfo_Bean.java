package net.search.db;

import java.util.ArrayList;

public class DetailInfo_Bean {
	ArrayList<String> img=new ArrayList<String>();
	// 이미지
	String name, addr, menu, opentime, restday, homepage, overview, max_peo, capacity, stroller, creditcard_availability;
	// 이름ㅇ, 주소ㅇ, 대표메뉴ㅇ, 오픈시간, 쉬는날ㅇ, 홈피ㅇ, 개요ㅇ, 수용인원ㅇ, 유모차ㅇ, 신용카드 가능여부ㅇ
	String Pets_allowed; //애완동물 가능 여부ㅇ
	String Available_age; //체험가능 연령ㅇ
	String Experience; //체험 안내ㅇ
	String Contact_Information; //문의 및 안내ㅇ
	String Opening_date; //개장일ㅇ
	String Parking_facilities; //주차시설ㅇ
	String When_to_use; //이용시기ㅇ
	String Hours_of_use; //이용시간ㅇ
	String Discount_information; //할인정보ㅇ
	String playroom_availability;//놀이방 여부ㅇ
	String Opening_Date; //개업일
	String Packable;//포장여부
	String Reservation_Guide;//예약안내
	String Scale;//규모
	String Number_seats;//좌석수
	String Smoking_Smoking;//금연/흡연
	String Handling_menu; //취급메뉴
	double lat, lng;//ㅇ
	String telname;
	int contentid, contenttypeid;
	
	public int getContentid() {
		return contentid;
	}
	public void setContentid(int contentid) {
		this.contentid = contentid;
	}
	public int getContenttypeid() {
		return contenttypeid;
	}
	public void setContenttypeid(int contenttypeid) {
		this.contenttypeid = contenttypeid;
	}
	public void setRestday(String restday) {
		this.restday = restday;
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
	public String getTelname() {
		return telname;
	}
	public void setTelname(String telname) {
		this.telname = telname;
	}
	public ArrayList<String> getImg() {
		return img;
	}
	public void setImg(ArrayList<String> img) {
		this.img = img;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getMenu() {
		return menu;
	}
	public void setMenu(String menu) {
		this.menu = menu;
	}
	public String getOpentime() {
		return opentime;
	}
	public void setOpentime(String opentime) {
		this.opentime = opentime;
	}
	public String getRestday() {
		return restday;
	}


	public String getHomepage() {
		return homepage;
	}
	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}
	public String getOverview() {
		return overview;
	}
	public void setOverview(String overview) {
		this.overview = overview;
	}

	public String getMax_peo() {
		return max_peo;
	}
	public void setMax_peo(String max_peo) {
		this.max_peo = max_peo;
	}
	public String getCapacity() {
		return capacity;
	}
	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}
	public String getStroller() {
		return stroller;
	}
	public void setStroller(String stroller) {
		this.stroller = stroller;
	}
	public String getCreditcard_availability() {
		return creditcard_availability;
	}
	public void setCreditcard_availability(String creditcard_availability) {
		this.creditcard_availability = creditcard_availability;
	}
	public String getPets_allowed() {
		return Pets_allowed;
	}
	public void setPets_allowed(String pets_allowed) {
		Pets_allowed = pets_allowed;
	}
	public String getAvailable_age() {
		return Available_age;
	}
	public void setAvailable_age(String available_age) {
		Available_age = available_age;
	}
	public String getExperience() {
		return Experience;
	}
	public void setExperience(String experience) {
		Experience = experience;
	}
	public String getContact_Information() {
		return Contact_Information;
	}
	public void setContact_Information(String contact_Information) {
		Contact_Information = contact_Information;
	}
	public String getOpening_date() {
		return Opening_date;
	}
	public void setOpening_date(String opening_date) {
		Opening_date = opening_date;
	}
	public String getParking_facilities() {
		return Parking_facilities;
	}
	public void setParking_facilities(String parking_facilities) {
		Parking_facilities = parking_facilities;
	}
	public String getWhen_to_use() {
		return When_to_use;
	}
	public void setWhen_to_use(String when_to_use) {
		When_to_use = when_to_use;
	}
	public String getHours_of_use() {
		return Hours_of_use;
	}
	public void setHours_of_use(String hours_of_use) {
		Hours_of_use = hours_of_use;
	}
	public String getDiscount_information() {
		return Discount_information;
	}
	public void setDiscount_information(String discount_information) {
		Discount_information = discount_information;
	}
	public String getPlayroom_availability() {
		return playroom_availability;
	}
	public void setPlayroom_availability(String playroom_availability) {
		this.playroom_availability = playroom_availability;
	}
	public String getOpening_Date() {
		return Opening_Date;
	}
	public void setOpening_Date(String opening_Date) {
		Opening_Date = opening_Date;
	}
	public String getPackable() {
		return Packable;
	}
	public void setPackable(String packable) {
		Packable = packable;
	}
	public String getReservation_Guide() {
		return Reservation_Guide;
	}
	public void setReservation_Guide(String reservation_Guide) {
		Reservation_Guide = reservation_Guide;
	}
	public String getScale() {
		return Scale;
	}
	public void setScale(String scale) {
		Scale = scale;
	}
	public String getNumber_seats() {
		return Number_seats;
	}
	public void setNumber_seats(String number_seats) {
		Number_seats = number_seats;
	}
	public String getSmoking_Smoking() {
		return Smoking_Smoking;
	}
	public void setSmoking_Smoking(String smoking_Smoking) {
		Smoking_Smoking = smoking_Smoking;
	}
	public String getHandling_menu() {
		return Handling_menu;
	}
	public void setHandling_menu(String handling_menu) {
		Handling_menu = handling_menu;
	}
	
	
}
