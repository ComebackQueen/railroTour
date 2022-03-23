package net.search.db;

import java.util.ArrayList;

public class DetailInfo_Review {
	ArrayList<String> imgfile=new ArrayList<String>();
	ArrayList<String> hashtag=new ArrayList<String>();
	int contentid, contenttypeid, like_yn, review_num;
	String memo, nikname, date, email_id, profile_img;
	
	public String getProfile_img() {
		return profile_img;
	}
	public void setProfile_img(String profile_img) {
		this.profile_img = profile_img;
	}
	public String getEmail_id() {
		return email_id;
	}
	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}
	public ArrayList<String> getImgfile() {
		return imgfile;
	}
	public void setImgfile(ArrayList<String> imgfile) {
		this.imgfile = imgfile;
	}
	public ArrayList<String> getHashtag() {
		return hashtag;
	}
	public void setHashtag(ArrayList<String> hashtag) {
		this.hashtag = hashtag;
	}
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
	public int getLike_yn() {
		return like_yn;
	}
	public void setLike_yn(int like_yn) {
		this.like_yn = like_yn;
	}
	public int getReview_num() {
		return review_num;
	}
	public void setReview_num(int review_num) {
		this.review_num = review_num;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getNikname() {
		return nikname;
	}
	public void setNikname(String nikname) {
		this.nikname = nikname;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
}
