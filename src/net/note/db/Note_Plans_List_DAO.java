package net.note.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import etc.function.DB_Connection;

public class Note_Plans_List_DAO extends DB_Connection{

	
	public ArrayList<Note_Plans_List_Bean> Select_Note_List(String id){ //내일로 노트 목록 보기
		ArrayList<Note_Plans_List_Bean> TravelDay_List=new ArrayList<Note_Plans_List_Bean>();
		
		String sql="select * from note_info1 where rownum<10 and email_id LIKE ? order by travel_start_day desc";

		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);

			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				int size=TravelDay_List.size();
				
				TravelDay_List.add(new Note_Plans_List_Bean());
				TravelDay_List.get(size).setNote_ID(rs.getInt("travel_id")); //노트 고유 번호 
				TravelDay_List.get(size).setTravel_Day(rs.getString("travel_start_day")); //출발일
				TravelDay_List.get(size).setNote_Name(rs.getString("note_name")); //노트명
				TravelDay_List.get(size).setTema_Name(rs.getString("travel_tema")); //테마명
				TravelDay_List.get(size).setView(rs.getInt("note_view")); //조회수
				TravelDay_List.get(size).setDay(rs.getInt("travel_day")); //일수
				TravelDay_List.get(size).setImg(rs.getString("img")); //이미지
			}
			
			
			sql="select * from member where email_id LIKE ?";
			
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			
			String nikname = null;
			String profile=null;
			while(rs.next()) {
				nikname=rs.getString("nikname");
				profile=rs.getString("imgfile");
			}
			
			for(int i=0; i<TravelDay_List.size(); i++) {
				TravelDay_List.get(i).setName(nikname);
				TravelDay_List.get(i).setProfileimg(profile);
				sql="select * from note_travel_area where travel_id=? order by travel_area_day asc";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, TravelDay_List.get(i).getNote_ID());
				rs=pstmt.executeQuery();
				
				int cnt=0; 
				while(rs.next()) {
					if(cnt==0) {
						TravelDay_List.get(i).setArea(rs.getString("travel_area_name"));
					}
					else {
						TravelDay_List.get(i).PlusArea("-"+rs.getString("travel_area_name"));
					}
					cnt++;
				}
			}
			
			for(int i=0; i<TravelDay_List.size(); i++) {
				sql="select count(*) AS total from note_like_cnt where travel_id=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, TravelDay_List.get(i).getNote_ID());
				rs=pstmt.executeQuery();
				
				while(rs.next()) {
					TravelDay_List.get(i).setLike(rs.getInt("total"));
				}
			}

		}catch(Exception ex) {
			System.out.println("Select_Note_List 에러 : "+ex);
		}finally {
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
	        if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
		}
		
		return TravelDay_List;
	}
	
	public void remove_noteinfo1(int NoteID) {
		int result=0;
		try {
			String sql="delete from note_info1 where travel_id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, NoteID);

			result=pstmt.executeUpdate();
			
		} catch (Exception ex) {
			System.out.println("remove_noteinfo1 : " + ex);
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
		}
	}
	public void remove_noteinfo2(int NoteID) {
		int result=0;
		try {
			String sql="delete from note_info2 where travel_id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, NoteID);

			result=pstmt.executeUpdate();
			
		} catch (Exception ex) {
			System.out.println("remove_noteinfo2 : " + ex);
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
		}
	}
	public void remove_notelikecnt(int NoteID) {
		int result=0;
		try {
			String sql="delete from note_like_cnt where travel_id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, NoteID);

			result=pstmt.executeUpdate();
			
		} catch (Exception ex) {
			System.out.println("remove_notelikecnt : " + ex);
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
		}
	}
	public void remove_note_travel_area(int NoteID) {
		int result=0;
		try {
			String sql="delete from note_travel_area where travel_id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, NoteID);

			result=pstmt.executeUpdate();
			
		} catch (Exception ex) {
			System.out.println("remove_note_travel_area : " + ex);
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
		}
	}
	
	public JSONObject Note_Add_List(int note_cnt, String id) {
		ArrayList<Note_Plans_List_Bean> TravelDay_List=new ArrayList<Note_Plans_List_Bean>();
		JSONObject result=new JSONObject();
		
		String sql="select * from note_info1 where rownum>"+note_cnt+" and rownum<"+(10+note_cnt)+" and email_id LIKE ? order by travel_start_day desc";

		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);

			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				int size=TravelDay_List.size();
				
				TravelDay_List.add(new Note_Plans_List_Bean());
				TravelDay_List.get(size).setNote_ID(rs.getInt("travel_id")); //노트 고유 번호 
				TravelDay_List.get(size).setTravel_Day(rs.getString("travel_start_day")); //출발일
				TravelDay_List.get(size).setNote_Name(rs.getString("note_name")); //노트명
				TravelDay_List.get(size).setTema_Name(rs.getString("travel_tema")); //테마명
				TravelDay_List.get(size).setView(rs.getInt("note_view")); //조회수
				TravelDay_List.get(size).setDay(rs.getInt("travel_day")); //일수
				TravelDay_List.get(size).setImg(rs.getString("img")); //이미지
				
				System.out.println(rs.getInt("travel_id"));
			}
			

			sql="select * from member where email_id LIKE ?";
			
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			
			String nikname = null;
			
			while(rs.next()) { //닉네임 가져오기
				nikname=rs.getString("nikname");
			}
			
			for(int i=0; i<TravelDay_List.size(); i++) { //일정별 지역 가져오기
				TravelDay_List.get(i).setName(nikname);
				
				sql="select * from note_travel_area where travel_id=? order by travel_area_day asc";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, TravelDay_List.get(i).getNote_ID());
				rs=pstmt.executeQuery();
				
				int cnt=0; 
				while(rs.next()) {
					if(cnt==0) {
						TravelDay_List.get(i).setArea(rs.getString("travel_area_name"));
					}
					else {
						TravelDay_List.get(i).PlusArea("-"+rs.getString("travel_area_name"));
					}
					cnt++;
				}
			}
			
			for(int i=0; i<TravelDay_List.size(); i++) { //좋아요 수 가져오기
				sql="select count(*) AS total from note_like_cnt where travel_id=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, TravelDay_List.get(i).getNote_ID());
				rs=pstmt.executeQuery();
				
				while(rs.next()) {
					TravelDay_List.get(i).setLike(rs.getInt("total"));
				}
			}
			
			JSONObject obj=new JSONObject();
			JSONArray jsonarray=new JSONArray();
			
			for(int i=0; i<TravelDay_List.size(); i++) { //최종으로 JSON Array로 묶는 작업
				obj=new JSONObject();
				obj.put("Note_ID", TravelDay_List.get(i).getNote_ID()); //노트 번호
				obj.put("Date", TravelDay_List.get(i).getTravel_Day()); //여행 날짜
				obj.put("Note_Name", TravelDay_List.get(i).getNote_Name()); //노트 명
				obj.put("Tema", TravelDay_List.get(i).getTema_Name()); // 테마명
				obj.put("View", TravelDay_List.get(i).getView()); //조회수
				obj.put("Like", TravelDay_List.get(i).getLike()); //좋아요 수
				obj.put("Area", TravelDay_List.get(i).getArea()); //지역
				obj.put("Name", TravelDay_List.get(i).getName()); //작성자 명
				obj.put("Img", TravelDay_List.get(i).getImg()); //썸네일 이미지
				obj.put("day", TravelDay_List.get(i).getDay()); //일 수
				
				jsonarray.add(obj);
			}
			
			result.put("items", jsonarray);
			
			sql="select COUNT(*) AS cnt from note_info1 where rownum>"+(10+note_cnt-1)+" and email_id LIKE ? order by travel_start_day desc";
			
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			
			int cnt=0;
			
			while(rs.next()) {
				cnt=rs.getInt("cnt");
			}
			System.out.println("현재 노트 개수 : "+note_cnt+" 다음 노트 잔여 개수 cnt : "+cnt);
			
			result.put("remaining", cnt);
		}catch(Exception ex) {
			System.out.println("Note_Add_List 에러 : "+ex);
		}finally {
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
	        if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
		}
		
		return result;
	}
}
