package net.note.db;

public class Note_Step2_ALL_INFO_Bean {
	String addr1;
	String addr2;
	int areacode;
	String cat1;
	String cat2;
	public void setCat1(String cat1) {
		this.cat1 = cat1;
	}
	public void setCat2(String cat2) {
		this.cat2 = cat2;
	}
	public void setCat3(String cat3) {
		this.cat3 = cat3;
	}
	String cat3;
	int content_id;
	int contenttype_id;
	double mapx;
	double mapy;
	int sigungucode;
	String title;
	String firstimage2;
	String firstimage;
	
	public String getAddr2() {
		return addr2;
	}
	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}
	public String getFirstimage2() {
		return firstimage2;
	}
	public void setFirstimage2(String firstimage2) {
		this.firstimage2 = firstimage2;
	}
	public String getFirstimage() {
		return firstimage;
	}
	public void setFirstimage(String firstimage) {
		this.firstimage = firstimage;
	}
	public String getAddr1() {
		return addr1;
	}
	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}
	public int getAreacode() {
		return areacode;
	}
	public void setAreacode(int areacode) {
		this.areacode = areacode;
	}
	
	public String getCat1() {
		return cat1;
	}
	public String getCat2() {
		return cat2;
	}
	public String getCat3() {
		return cat3;
	}
	public int getContent_id() {
		return content_id;
	}
	public void setContent_id(int content_id) {
		this.content_id = content_id;
	}
	public int getContenttype_id() {
		return contenttype_id;
	}
	public void setContenttype_id(int contenttype_id) {
		this.contenttype_id = contenttype_id;
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
	public int getSigungucode() {
		return sigungucode;
	}
	public void setSigungucode(int sigungucode) {
		this.sigungucode = sigungucode;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
}
