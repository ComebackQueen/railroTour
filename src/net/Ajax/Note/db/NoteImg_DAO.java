package net.Ajax.Note.db;

import java.sql.SQLException;

import etc.function.DB_Connection;

public class NoteImg_DAO extends DB_Connection{
	
	public void img_change(int note_id, String path) {
		String sql="update note_info1 set img=? where travel_id=?";
		
		int result=0;
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, path);
			pstmt.setInt(2, note_id);
			result=pstmt.executeUpdate();
			
			System.out.println("노트1 정보 이미지 변경 개수 (1개여야함) : "+result);
		
		}catch(Exception ex) {
			System.out.println("img_change ERROR : "+ex);
		}finally {
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
	        if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
		}
	}
}
