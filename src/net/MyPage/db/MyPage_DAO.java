package net.MyPage.db;

import java.sql.SQLException;

import etc.function.DB_Connection;

public class MyPage_DAO extends DB_Connection{
	
	public void setProFileImg(String url, String email_id) {
		String sql="update member set imgfile=? where email_id=?";
		int result=0;
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, url);
			pstmt.setString(2, email_id);
			result=pstmt.executeUpdate();
			
		}catch(Exception ex) {
			System.out.println("setProFileImg ERROR : "+ex);
		}finally {
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
	        if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
		}
	}
	
	public MemberINFO_Bean getMemberInfo(String email_id) {
		MemberINFO_Bean member=new MemberINFO_Bean();
		String sql="select * from member where email_id=?";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, email_id);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				member.setEmail_id(rs.getString("email_id"));
				member.setName(rs.getString("name"));
				member.setNikname(rs.getString("nikname"));
				member.setPhone(rs.getString("phone"));
				member.setProfile(rs.getString("imgfile"));
				member.setSex(rs.getString("sex"));
			}

		}catch(Exception ex) {
			System.out.println("getMemberInfo ERROR : "+ex);
		}finally {
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
	        if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
		}
		return member;
	}
	public void changePassword(String pw, String email_id) {
		String sql="update member set password=? where email_id=?";
		int result=0;
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, pw);
			pstmt.setString(2, email_id);
			result=pstmt.executeUpdate();
			
		}catch(Exception ex) {
			System.out.println("changePassword ERROR : "+ex);
		}finally {
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
	        if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
		}
	} 
	
	public int changePassword(String id, String oldPw, String newPw){
		String dbpw = ""; //db?????? ??????????????? ????????? ??????
		int x = -1;
		
		//System.out.println(pw);
		try{
			// ???????????? ??????
			StringBuffer query1 = new StringBuffer();
			query1.append("select password from member where email_id=?");
			
			StringBuffer query2 = new StringBuffer();
			query2.append("update member set password=? where email_id=?");
			
			pstmt = con.prepareStatement(query1.toString());
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				dbpw = rs.getString("password"); // ??? ??????????????? DB?????? ??????
				System.out.println(dbpw);
				if(dbpw.equals(oldPw)){
					// ???????????? ???????????? ??????
					pstmt = con.prepareStatement(query2.toString());
					pstmt.setString(1, newPw);
					pstmt.setString(2, id);
					pstmt.executeUpdate();
					con.commit();
					x = 1; // ?????? ??????
				} else {
					x = 0; // ???????????? ???????????? ==> ??????
				}
			}
			
		} catch(Exception ex) {
			System.out.println("deleteMember ERROR : "+ex);
		}finally {
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
	        if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
		}
		return x;
	}
	
	public int deleteMember(String id, String pw){
		String dbpw = ""; //db?????? ??????????????? ????????? ??????
		int x = -1;
		
		//System.out.println(pw);
		try{
			// ???????????? ??????
			StringBuffer query1 = new StringBuffer();
			query1.append("select password from member where email_id=?");
			
			StringBuffer query2 = new StringBuffer();
			query2.append("delete from member where email_id=?");
			
			pstmt = con.prepareStatement(query1.toString());
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				dbpw = rs.getString("password"); // ????????? ??????????????? DB?????? ??????
				System.out.println(dbpw);
				if(dbpw.equals(pw)){
					// ???????????? ???????????? ??????
					pstmt = con.prepareStatement(query2.toString());
					pstmt.setString(1, id);
					pstmt.executeUpdate();
					con.commit();
					x = 1; // ?????? ??????
				} else {
					x = 0; // ???????????? ???????????? ==> ??????
				}
			}
			
		} catch(Exception ex) {
			System.out.println("deleteMember ERROR : "+ex);
		}finally {
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
	        if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
		}
		return x;
	}
}
