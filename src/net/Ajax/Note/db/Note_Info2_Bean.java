package net.Ajax.Note.db;

public class Note_Info2_Bean {
	String kinds_1, kinds_2, route_name, ImgSrc;
	int content_id, content_type_id, sigungu_code, area_code, day_orders, orders;
	double mapx, mapy;
	
	public String getImgSrc() {
		return ImgSrc;
	}
	public void setImgSrc(String imgSrc) {
		ImgSrc = imgSrc;
	}
	public String getKinds_1() {
		return kinds_1;
	}
	public void setKinds_1(String kinds_1) {
		this.kinds_1 = kinds_1;
	}
	public String getKinds_2() {
		return kinds_2;
	}
	public void setKinds_2(String kinds_2) {
		this.kinds_2 = kinds_2;
	}
	public String getRoute_name() {
		return route_name;
	}
	public void setRoute_name(String route_name) {
		this.route_name = route_name;
	}
	public int getContent_id() {
		return content_id;
	}
	public void setContent_id(int content_id) {
		this.content_id = content_id;
	}
	public int getContent_type_id() {
		return content_type_id;
	}
	public void setContent_type_id(int content_type_id) {
		this.content_type_id = content_type_id;
	}
	public int getSigungu_code() {
		return sigungu_code;
	}
	public void setSigungu_code(int sigungu_code) {
		this.sigungu_code = sigungu_code;
	}
	public int getArea_code() {
		return area_code;
	}
	public void setArea_code(int area_code) {
		this.area_code = area_code;
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
	public double getMapx() {
		return mapx;
	}
	public void setMapx(double mapx) {
		this.mapx = mapx;
	}
	public double getMapy() {
		return mapy;
	}
	public void setMapy(double mapy) {
		this.mapy = mapy;
	}
}
