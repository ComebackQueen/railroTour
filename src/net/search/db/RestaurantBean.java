package net.search.db;

import org.json.simple.JSONObject;

public class RestaurantBean {
	private String Search_Word; //검색어
	private String result_Code; //결과 코드
	private String result_Msg; //결과 메시지
	private String Address1; //간략주소
	private String Address2; //상세주소
	private String img; //이미지
	private int maxX; //위도
	private int maxY; //경도
	private String title; //이름
	public String getSearch_Word() {
		return Search_Word;
	}
	public void setSearch_Word(String search_Word) {
		Search_Word = search_Word;
	}
	public String getResult_Code() {
		return result_Code;
	}
	public void setResult_Code(String result_Code) {
		this.result_Code = result_Code;
	}
	public String getResult_Msg() {
		return result_Msg;
	}
	public void setResult_Msg(String result_Msg) {
		this.result_Msg = result_Msg;
	}
	public String getAddress1() {
		return Address1;
	}
	public void setAddress1(String address1) {
		Address1 = address1;
	}
	public String getAddress2() {
		return Address2;
	}
	public void setAddress2(String address2) {
		Address2 = address2;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public int getMaxX() {
		return maxX;
	}
	public void setMaxX(int maxX) {
		this.maxX = maxX;
	}
	public int getMaxY() {
		return maxY;
	}
	public void setMaxY(int maxY) {
		this.maxY = maxY;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public JSONObject getJson() {
		   
	      JSONObject obj = new JSONObject();
	      

	      if(Search_Word != null)
	      obj.put("Search_Word",Search_Word);
	      if(result_Code != null)
	      obj.put("result_Code", result_Code);
	      if(result_Msg != null)
	      obj.put("result_Msg", result_Msg);
	      if(Address1 != null)
	      obj.put("Address1", Address1);
	      if(Address2 != null)
	      obj.put("Address2", Address2);
	      if(img != null)
	      obj.put("img", img);
	      if(maxX != 0)
	      obj.put("maxX", maxX);
	      if(maxY != 0)
	      obj.put("maxY", maxY);
	      if(title != null)
	      obj.put("title", title);
	      
	   
	      return obj;
	   }
}
