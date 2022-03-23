package net.note.db;

import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import etc.function.DB_Connection;

public class Note_Detail_DAO extends DB_Connection{

	public void NoteViewIncrease(int Note_ID) { //노트 조회수 증가
		String sql="update note_info1 set note_view=note_view+1 where travel_id=?";
		int result=-1;
		
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, Note_ID);
			result=pstmt.executeUpdate();
			
		}catch(Exception ex) {
			System.out.println("NoteViewIncrese ERROR : "+ex);
		}finally {
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
	        if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
		}
	}
	public String Note_getID(int Note_ID) { //해당 노트의 계정아이디 가져오기
		String sql="select * from note_info1 where travel_id=?";
		String Email_ID=null;
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, Note_ID);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				Email_ID=rs.getString("email_id");
			}
		}catch(Exception ex) {
			System.out.println("Note_getID ERROR : "+ex);
		}finally {
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
	        if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
		}
		return Email_ID;
	}
	public String Note_getNikname(String Email_ID) { //닉네임 가져오기
		String sql="select * from member where email_id=?";
		String Nikname=null;
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, Email_ID);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				Nikname=rs.getString("nikname");
			}
		}catch(Exception ex) {
			System.out.println("Note_getNikname ERROR : "+ex);
		}finally {
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
	        if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
		}
		return Nikname;
	}
	public String ProFile_getImg(String Email_ID) { //프로필 이미지 경로
		String sql="select * from member where email_id=?";
		String Img=null;
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, Email_ID);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				Img=rs.getString("imgfile");
			}
		}catch(Exception ex) {
			System.out.println("ProFile_getImg ERROR : "+ex);
		}finally {
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
	        if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
		}
		return Img;
	}
	public String BackGround_getImg(int Note_ID) { //노트 커버사진 가져오기
		String sql="select * from note_info1 where travel_id=?";
		String Img=null;
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, Note_ID);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				Img=rs.getString("img");
			}
		}catch(Exception ex) {
			System.out.println("BackGround_getImg ERROR : "+ex);
		}finally {
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
	        if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
		}
		return Img;
	}
	public int Follow_getYN(String Email_id, String Follow_id) {//팔로워 유무 확인
		String sql="select COUNT(*) AS cnt from member_follow where email_id=? and follow_id=?";
		int cnt=-1;
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, Email_id);
			pstmt.setString(2, Follow_id);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				cnt=rs.getInt("cnt");
			}
		}catch(Exception ex) {
			System.out.println("Follow_getYN ERROR : "+ex);
		}finally {
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
	        if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
		}
		return cnt;
	}
	public int Like_getYN(String Email_ID, int Note_ID) { //좋아요 유무 확인
		String sql="select COUNT(*) AS cnt from note_like_cnt where email_id=? and travel_id=?";
		int cnt=-1;
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, Email_ID);
			pstmt.setInt(2, Note_ID);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				cnt=rs.getInt("cnt");
			}
		}catch(Exception ex) {
			System.out.println("Like_getYN ERROR : "+ex);
		}finally {
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
	        if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
		}
		return cnt;
	}
	public String Note_getDate(int Note_ID) {//날짜 가져오기
		String sql="select * from note_info1 where travel_id=?";
		String date=null;
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, Note_ID);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				date=rs.getString("travel_start_day");
			}
		}catch(Exception ex) {
			System.out.println("Note_getDate ERROR : "+ex);
		}finally {
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
	        if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
		}
		return date;
	}
	public String Note_getTema(int Note_ID) {//테마 가져오기
		String sql="select * from note_info1 where travel_id=?";
		String tema=null;
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, Note_ID);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				tema=rs.getString("travel_tema");
			}
		}catch(Exception ex) {
			System.out.println("Note_getTema ERROR : "+ex);
		}finally {
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
	        if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
		}
		return tema;
	}
	public int Note_LikeCnt(int Note_ID) {//좋아요 수 가져오기
		String sql="select COUNT(*) AS cnt from note_like_cnt where travel_id=?";
		int like_cnt=-1;
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, Note_ID);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				like_cnt=rs.getInt("cnt");
			}
		}catch(Exception ex) {
			System.out.println("Note_LikeCnt ERROR : "+ex);
		}finally {
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
	        if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
		}
		return like_cnt;
	}
	public int Note_View(int Note_ID) { //조회수 가져오기
		String sql="select * from note_info1 where travel_id=?";
		int Views=-1;
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, Note_ID);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				Views=rs.getInt("note_view");
			}
		}catch(Exception ex) {
			System.out.println("Note_View ERROR : "+ex);
		}finally {
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
	        if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
		}
		return Views;
	}
	public int Note_TravelDay(int Note_ID) {//여행 일 수 가져오기
		String sql="select * from note_info1 where travel_id=?";
		int travel_day=-1;
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, Note_ID);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				travel_day=rs.getInt("travel_day");
			}
		}catch(Exception ex) {
			System.out.println("Note_TravelDay ERROR : "+ex);
		}finally {
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
	        if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
		}
		return travel_day;
	}
	public String Note_getName(int Note_ID) {//노트 명 가져오기
		String sql="select * from note_info1 where travel_id=?";
		String name=null;
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, Note_ID);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				name=rs.getString("note_name");
			}
		}catch(Exception ex) {
			System.out.println("Note_getName ERROR : "+ex);
		}finally {
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
	        if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
		}
		return name;
	}
	public int Note_getDays(int Note_ID) {//여행 일 수 가져오기
		String sql="select * from note_info1 where travel_id=?";
		int days=0;
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, Note_ID);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				days=rs.getInt("travel_day");
			}
		}catch(Exception ex) {
			System.out.println("Note_getDays ERROR : "+ex);
		}finally {
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
	        if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
		}
		return days;
	}
	public ArrayList<Note_Detail_Info_Bean> Note_getDetailInfo(int Note_ID){
		ArrayList<Note_Detail_Info_Bean> note_info=new ArrayList<Note_Detail_Info_Bean>();
		
		String sql="select * from note_info2 where travel_id=? order by day_orders asc, orders asc";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, Note_ID);
			rs=pstmt.executeQuery();
			
			int size;
			while(rs.next()) {
				size=note_info.size();
				
				note_info.add(new Note_Detail_Info_Bean());
				
				note_info.get(size).setKinds_1(rs.getString("kinds_1")); //분류1 일반/해시
				note_info.get(size).setKinds_2(rs.getString("kinds_2")); //분류2 이동/관광지/해시
				note_info.get(size).setRoute_name(rs.getString("route_name"));//장소명
				note_info.get(size).setMemo(rs.getString("memo")); //메모
				note_info.get(size).setContent_ID(rs.getInt("content_id"));//콘텐츠 아이디
				note_info.get(size).setContent_Type_ID(rs.getInt("content_type_id"));//콘텐츠 타입 아이디
				note_info.get(size).setSigungu_code(rs.getInt("sigungu_code"));//시군구 코드
				note_info.get(size).setArea_code(rs.getInt("area_code"));//지역코드
				note_info.get(size).setArea_name(rs.getString("area_name"));//지역명
				note_info.get(size).setDays(rs.getString("days")); //데이 Day1 day2
				note_info.get(size).setDate(rs.getString("dates")); //날짜
				note_info.get(size).setOrders(rs.getInt("orders")); //일정 순서
				note_info.get(size).setDay_orders(rs.getInt("day_orders")); //지역 순서
			}
			
			URL url;
			InputStreamReader isr;
			JSONObject items; 
			
			for(int i=0; i<note_info.size(); i++) { //관광지/음식점 좋아요 개수 가져오기
				if(note_info.get(i).getKinds_1().compareTo("일반")==0) {
					sql="select sum(like_yn) as result from review where content_id=? and content_type_id=?"; //좋아요 개수 합산
					pstmt=con.prepareStatement(sql);
					pstmt.setInt(1, note_info.get(i).getContent_ID());
					pstmt.setInt(2, note_info.get(i).getContent_Type_ID());
					rs=pstmt.executeQuery();
					
					while(rs.next()) {
						note_info.get(i).setLike_cnt(rs.getInt("result"));
					}
					url=new URL("http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailCommon?ServiceKey="+Key+"&contentId="+note_info.get(i).getContent_ID()+"&defaultYN=N&MobileOS=ETC&MobileApp=AppTest&_type=json&firstImageYN=Y");
					isr = new InputStreamReader(url.openConnection().getInputStream(),"UTF-8");
					items=(JSONObject) JSONValue.parseWithException(isr);
					items=(JSONObject) items.get("response");
					items=(JSONObject) items.get("body");
					items=(JSONObject) items.get("items");
					items=(JSONObject) items.get("item");
					
					if(items.get("firstimage")!=null) {
						note_info.get(i).setImg(items.get("firstimage").toString());
					}
					else if(items.get("firstimage2")!=null) {
						note_info.get(i).setImg(items.get("firstimage2").toString());
					}
					else {
						note_info.get(i).setImg("./jpg/no_image.gif");
					}
				}
				else if(note_info.get(i).getKinds_1().compareTo("해시")==0) {
					// Tour API 
					// 37 = 전북, 38 = 전남, 5 = 광주
					// 기차 API
					// 24 = 광주, 35 = 전북, 36 = 전남
					
					if (note_info.get(i).getKinds_2().compareTo("이동") == 0) {// 기차 이동시
						int sigungu_code = 0;
						switch (note_info.get(i).getSigungu_code()) {
						case 5:
							sigungu_code = 24;
							break; // 광주
						case 37:
							sigungu_code = 35;
							break; // 전북
						case 38:
							sigungu_code = 36;
							break; // 전남
						}
						url = new URL(
								"http://openapi.tago.go.kr/openapi/service/TrainInfoService/getCtyAcctoTrainSttnList?serviceKey="
										+ Train_Key + "&cityCode=" + sigungu_code + "&numOfRows=1&_type=json");
						isr = new InputStreamReader(url.openConnection().getInputStream(), "UTF-8");
						items = (JSONObject) JSONValue.parseWithException(isr);
						items = (JSONObject) items.get("response");
						items = (JSONObject) items.get("body");
						System.out.println("출발역 기차 정보 : " + items);
						int count = Integer.parseInt(items.get("totalCount").toString());

						for (int o = 1; o <= count; o++) { // 출발역 셋팅
							url = new URL(
									"http://openapi.tago.go.kr/openapi/service/TrainInfoService/getCtyAcctoTrainSttnList?serviceKey="
											+ Train_Key + "&cityCode=" + sigungu_code
											+ "&numOfRows=1&_type=json&pageNo=" + o);
							isr = new InputStreamReader(url.openConnection().getInputStream(), "UTF-8");
							items = (JSONObject) JSONValue.parseWithException(isr);
							items = (JSONObject) items.get("response");
							items = (JSONObject) items.get("body");
							items = (JSONObject) items.get("items");
							items = (JSONObject) items.get("item");

							note_info.get(i).add_Start_Station(items.get("nodename").toString(),
									items.get("nodeid").toString());
						}

						// 도착역 셋팅
						url = new URL(
								"http://openapi.tago.go.kr/openapi/service/TrainInfoService/getCtyAcctoTrainSttnList?serviceKey="
										+ Train_Key + "&cityCode=" + sigungu_code + "&numOfRows=1&_type=json");
						isr = new InputStreamReader(url.openConnection().getInputStream(), "UTF-8");
						items = (JSONObject) JSONValue.parseWithException(isr);
						items = (JSONObject) items.get("response");
						items = (JSONObject) items.get("body");
						System.out.println("도착역 기차 정보 : " + items);

						count = Integer.parseInt(items.get("totalCount").toString());
						for (int o = 1; o <= count; o++) { // 도착역 셋팅
							url = new URL(
									"http://openapi.tago.go.kr/openapi/service/TrainInfoService/getCtyAcctoTrainSttnList?serviceKey="
											+ Train_Key + "&cityCode=" + sigungu_code
											+ "&numOfRows=1&_type=json&pageNo=" + o);
							isr = new InputStreamReader(url.openConnection().getInputStream(), "UTF-8");
							items = (JSONObject) JSONValue.parseWithException(isr);
							items = (JSONObject) items.get("response");
							items = (JSONObject) items.get("body");
							items = (JSONObject) items.get("items");
							items = (JSONObject) items.get("item");

							note_info.get(i).add_End_Station(items.get("nodename").toString(),
									items.get("nodeid").toString());
						}
					}
				}
			}
		}catch(Exception ex) {
			System.out.println("Note_getDetailInfo ERROR : "+ex);
		}finally {
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
	        if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
		}
		return note_info;
	}
	
	public ArrayList<Note_Travel_Area_Bean> Note_getArea(int NoteID){
		String sql="select * from note_travel_area where travel_id=? order by travel_area_day asc";
		ArrayList<Note_Travel_Area_Bean> list=new ArrayList<Note_Travel_Area_Bean>();
		
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, NoteID);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				list.add(new Note_Travel_Area_Bean(rs.getString("travel_area_name"), rs.getInt("travel_area_day")));
			}
			
		}catch(Exception ex) {
			System.out.println("Note_getArea ERROR : "+ex);
		}finally {
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
	        if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
		}
		return list;
	}
}
