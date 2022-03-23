package net.Ajax.Note.db;

import java.sql.SQLException;

import etc.function.DB_Connection;

public class Note_Like_DAO extends DB_Connection{
	
	public void Note_Like_UP(int NoteID, String email_id) {//좋아요
		String sql="insert into note_like_cnt values(?, ?)";
		int result=0;
		
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, email_id);
			pstmt.setInt(2, NoteID);
			result=pstmt.executeUpdate();

		}catch(Exception ex) {
			System.out.println("Note_Like_UP ERROR : "+ex);
		}finally {
				if(rs!=null) try{rs.close();}catch(SQLException ex){}
	           if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
		}
	}
	
	public void Note_Like_DOWN(int NoteID, String email_id) {//좋아요 취소
		int result=0;
		try {
			String sql="delete from note_like_cnt where travel_id=? and email_id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, NoteID);
			pstmt.setString(2, email_id);

			result=pstmt.executeUpdate();
			
		} catch (Exception ex) {
			System.out.println("Note_Like_DOWN ERROR2 : " + ex);
		} finally {
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
	           if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
		}
	}
}
