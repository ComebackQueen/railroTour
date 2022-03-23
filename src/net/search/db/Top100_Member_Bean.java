package net.search.db;

public class Top100_Member_Bean {
	int follow_cnt, note_cnt;
	String img, nikname, email_id;
	
	public String getEmail_id() {
		return email_id;
	}
	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}
	public int getFollow_cnt() {
		return follow_cnt;
	}
	public void setFollow_cnt(int follow_cnt) {
		this.follow_cnt = follow_cnt;
	}
	public int getNote_cnt() {
		return note_cnt;
	}
	public void setNote_cnt(int note_cnt) {
		this.note_cnt = note_cnt;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getNikname() {
		return nikname;
	}
	public void setNikname(String nikname) {
		this.nikname = nikname;
	}
	
}
