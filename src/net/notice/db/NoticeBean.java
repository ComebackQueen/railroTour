package net.notice.db;

import java.sql.Date;

public class NoticeBean {
	private int num;
	private String emailid;
	private String title;
	private String content;
	private int readcnt;
	private Date dates;
	private String fileName_1;
	private String fileName_2;
	private String fileName_3;
	private String fileName_4;
	private String fileName_5;
	private String filePath;
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getEmailid() {
		return emailid;
	}
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getReadcnt() {
		return readcnt;
	}
	public void setReadcnt(int readcnt) {
		this.readcnt = readcnt;
	}
	public Date getDates() {
		return dates;
	}
	public void setDates(Date dates) {
		this.dates = dates;
	}
	public String getFileName_1() {
		return fileName_1;
	}
	public void setFileName_1(String fileName_1) {
		this.fileName_1 = fileName_1;
	}
	public String getFileName_2() {
		return fileName_2;
	}
	public void setFileName_2(String fileName_2) {
		this.fileName_2 = fileName_2;
	}
	public String getFileName_3() {
		return fileName_3;
	}
	public void setFileName_3(String fileName_3) {
		this.fileName_3 = fileName_3;
	}
	public String getFileName_4() {
		return fileName_4;
	}
	public void setFileName_4(String fileName_4) {
		this.fileName_4 = fileName_4;
	}
	public String getFileName_5() {
		return fileName_5;
	}
	public void setFileName_5(String fileName_5) {
		this.fileName_5 = fileName_5;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	
}
