package net.search.db;

import java.util.ArrayList;

public class Review_Bean {
	int contentid, contenttypeid, like_yn, review_num;
	String memo, datetime, profile_img, email_id, nikname;
	ArrayList<String> tags=new ArrayList<String>();
	ArrayList<String> filesPath=new ArrayList<String>();
	
	public String getNikname() {
		return nikname;
	}
	public void setNikname(String nikname) {
		this.nikname = nikname;
	}
	public String getEmail_id() {
		return email_id;
	}
	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}
	public String getProfile_img() {
		return profile_img;
	}
	public void setProfile_img(String profile_img) {
		this.profile_img = profile_img;
	}
	public int getReview_num() {
		return review_num;
	}
	public void setReview_num(int review_num) {
		this.review_num = review_num;
	}
	public String getDatetime() {
		return datetime;
	}
	public void setDatetime(String datetime) {
		this.datetime = datetime;
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
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public ArrayList<String> getTags() {
		return tags;
	}
	public void setTags(ArrayList<String> tags) {
		this.tags = tags;
	}
	public ArrayList<String> getFilesPath() {
		return filesPath;
	}
	public void setFilesPath(ArrayList<String> filesPath) {
		this.filesPath = filesPath;
	}
	
	
}
