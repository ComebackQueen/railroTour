package net.note.db;

import java.sql.SQLException;

import etc.function.DB_Connection;

public class Note_Add_DAO extends DB_Connection{

	public void Note_Add (String Email_ID, int Note_ID) {
		String sql="select * from note_info1 where travel_id=?";
		Note_Add_Bean Info=new Note_Add_Bean();
		
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, Note_ID);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				Info.setNote_Name(rs.getString("note_name"));
				Info.setTravel_Start_Day(rs.getString("travel_start_day"));
				Info.setTravel_Day(rs.getInt("travel_day"));
				Info.setTravel_Tema(rs.getString("travel_tema"));
				Info.setTravel_People(rs.getInt("travel_people"));
				Info.setImg(rs.getString("img"));
			}
			
			sql="insert into note_info1(email_id, note_name, travel_start_day, travel_day, travel_tema, travel_people, note_view, img) values(?, ?, ?, ?, ?, ?, 0, ?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, Email_ID);
			pstmt.setString(2, Info.getNote_Name());
			pstmt.setString(3, Info.getTravel_Start_Day());
			pstmt.setInt(4, Info.getTravel_Day());
			pstmt.setString(5, Info.getTravel_Tema());
			pstmt.setInt(6, Info.getTravel_People());
			pstmt.setString(7, Info.getImg());
			
			int result=0;
			result=pstmt.executeUpdate();
			
			sql="select travel_id from note_info1 where email_id=? and note_name=? and travel_start_day=? and travel_day=? and travel_tema=? and travel_people=? and img=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, Email_ID);
			pstmt.setString(2, Info.getNote_Name());
			pstmt.setString(3, Info.getTravel_Start_Day());
			pstmt.setInt(4, Info.getTravel_Day());
			pstmt.setString(5, Info.getTravel_Tema());
			pstmt.setInt(6, Info.getTravel_People());
			pstmt.setString(7, Info.getImg());
			
			rs=pstmt.executeQuery();
			int mynote_id=0;
			
			while(rs.next()) {
				mynote_id=rs.getInt("travel_id");
			}
			System.out.println("복사한 일정의 노트 아이디 : "+mynote_id);
			
			sql="insert into note_travel_area select ?, travel_area_name, travel_area_day from note_travel_area where travel_id=?";
			
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, mynote_id);
			pstmt.setInt(2, Note_ID);
			result=pstmt.executeUpdate();
			
			System.out.println("복사한 일정의 지역 개수 : "+result);
			
			sql="insert into note_info2 select ?, kinds_1, route_name, kinds_2, memo, content_id, content_type_id, sigungu_code, area_code, area_name, weeks, days, dates, orders, day_orders from note_info2 where travel_id=?";
			
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, mynote_id);
			pstmt.setInt(2, Note_ID);
			result=pstmt.executeUpdate();
			
			System.out.println("복사한 일정의 장소 개수 : "+result);
		}catch(Exception ex) {
			System.out.println("Note_Add ERROR : "+ex);
		}finally {
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
	        if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
		}
	}
	
}
