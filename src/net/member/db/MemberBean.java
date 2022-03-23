package net.member.db;


//ȸ������ ������ �����
public class MemberBean {
    private String MEMBER_ID;
    private String MEMBER_PW;
    private String MEMBER_NAME;
    private String MEMBER_NIKNAME;
    private String PHONE_NUMBER;
    private String MEMBER_GENDER; 
    private String IMG_NAME;
    private int STAMP_CNT;
    int Note_Count, Following_YN;


	public int getNote_Count() {
		return Note_Count;
	}
	public void setNote_Count(int note_Count) {
		Note_Count = note_Count;
	}
	public int getFollowing_YN() {
		return Following_YN;
	}
	public void setFollowing_YN(int following_YN) {
		Following_YN = following_YN;
	}
	public String getMEMBER_ID() {
		return MEMBER_ID;
	}
	public void setMEMBER_ID(String mEMBER_ID) {
		MEMBER_ID = mEMBER_ID;
	}
	public String getMEMBER_PW() {
		return MEMBER_PW;
	}
	public void setMEMBER_PW(String mEMBER_PW) {
		MEMBER_PW = mEMBER_PW;
	}
	public String getMEMBER_NAME() {
		return MEMBER_NAME;
	}
	public void setMEMBER_NAME(String mEMBER_NAME) {
		MEMBER_NAME = mEMBER_NAME;
	}
	public String getMEMBER_NIKNAME() {
		return MEMBER_NIKNAME;
	}
	public void setMEMBER_NIKNAME(String mEMBER_NIKNAME) {
		MEMBER_NIKNAME = mEMBER_NIKNAME;
	}
	public String getPHONE_NUMBER() {
		return PHONE_NUMBER;
	}
	public void setPHONE_NUMBER(String pHONE_NUMBER) {
		PHONE_NUMBER = pHONE_NUMBER;
	}
	public String getMEMBER_GENDER() {
		return MEMBER_GENDER;
	}
	public void setMEMBER_GENDER(String mEMBER_GENDER) {
		MEMBER_GENDER = mEMBER_GENDER;
	}
	public String getIMG_NAME() {
		return IMG_NAME;
	}
	public void setIMG_NAME(String iMG_NAME) {
		IMG_NAME = iMG_NAME;
	}
	public int getSTAMP_CNT() {
		return STAMP_CNT;
	}
	public void setSTAMP_CNT(int sTAMP_CNT) {
		STAMP_CNT = sTAMP_CNT;
	}
}
