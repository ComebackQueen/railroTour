package net.member.db;

import java.sql.SQLException;
import java.util.ArrayList;

import etc.function.DB_Connection;
import net.note.db.Note_Plans_List_Bean;

public class MainDAO extends DB_Connection{
	public ArrayList<String> getPopular_keyword(){
		ArrayList<String> keyword=new ArrayList<String>();
		try {
			String sql="select * from popular_searches order by cnt desc limit 10";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				keyword.add(rs.getString("keyword"));
			}
		}catch(Exception ex) {
			System.out.println("getPopular_keyword error : "+ex);
		}finally {
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
	        if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
		}
		return keyword;
	}
	public ArrayList<Note_Plans_List_Bean> getRealTime_Note(){
		ArrayList<Note_Plans_List_Bean> Railro_Note=new ArrayList<Note_Plans_List_Bean>();
		try {
			String sql="select * from note_info1 order by travel_start_day desc limit 2";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				Railro_Note.add(new Note_Plans_List_Bean());
				Railro_Note.get(Railro_Note.size()-1).setNote_ID(rs.getInt("travel_id")); //노트 고유 번호 
				Railro_Note.get(Railro_Note.size()-1).setTravel_Day(rs.getString("travel_start_day")); //출발일
				Railro_Note.get(Railro_Note.size()-1).setNote_Name(rs.getString("note_name")); //노트명
				Railro_Note.get(Railro_Note.size()-1).setTema_Name(rs.getString("travel_tema")); //테마명
				Railro_Note.get(Railro_Note.size()-1).setView(rs.getInt("note_view")); //조회수
				Railro_Note.get(Railro_Note.size()-1).setDay(rs.getInt("travel_day")); //일수
				Railro_Note.get(Railro_Note.size()-1).setImg(rs.getString("img")); //이미지
				Railro_Note.get(Railro_Note.size()-1).setEmail_id(rs.getString("email_id"));
			}
			for(int i=0; i<Railro_Note.size(); i++) {
				sql="select * from member where email_id=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, Railro_Note.get(i).getEmail_id());
				rs=pstmt.executeQuery();
				
				while(rs.next()) {
					Railro_Note.get(i).setName(rs.getString("nikname"));
					Railro_Note.get(i).setProfileimg(rs.getString("imgfile"));
				}
			}
		}catch(Exception ex) {
			System.out.println("getRealTime_Note error : "+ex);
		}finally {
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
	        if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
		}
		return Railro_Note;
	}
	public ArrayList<Note_Plans_List_Bean> getPopular_Note(){
		ArrayList<Note_Plans_List_Bean> Railro_Note=new ArrayList<Note_Plans_List_Bean>();
		try {
			String sql="SELECT COUNT(*) AS cnt, travel_id FROM note_like_cnt GROUP BY travel_id ORDER BY COUNT(*) desc limit 3";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();

			while(rs.next()) {
				Railro_Note.add(new Note_Plans_List_Bean());
				Railro_Note.get(Railro_Note.size()-1).setNote_ID(rs.getInt("travel_id"));
			}
			
			for(int i=0; i<Railro_Note.size(); i++) {
				sql="select * from note_info1 where travel_id=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, Railro_Note.get(i).getNote_ID());
				rs=pstmt.executeQuery();
				
				while(rs.next()) {
					Railro_Note.get(i).setNote_ID(rs.getInt("travel_id")); //노트 고유 번호 
					Railro_Note.get(i).setTravel_Day(rs.getString("travel_start_day")); //출발일
					Railro_Note.get(i).setNote_Name(rs.getString("note_name")); //노트명
					Railro_Note.get(i).setTema_Name(rs.getString("travel_tema")); //테마명
					Railro_Note.get(i).setView(rs.getInt("note_view")); //조회수
					Railro_Note.get(i).setDay(rs.getInt("travel_day")); //일수
					Railro_Note.get(i).setImg(rs.getString("img")); //이미지
					Railro_Note.get(i).setEmail_id(rs.getString("email_id"));
				}
				
				sql="select * from member where email_id=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, Railro_Note.get(i).getEmail_id());
				rs=pstmt.executeQuery();
				
				while(rs.next()) {
					Railro_Note.get(i).setName(rs.getString("nikname"));
					Railro_Note.get(i).setProfileimg(rs.getString("imgfile"));
				}
				
				
			}

			for (int i = 0; i < Railro_Note.size(); i++) {

				sql = "select * from note_travel_area where travel_id=? order by travel_area_day asc";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, Railro_Note.get(i).getNote_ID());
				rs = pstmt.executeQuery();

				int cnt = 0;
				while (rs.next()) {
					if (cnt == 0) {
						Railro_Note.get(i).setArea(rs.getString("travel_area_name"));
					} else {
						Railro_Note.get(i).PlusArea("-" + rs.getString("travel_area_name"));
					}
					cnt++;
				}
			}

			for (int i = 0; i < Railro_Note.size(); i++) {
				sql = "select count(*) AS total from note_like_cnt where travel_id=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, Railro_Note.get(i).getNote_ID());
				rs = pstmt.executeQuery();

				while (rs.next()) {
					Railro_Note.get(i).setLike(rs.getInt("total"));
				}
			}
		}catch(Exception ex) {
			System.out.println("getPopular_Note error : "+ex);
		}finally {
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
	        if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
		}
		return Railro_Note;
	}
}
