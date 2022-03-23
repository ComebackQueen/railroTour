package net.Ajax.Note.db;

import java.sql.SQLException;

import etc.function.DB_Connection;

public class Note_Memo_DAO extends DB_Connection{
	public void Memo_Update(int Note_ID, int orders, int day_orders, String memo) {
		String sql="update note_info2 set memo=? where travel_id=? and orders=? and day_orders=?";
		int result=0;
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, memo);
			pstmt.setInt(2, Note_ID);
			pstmt.setInt(3, orders);
			pstmt.setInt(4, day_orders);
			result=pstmt.executeUpdate();
			
			System.out.println("메모 변경 개수 : "+result);
		
		}catch(Exception ex) {
			System.out.println("Memo_Update ERROR : "+ex);
		}finally {
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
	        if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
		}
	}
}
