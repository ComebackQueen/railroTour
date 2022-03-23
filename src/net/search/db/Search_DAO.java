package net.search.db;

import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import etc.function.DB_Connection;
import net.member.db.MemberBean;
import net.note.db.Note_Plans_List_Bean;

public class Search_DAO extends DB_Connection{
	public ArrayList<Tour_Food_Bean> getTour(String keyword){
		ArrayList<Tour_Food_Bean> Tour=new ArrayList<Tour_Food_Bean>();
		try {
			URL url=new URL("http://api.visitkorea.or.kr/openapi/service/rest/KorService/searchKeyword?serviceKey="+Key+"&MobileApp=AppTest&MobileOS=ETC&pageNo=1&startPage=1&numOfRows=1&pageSize=1&listYN=Y&arrange=A&contentTypeId=12&keyword="+URLEncoder.encode(keyword, "UTF-8")+"&_type=json");
			InputStreamReader isr = new InputStreamReader(url.openConnection().getInputStream(),"UTF-8");
			JSONObject items=(JSONObject) JSONValue.parseWithException(isr); 
			items=(JSONObject) items.get("response");
			items=(JSONObject) items.get("body");
			int totalCount=Integer.parseInt(items.get("totalCount").toString());

			if (totalCount < 5) {
				for(int i=1; i<=totalCount; i++) {
					items=new JSONObject();
					url=new URL("http://api.visitkorea.or.kr/openapi/service/rest/KorService/searchKeyword?serviceKey="+Key+"&MobileApp=AppTest&MobileOS=ETC&pageNo="+i+"&startPage=1&numOfRows=1&pageSize=1&listYN=Y&arrange=A&contentTypeId=12&keyword="+URLEncoder.encode(keyword, "UTF-8")+"&_type=json");
					isr = new InputStreamReader(url.openConnection().getInputStream(),"UTF-8");
					items=(JSONObject) JSONValue.parseWithException(isr); 
					items=(JSONObject) items.get("response");
					items=(JSONObject) items.get("body");
					items=(JSONObject) items.get("items");
					items=(JSONObject) items.get("item");
					//System.out.println("아래 : "+items);
					
					Tour.add(new Tour_Food_Bean());
					//콘텐츠 아이디&타입 설정
					Tour.get(Tour.size()-1).setContentid(Integer.parseInt(items.get("contentid").toString())); 
					Tour.get(Tour.size()-1).setContenttypeid(Integer.parseInt(items.get("contenttypeid").toString()));
					Tour.get(Tour.size()-1).setTotalcount(totalCount);
					//대표 이미지 설정
					if(items.get("firstimage")!=null) {
						Tour.get(Tour.size()-1).setImg(items.get("firstimage").toString());
					}
					else {
						if(items.get("firstimage2")!=null) {
							Tour.get(Tour.size()-1).setImg(items.get("firstimage2").toString());
						}
						else {
							Tour.get(Tour.size()-1).setImg("./jpg/no_image.gif");
						}
					}
					
					//이름 설정
					Tour.get(Tour.size()-1).setTitle(items.get("title").toString());
					
					url=new URL("http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailCommon?serviceKey="+Key+"&numOfRows=1&pageSize=1&pageNo=1&startPage=1&MobileOS=ETC&MobileApp=AppTest&contentId="+Tour.get(i-1).getContentid()+"&contentTypeId="+Tour.get(i-1).getContenttypeid()+"&defaultYN=N&firstImageYN=N&areacodeYN=N&catcodeYN=N&addrinfoYN=N&mapinfoYN=N&overviewYN=Y&_type=json");
					isr = new InputStreamReader(url.openConnection().getInputStream(),"UTF-8");
					items=(JSONObject) JSONValue.parseWithException(isr); 
					items=(JSONObject) items.get("response");
					items=(JSONObject) items.get("body");
					items=(JSONObject) items.get("items");
					items=(JSONObject) items.get("item");
					
					if(items.get("overview")!=null) {
						Tour.get(Tour.size()-1).setContent(items.get("overview").toString());
					}
					else {
						Tour.get(Tour.size()-1).setContent(null);
					}
					
					String sql="SELECT SUM(like_yn) as like_cnt FROM review WHERE content_id=? AND content_type_id=?";
					pstmt=con.prepareStatement(sql);
					pstmt.setInt(1, Tour.get(i-1).getContentid());
					pstmt.setInt(2, Tour.get(i-1).getContenttypeid());
					rs=pstmt.executeQuery();
					
					while(rs.next()) {
						Tour.get(i-1).setLike_cnt(rs.getInt("like_cnt"));
					}
					
					sql="select count(*) as review_count from review where content_id=? and content_type_id=?";
					pstmt=con.prepareStatement(sql);
					pstmt.setInt(1, Tour.get(i-1).getContentid());
					pstmt.setInt(2, Tour.get(i-1).getContenttypeid());
					rs=pstmt.executeQuery();
					
					while(rs.next()) {
						Tour.get(i-1).setReview_cnt(rs.getInt("review_count"));
					}
				}
			}
			else {
				for(int i=1; i<=4; i++) {
					items=new JSONObject();
					url=new URL("http://api.visitkorea.or.kr/openapi/service/rest/KorService/searchKeyword?serviceKey="+Key+"&MobileApp=AppTest&MobileOS=ETC&pageNo="+i+"&startPage=1&numOfRows=1&pageSize=1&listYN=Y&arrange=A&contentTypeId=12&keyword="+URLEncoder.encode(keyword, "UTF-8")+"&_type=json");
					isr = new InputStreamReader(url.openConnection().getInputStream(),"UTF-8");
					items=(JSONObject) JSONValue.parseWithException(isr); 
					items=(JSONObject) items.get("response");
					items=(JSONObject) items.get("body");
					items=(JSONObject) items.get("items");
					items=(JSONObject) items.get("item");
					//System.out.println("아래 : "+items);
					
					Tour.add(new Tour_Food_Bean());
					//콘텐츠 아이디&타입 설정
					Tour.get(Tour.size()-1).setContentid(Integer.parseInt(items.get("contentid").toString())); 
					Tour.get(Tour.size()-1).setContenttypeid(Integer.parseInt(items.get("contenttypeid").toString()));
					Tour.get(Tour.size()-1).setTotalcount(totalCount);
					//대표 이미지 설정
					if(items.get("firstimage")!=null) {
						Tour.get(Tour.size()-1).setImg(items.get("firstimage").toString());
					}
					else {
						if(items.get("firstimage2")!=null) {
							Tour.get(Tour.size()-1).setImg(items.get("firstimage2").toString());
						}
						else {
							Tour.get(Tour.size()-1).setImg("./jpg/no_image.gif");
						}
					}
					
					//이름 설정
					Tour.get(Tour.size()-1).setTitle(items.get("title").toString());
					
					url=new URL("http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailCommon?serviceKey="+Key+"&numOfRows=1&pageSize=1&pageNo=1&startPage=1&MobileOS=ETC&MobileApp=AppTest&contentId="+Tour.get(i-1).getContentid()+"&contentTypeId="+Tour.get(i-1).getContenttypeid()+"&defaultYN=N&firstImageYN=N&areacodeYN=N&catcodeYN=N&addrinfoYN=N&mapinfoYN=N&overviewYN=Y&_type=json");
					isr = new InputStreamReader(url.openConnection().getInputStream(),"UTF-8");
					items=(JSONObject) JSONValue.parseWithException(isr); 
					items=(JSONObject) items.get("response");
					items=(JSONObject) items.get("body");
					items=(JSONObject) items.get("items");
					items=(JSONObject) items.get("item");
					
					if(items.get("overview")!=null) {
						Tour.get(Tour.size()-1).setContent(items.get("overview").toString());
					}
					else {
						Tour.get(Tour.size()-1).setContent(null);
					}
					
					String sql="SELECT SUM(like_yn) as like_cnt FROM review WHERE content_id=? AND content_type_id=?";
					pstmt=con.prepareStatement(sql);
					pstmt.setInt(1, Tour.get(i-1).getContentid());
					pstmt.setInt(2, Tour.get(i-1).getContenttypeid());
					rs=pstmt.executeQuery();
					
					while(rs.next()) {
						Tour.get(i-1).setLike_cnt(rs.getInt("like_cnt"));
					}
					
					sql="select count(*) as review_count from review where content_id=? and content_type_id=?";
					pstmt=con.prepareStatement(sql);
					pstmt.setInt(1, Tour.get(i-1).getContentid());
					pstmt.setInt(2, Tour.get(i-1).getContenttypeid());
					rs=pstmt.executeQuery();
					
					while(rs.next()) {
						Tour.get(i-1).setReview_cnt(rs.getInt("review_count"));
					}
				}
			}
		}catch(Exception e) {
			System.out.println("getTour ERROR : "+e);
		}finally {
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
	        if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
		}
		return Tour;
	}
	public ArrayList<Tour_Food_Bean> getFood(String keyword){
		ArrayList<Tour_Food_Bean> Food=new ArrayList<Tour_Food_Bean>();
		try {
			URL url=new URL("http://api.visitkorea.or.kr/openapi/service/rest/KorService/searchKeyword?serviceKey="+Key+"&MobileApp=AppTest&MobileOS=ETC&pageNo=1&startPage=1&numOfRows=1&pageSize=1&listYN=Y&arrange=A&contentTypeId=39&keyword="+URLEncoder.encode(keyword, "UTF-8")+"&_type=json");
			InputStreamReader isr = new InputStreamReader(url.openConnection().getInputStream(),"UTF-8");
			JSONObject items=(JSONObject) JSONValue.parseWithException(isr); 
			items=(JSONObject) items.get("response");
			items=(JSONObject) items.get("body");
			int totalCount=Integer.parseInt(items.get("totalCount").toString());

			if (totalCount < 5) {
				for(int i=1; i<=totalCount; i++) {
					items=new JSONObject();
					url=new URL("http://api.visitkorea.or.kr/openapi/service/rest/KorService/searchKeyword?serviceKey="+Key+"&MobileApp=AppTest&MobileOS=ETC&pageNo="+i+"&startPage=1&numOfRows=1&pageSize=1&listYN=Y&arrange=A&contentTypeId=39&keyword="+URLEncoder.encode(keyword, "UTF-8")+"&_type=json");
					isr = new InputStreamReader(url.openConnection().getInputStream(),"UTF-8");
					items=(JSONObject) JSONValue.parseWithException(isr); 
					items=(JSONObject) items.get("response");
					items=(JSONObject) items.get("body");
					items=(JSONObject) items.get("items");
					items=(JSONObject) items.get("item");
					//System.out.println("아래 : "+items);
					
					Food.add(new Tour_Food_Bean());
					//콘텐츠 아이디&타입 설정
					Food.get(Food.size()-1).setContentid(Integer.parseInt(items.get("contentid").toString())); 
					Food.get(Food.size()-1).setContenttypeid(Integer.parseInt(items.get("contenttypeid").toString()));
					
					//대표 이미지 설정
					if(items.get("firstimage")!=null) {
						Food.get(Food.size()-1).setImg(items.get("firstimage").toString());
					}
					else {
						if(items.get("firstimage2")!=null) {
							Food.get(Food.size()-1).setImg(items.get("firstimage2").toString());
						}
						else {
							Food.get(Food.size()-1).setImg("./jpg/no_image.gif");
						}
					}
					
					//이름 설정
					Food.get(Food.size()-1).setTitle(items.get("title").toString());
					
					url=new URL("http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailCommon?serviceKey="+Key+"&numOfRows=1&pageSize=1&pageNo=1&startPage=1&MobileOS=ETC&MobileApp=AppTest&contentId="+Food.get(i-1).getContentid()+"&contentTypeId="+Food.get(i-1).getContenttypeid()+"&defaultYN=N&firstImageYN=N&areacodeYN=N&catcodeYN=N&addrinfoYN=N&mapinfoYN=N&overviewYN=Y&_type=json");
					isr = new InputStreamReader(url.openConnection().getInputStream(),"UTF-8");
					items=(JSONObject) JSONValue.parseWithException(isr); 
					items=(JSONObject) items.get("response");
					items=(JSONObject) items.get("body");
					items=(JSONObject) items.get("items");
					items=(JSONObject) items.get("item");
					
					if(items.get("overview")!=null) {
						Food.get(Food.size()-1).setContent(items.get("overview").toString());
					}
					else {
						Food.get(Food.size()-1).setContent(null);
					}
					
					String sql="SELECT SUM(like_yn) as like_cnt FROM review WHERE content_id=? AND content_type_id=?";
					pstmt=con.prepareStatement(sql);
					pstmt.setInt(1, Food.get(i-1).getContentid());
					pstmt.setInt(2, Food.get(i-1).getContenttypeid());
					rs=pstmt.executeQuery();
					
					while(rs.next()) {
						Food.get(i-1).setLike_cnt(rs.getInt("like_cnt"));
					}
					
					sql="select count(*) as review_count from review where content_id=? and content_type_id=?";
					pstmt=con.prepareStatement(sql);
					pstmt.setInt(1, Food.get(i-1).getContentid());
					pstmt.setInt(2, Food.get(i-1).getContenttypeid());
					rs=pstmt.executeQuery();
					
					while(rs.next()) {
						Food.get(i-1).setReview_cnt(rs.getInt("review_count"));
					}
				}
			}
			else {
				for(int i=1; i<=4; i++) {
					items=new JSONObject();
					url=new URL("http://api.visitkorea.or.kr/openapi/service/rest/KorService/searchKeyword?serviceKey="+Key+"&MobileApp=AppTest&MobileOS=ETC&pageNo="+i+"&startPage=1&numOfRows=1&pageSize=1&listYN=Y&arrange=A&contentTypeId=39&keyword="+URLEncoder.encode(keyword, "UTF-8")+"&_type=json");
					isr = new InputStreamReader(url.openConnection().getInputStream(),"UTF-8");
					items=(JSONObject) JSONValue.parseWithException(isr); 
					items=(JSONObject) items.get("response");
					items=(JSONObject) items.get("body");
					items=(JSONObject) items.get("items");
					items=(JSONObject) items.get("item");
					System.out.println("아래 : "+items);
					
					Food.add(new Tour_Food_Bean());
					//콘텐츠 아이디&타입 설정
					Food.get(Food.size()-1).setContentid(Integer.parseInt(items.get("contentid").toString())); 
					Food.get(Food.size()-1).setContenttypeid(Integer.parseInt(items.get("contenttypeid").toString()));
					
					//대표 이미지 설정
					if(items.get("firstimage")!=null) {
						Food.get(Food.size()-1).setImg(items.get("firstimage").toString());
					}
					else {
						if(items.get("firstimage2")!=null) {
							Food.get(Food.size()-1).setImg(items.get("firstimage2").toString());
						}
						else {
							Food.get(Food.size()-1).setImg("./jpg/no_image.gif");
						}
					}
					
					//이름 설정
					Food.get(Food.size()-1).setTitle(items.get("title").toString());
					
					url=new URL("http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailCommon?serviceKey="+Key+"&numOfRows=1&pageSize=1&pageNo=1&startPage=1&MobileOS=ETC&MobileApp=AppTest&contentId="+Food.get(i-1).getContentid()+"&contentTypeId="+Food.get(i-1).getContenttypeid()+"&defaultYN=N&firstImageYN=N&areacodeYN=N&catcodeYN=N&addrinfoYN=N&mapinfoYN=N&overviewYN=Y&_type=json");
					isr = new InputStreamReader(url.openConnection().getInputStream(),"UTF-8");
					items=(JSONObject) JSONValue.parseWithException(isr); 
					items=(JSONObject) items.get("response");
					items=(JSONObject) items.get("body");
					items=(JSONObject) items.get("items");
					items=(JSONObject) items.get("item");
					
					if(items.get("overview")!=null) {
						Food.get(Food.size()-1).setContent(items.get("overview").toString());
					}
					else {
						Food.get(Food.size()-1).setContent(null);
					}
					
					String sql="SELECT SUM(like_yn) as like_cnt FROM review WHERE content_id=? AND content_type_id=?";
					pstmt=con.prepareStatement(sql);
					pstmt.setInt(1, Food.get(i-1).getContentid());
					pstmt.setInt(2, Food.get(i-1).getContenttypeid());
					rs=pstmt.executeQuery();
					
					while(rs.next()) {
						Food.get(i-1).setLike_cnt(rs.getInt("like_cnt"));
					}
					
					sql="select count(*) as review_count from review where content_id=? and content_type_id=?";
					pstmt=con.prepareStatement(sql);
					pstmt.setInt(1, Food.get(i-1).getContentid());
					pstmt.setInt(2, Food.get(i-1).getContenttypeid());
					rs=pstmt.executeQuery();
					
					while(rs.next()) {
						Food.get(i-1).setReview_cnt(rs.getInt("review_count"));
					}
				}
			}
		}catch(Exception e) {
			System.out.println("getFood ERROR : "+e);
		}finally {
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
	        if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
		}
		return Food;
	}
	
	public ArrayList<Note_Plans_List_Bean> getNote(String Keyword){
		ArrayList<Note_Plans_List_Bean> Railro_Note=new ArrayList<Note_Plans_List_Bean>();
		String sql="select * from note_info1 where note_name LIKE ? limit 3";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, "%"+Keyword+"%");
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
			
			for(int i=0; i<Railro_Note.size(); i++) {
				
				sql="select * from note_travel_area where travel_id=? order by travel_area_day asc";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, Railro_Note.get(i).getNote_ID());
				rs=pstmt.executeQuery();
				
				int cnt=0; 
				while(rs.next()) {
					if(cnt==0) {
						Railro_Note.get(i).setArea(rs.getString("travel_area_name"));
					}
					else {
						Railro_Note.get(i).PlusArea("-"+rs.getString("travel_area_name"));
					}
					cnt++;
				}
			}
			
			for(int i=0; i<Railro_Note.size(); i++) {
				sql="select count(*) AS total from note_like_cnt where travel_id=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, Railro_Note.get(i).getNote_ID());
				rs=pstmt.executeQuery();
				
				while(rs.next()) {
					Railro_Note.get(i).setLike(rs.getInt("total"));
				}
			}
		}catch(Exception e) {
			System.out.println("getNote ERROR : "+e);
		}finally {
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
	        if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
		}
		return Railro_Note;
	}
	public ArrayList<MemberBean> getMember(String keyword, String email_id){
		ArrayList<MemberBean> Member=new ArrayList<MemberBean>();
		String sql="select * from member where nikname LIKE ? limit 4";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, "%"+keyword+"%");
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				Member.add(new MemberBean());
				Member.get(Member.size()-1).setMEMBER_NIKNAME(rs.getString("nikname"));
				Member.get(Member.size()-1).setIMG_NAME(rs.getString("imgfile"));
				Member.get(Member.size()-1).setMEMBER_ID(rs.getString("email_id"));
			}
			
			for(int i=0; i<Member.size(); i++) {
				sql="select count(*) as note_cnt from note_info1 where email_id=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, Member.get(i).getMEMBER_ID());
				rs=pstmt.executeQuery();
				
				while(rs.next()) {
					Member.get(i).setNote_Count(rs.getInt("note_cnt"));
				}
				
				sql="select count(*) as follow_cnt from member_follow where email_id=? and follow_id=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, email_id);
				pstmt.setString(2, Member.get(i).getMEMBER_ID());
				rs=pstmt.executeQuery();
				
				while(rs.next()) {
					Member.get(i).setFollowing_YN(rs.getInt("follow_cnt"));
				}
			}
		}catch(Exception e) {
			System.out.println("getMember ERROR : "+e);
		}finally {
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
	        if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
		}
		return Member;
	}
	public ArrayList<Tour_Food_Bean> Filter_Search(int page_num, String keyword, int contenttypeid){
		ArrayList<Tour_Food_Bean> data=new ArrayList<Tour_Food_Bean>();
		try {
			URL url=new URL("http://api.visitkorea.or.kr/openapi/service/rest/KorService/searchKeyword?serviceKey="+Key+"&MobileApp=AppTest&MobileOS=ETC&pageNo=1&startPage=1&numOfRows=1&pageSize=1&listYN=Y&arrange=A&contentTypeId="+contenttypeid+"&keyword="+URLEncoder.encode(keyword, "UTF-8")+"&_type=json");
			InputStreamReader isr = new InputStreamReader(url.openConnection().getInputStream(),"UTF-8");
			JSONObject items=(JSONObject) JSONValue.parseWithException(isr); 
			items=(JSONObject) items.get("response");
			items=(JSONObject) items.get("body");
			int totalCount=Integer.parseInt(items.get("totalCount").toString());
			
			if(totalCount-((page_num-1)*10)>10) {
				System.out.println("10개단위");
				for(int i=(page_num-1)*10+1; i<=page_num*10; i++) {
					url=new URL("http://api.visitkorea.or.kr/openapi/service/rest/KorService/searchKeyword?serviceKey="+Key+"&MobileApp=AppTest&MobileOS=ETC&pageNo="+i+"&startPage=1&numOfRows=1&pageSize=1&listYN=Y&arrange=A&contentTypeId="+contenttypeid+"&keyword="+URLEncoder.encode(keyword, "UTF-8")+"&_type=json");
					isr = new InputStreamReader(url.openConnection().getInputStream(),"UTF-8");
					items=(JSONObject) JSONValue.parseWithException(isr); 
					items=(JSONObject) items.get("response");
					items=(JSONObject) items.get("body");
					items=(JSONObject) items.get("items");
					items=(JSONObject) items.get("item");
					
					data.add(new Tour_Food_Bean());
					data.get(data.size()-1).setTotalcount(totalCount);
					//콘텐츠 아이디&타입 설정
					data.get(data.size()-1).setContentid(Integer.parseInt(items.get("contentid").toString())); 
					data.get(data.size()-1).setContenttypeid(Integer.parseInt(items.get("contenttypeid").toString()));
					//System.out.println("검색결과 : "+i+" : "+items.get("contentid").toString());
					//대표 이미지 설정
					if(items.get("firstimage")!=null) {
						data.get(data.size()-1).setImg(items.get("firstimage").toString());
					}
					else {
						if(items.get("firstimage2")!=null) {
							data.get(data.size()-1).setImg(items.get("firstimage2").toString());
						}
						else {
							data.get(data.size()-1).setImg("./jpg/no_image.gif");
						}
					}
					
					//이름 설정
					data.get(data.size()-1).setTitle(items.get("title").toString());
					
					url=new URL("http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailCommon?serviceKey="+Key+"&numOfRows=1&pageSize=1&pageNo=1&startPage=1&MobileOS=ETC&MobileApp=AppTest&contentId="+data.get(data.size()-1).getContentid()+"&contentTypeId="+data.get(data.size()-1).getContenttypeid()+"&defaultYN=N&firstImageYN=N&areacodeYN=N&catcodeYN=N&addrinfoYN=N&mapinfoYN=N&overviewYN=Y&_type=json");
					isr = new InputStreamReader(url.openConnection().getInputStream(),"UTF-8");
					items=(JSONObject) JSONValue.parseWithException(isr); 
					items=(JSONObject) items.get("response");
					items=(JSONObject) items.get("body");
					items=(JSONObject) items.get("items");
					items=(JSONObject) items.get("item");
					
					if(items.get("overview")!=null) {
						data.get(data.size()-1).setContent(items.get("overview").toString());
					}
					else {
						data.get(data.size()-1).setContent(null);
					}
					
					String sql="SELECT SUM(like_yn) as like_cnt FROM review WHERE content_id=? AND content_type_id=?";
					pstmt=con.prepareStatement(sql);
					pstmt.setInt(1, data.get(data.size()-1).getContentid());
					pstmt.setInt(2, data.get(data.size()-1).getContenttypeid());
					rs=pstmt.executeQuery();
					
					while(rs.next()) {
						data.get(data.size()-1).setLike_cnt(rs.getInt("like_cnt"));
					}
					
					sql="select count(*) as review_count from review where content_id=? and content_type_id=?";
					pstmt=con.prepareStatement(sql);
					pstmt.setInt(1, data.get(data.size()-1).getContentid());
					pstmt.setInt(2, data.get(data.size()-1).getContenttypeid());
					rs=pstmt.executeQuery();
					
					while(rs.next()) {
						data.get(data.size()-1).setReview_cnt(rs.getInt("review_count"));
					}
				}
			}
			else {
				System.out.println("나머지");
				for(int i=(page_num-1)*10+1; i<=totalCount; i++) {
					url=new URL("http://api.visitkorea.or.kr/openapi/service/rest/KorService/searchKeyword?serviceKey="+Key+"&MobileApp=AppTest&MobileOS=ETC&pageNo="+i+"&startPage=1&numOfRows=1&pageSize=1&listYN=Y&arrange=A&contentTypeId="+contenttypeid+"&keyword="+URLEncoder.encode(keyword, "UTF-8")+"&_type=json");
					isr = new InputStreamReader(url.openConnection().getInputStream(),"UTF-8");
					items=(JSONObject) JSONValue.parseWithException(isr); 
					items=(JSONObject) items.get("response");
					items=(JSONObject) items.get("body");
					items=(JSONObject) items.get("items");
					items=(JSONObject) items.get("item");
					
					data.add(new Tour_Food_Bean());
					data.get(data.size()-1).setTotalcount(totalCount);
					//콘텐츠 아이디&타입 설정
					data.get(data.size()-1).setContentid(Integer.parseInt(items.get("contentid").toString())); 
					data.get(data.size()-1).setContenttypeid(Integer.parseInt(items.get("contenttypeid").toString()));
					
					//대표 이미지 설정
					if(items.get("firstimage")!=null) {
						data.get(data.size()-1).setImg(items.get("firstimage").toString());
					}
					else {
						if(items.get("firstimage2")!=null) {
							data.get(data.size()-1).setImg(items.get("firstimage2").toString());
						}
						else {
							data.get(data.size()-1).setImg("./jpg/no_image.gif");
						}
					}
					
					//이름 설정
					data.get(data.size()-1).setTitle(items.get("title").toString());
					
					url=new URL("http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailCommon?serviceKey="+Key+"&numOfRows=1&pageSize=1&pageNo=1&startPage=1&MobileOS=ETC&MobileApp=AppTest&contentId="+data.get(data.size()-1).getContentid()+"&contentTypeId="+data.get(data.size()-1).getContenttypeid()+"&defaultYN=N&firstImageYN=N&areacodeYN=N&catcodeYN=N&addrinfoYN=N&mapinfoYN=N&overviewYN=Y&_type=json");
					isr = new InputStreamReader(url.openConnection().getInputStream(),"UTF-8");
					items=(JSONObject) JSONValue.parseWithException(isr); 
					items=(JSONObject) items.get("response");
					items=(JSONObject) items.get("body");
					items=(JSONObject) items.get("items");
					items=(JSONObject) items.get("item");
					
					if(items.get("overview")!=null) {
						data.get(data.size()-1).setContent(items.get("overview").toString());
					}
					else {
						data.get(data.size()-1).setContent(null);
					}
					
					String sql="SELECT SUM(like_yn) as like_cnt FROM review WHERE content_id=? AND content_type_id=?";
					pstmt=con.prepareStatement(sql);
					pstmt.setInt(1, data.get(data.size()-1).getContentid());
					pstmt.setInt(2, data.get(data.size()-1).getContenttypeid());
					rs=pstmt.executeQuery();
					
					while(rs.next()) {
						data.get(data.size()-1).setLike_cnt(rs.getInt("like_cnt"));
					}
					
					sql="select count(*) as review_count from review where content_id=? and content_type_id=?";
					pstmt=con.prepareStatement(sql);
					pstmt.setInt(1, data.get(data.size()-1).getContentid());
					pstmt.setInt(2, data.get(data.size()-1).getContenttypeid());
					rs=pstmt.executeQuery();
					
					while(rs.next()) {
						data.get(data.size()-1).setReview_cnt(rs.getInt("review_count"));
					}
				}
			}
		}catch(Exception e) {
			System.out.println("Filter_Search ERROR : "+e);
		}finally {
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
	        if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
		}
		return data;
	}
	public int Filter_Note_Count(String keyword) {
		int totalcount = 0;
		try {
			String sql = "select count(*) as note_cnt from note_info1 where note_name LIKE ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + keyword + "%");
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				totalcount = rs.getInt("note_cnt");
			}
		} catch (Exception e) {
			System.out.println("Filter_RailroNote_Search ERROR : " + e);
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
		return totalcount;
	}
	public ArrayList<Note_Plans_List_Bean> Filter_RailroNote_Search(String keyword, int page_num, int count){
		ArrayList<Note_Plans_List_Bean> data=new ArrayList<Note_Plans_List_Bean>();
		try {
			
			String sql="select * from note_info1 where note_name LIKE ? limit "+(page_num-1)*9+",9";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, "%"+keyword+"%");
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				data.add(new Note_Plans_List_Bean());
				data.get(data.size()-1).setNote_ID(rs.getInt("travel_id")); //노트 고유 번호 
				data.get(data.size()-1).setTravel_Day(rs.getString("travel_start_day")); //출발일
				data.get(data.size()-1).setNote_Name(rs.getString("note_name")); //노트명
				data.get(data.size()-1).setTema_Name(rs.getString("travel_tema")); //테마명
				data.get(data.size()-1).setView(rs.getInt("note_view")); //조회수
				data.get(data.size()-1).setDay(rs.getInt("travel_day")); //일수
				data.get(data.size()-1).setImg(rs.getString("img")); //이미지
				data.get(data.size()-1).setEmail_id(rs.getString("email_id"));
			}
			
			for(int i=0; i<data.size(); i++) {
				sql="select * from member where email_id=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, data.get(i).getEmail_id());
				rs=pstmt.executeQuery();
				
				while(rs.next()) {
					data.get(i).setName(rs.getString("nikname"));
					data.get(i).setProfileimg(rs.getString("imgfile"));
				}
			}
			
			for(int i=0; i<data.size(); i++) {
				
				sql="select * from note_travel_area where travel_id=? order by travel_area_day asc";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, data.get(i).getNote_ID());
				rs=pstmt.executeQuery();
				
				int cnt=0; 
				while(rs.next()) {
					if(cnt==0) {
						data.get(i).setArea(rs.getString("travel_area_name"));
					}
					else {
						data.get(i).PlusArea("-"+rs.getString("travel_area_name"));
					}
					cnt++;
				}
			}
			
			for(int i=0; i<data.size(); i++) {
				sql="select count(*) AS total from note_like_cnt where travel_id=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, data.get(i).getNote_ID());
				rs=pstmt.executeQuery();
				
				while(rs.next()) {
					data.get(i).setLike(rs.getInt("total"));
				}
			}
		}catch(Exception e) {
			System.out.println("Filter_RailroNote_Search ERROR : "+e);
		}finally {
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
	        if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
		}
		return data;
	}
	public int getMemberSize(String keyword) {
		int totalcount=0;
		try {
			String sql="select count(*) as cnt from member where nikname LIKE ?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, "%"+keyword+"%");
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				totalcount=rs.getInt("cnt");
			}
		}catch(Exception e) {
			System.out.println("getMemberSize ERROR : "+e);
		}finally {
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
	        if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
		}
		return totalcount;
	}

	public ArrayList<MemberBean> getMemberList(String keyword, int totalcount, int page_num) {
		ArrayList<MemberBean> Member = new ArrayList<MemberBean>();
		try {

			String sql = "select * from member where nikname LIKE ? limit " +(page_num - 1)*12 + ",12";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + keyword + "%");
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Member.add(new MemberBean());
				Member.get(Member.size() - 1).setMEMBER_NIKNAME(rs.getString("nikname"));
				Member.get(Member.size() - 1).setIMG_NAME(rs.getString("imgfile"));
				Member.get(Member.size() - 1).setMEMBER_ID(rs.getString("email_id"));
			}

			for (int i = 0; i < Member.size(); i++) {
				sql = "select count(*) as note_cnt from note_info1 where email_id=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, Member.get(i).getMEMBER_ID());
				rs = pstmt.executeQuery();

				while (rs.next()) {
					Member.get(i).setNote_Count(rs.getInt("note_cnt"));
				}

				sql = "select count(*) as follow_cnt from member_follow where email_id=? and follow_id=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, Member.get(i).getMEMBER_ID());
				pstmt.setString(2, Member.get(i).getMEMBER_ID());
				rs = pstmt.executeQuery();

				while (rs.next()) {
					Member.get(i).setFollowing_YN(rs.getInt("follow_cnt"));
				}
			}
		} catch (Exception e) {
			System.out.println("getMemberList ERROR : " + e);
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
		return Member;
	}
	
	public void Popular_keyword(String keyword) {
		try {

			String sql = "select count(*) as cnt from popular_searches where keyword=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, keyword);
			rs = pstmt.executeQuery();
			
			int count=0;
			
			while (rs.next()) {
				count=rs.getInt("cnt");
			}
			
			if(count!=0) {
				int change=0;
				sql="update popular_searches set cnt=cnt+1 where keyword=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, keyword);
				change=pstmt.executeUpdate();
			}
			else {
				int insert=0;
				sql="insert into popular_searches values(?, 1)";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, keyword);
				insert=pstmt.executeUpdate();
			}
			
		} catch (Exception e) {
			System.out.println("Popular_keyword ERROR : " + e);
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
	public Top100_Bean[] getTop100(int contenttypeid, int page_num) {
		Top100_Bean[] top100=new Top100_Bean[20];
		try {
			for(int i=0; i<top100.length; i++) {
				top100[i]=new Top100_Bean();
			}
			String sql="SELECT SUM(like_yn) AS like_cnt, COUNT(*) AS review_cnt, content_id, content_type_id FROM review WHERE content_type_id="+contenttypeid+" GROUP BY content_id ORDER by SUM(like_yn) DESC limit "+((page_num-1)*20)+",20";
			pstmt = con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			int cnt=0;
			while(rs.next()) {
				top100[cnt].setContentid(rs.getInt("content_id"));
				top100[cnt].setContenttypeid(rs.getInt("content_type_id"));
				top100[cnt].setLike_cnt(rs.getInt("like_cnt")); //좋아요 수
				top100[cnt].setReview_cnt(rs.getInt("review_cnt")); //리뷰 개수
				cnt++;
			}
			
			URL url;
			InputStreamReader isr;
			JSONObject items; 
			for(int i=0; i<top100.length; i++) {
				sql="SELECT COUNT(*) AS tag_cnt, content_id, content_type_id, hashtag FROM review_hashtag WHERE content_id=? and content_type_id=? GROUP BY hashtag ORDER BY COUNT(*) DESC limit 4";
				
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, top100[i].getContentid());
				pstmt.setInt(2, top100[i].getContenttypeid());
				rs=pstmt.executeQuery();
				System.out.println(i);
				while(rs.next()) {
					top100[i].getTags().add(new String(rs.getString("hashtag")));
				}
				
				url=new URL("http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailCommon?serviceKey="+Key+"&numOfRows=1&pageSize=1&pageNo=1&startPage=1&MobileOS=ETC&MobileApp=AppTest&contentId="+top100[i].getContentid()+"&contentTypeId="+top100[i].getContenttypeid()+"&defaultYN=Y&firstImageYN=Y&areacodeYN=N&catcodeYN=N&addrinfoYN=N&mapinfoYN=N&overviewYN=N&_type=json");
				isr = new InputStreamReader(url.openConnection().getInputStream(),"UTF-8");
				items=(JSONObject) JSONValue.parseWithException(isr); 
				items=(JSONObject) items.get("response");
				items=(JSONObject) items.get("body");
				items=(JSONObject) items.get("items");
				items=(JSONObject) items.get("item");
				
				top100[i].setName(items.get("title").toString());
				
				if(items.get("firstimage")!=null) {
					top100[i].setImg(items.get("firstimage").toString());
				}
				else {
					if(items.get("firstimage2")!=null) {
						top100[i].setImg(items.get("firstimage2").toString());
					}
					else {
						top100[i].setImg("./jpg/no_image.gif");
					}
				}
			}
			
			
		} catch (Exception e) {
			System.out.println("getTop100 ERROR : " + e);
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
		
		return top100;
	}
	
	public Note_Plans_List_Bean[] getTop_Note(int page_num) {
		Note_Plans_List_Bean[] data=new Note_Plans_List_Bean[20];
		try {
			String sql="SELECT travel_id, COUNT(travel_id) FROM note_like_cnt GROUP BY travel_id ORDER BY COUNT(travel_id) DESC limit ?,20";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, ((page_num-1)*20));
			rs=pstmt.executeQuery();
			
			for(int i=0; i<data.length; i++) {
				data[i]=new Note_Plans_List_Bean();
			}
			int cnt=0;
			while(rs.next()) {
				data[cnt].setNote_ID(rs.getInt("travel_id")); //노트 고유 번호 

				cnt++;
			}

			
			for(int i=0; i<data.length; i++) {
				sql="select * from note_info1 where travel_id=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, data[i].getNote_ID());
				rs=pstmt.executeQuery();
				
				while(rs.next()) {
					data[i].setTravel_Day(rs.getString("travel_start_day")); //출발일
					data[i].setNote_Name(rs.getString("note_name")); //노트명
					data[i].setTema_Name(rs.getString("travel_tema")); //테마명
					data[i].setView(rs.getInt("note_view")); //조회수
					data[i].setDay(rs.getInt("travel_day")); //일수
					data[i].setImg(rs.getString("img")); //이미지
					data[i].setEmail_id(rs.getString("email_id"));
				}
				
				sql="select * from member where email_id LIKE ?";
				
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, data[i].getEmail_id());
				rs=pstmt.executeQuery();
				
				while(rs.next()) {
					data[i].setName(rs.getString("nikname"));
					data[i].setProfileimg(rs.getString("imgfile"));
				}
			}
			
			for(int i=0; i<data.length; i++) {
				sql="select * from note_travel_area where travel_id=? order by travel_area_day asc";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, data[i].getNote_ID());
				rs=pstmt.executeQuery();
				
				int cnt1=0; 
				while(rs.next()) {
					if(cnt1==0) {
						data[i].setArea(rs.getString("travel_area_name"));
					}
					else {
						data[i].PlusArea("-"+rs.getString("travel_area_name"));
					}
					cnt1++;
				}
			}
			
			for(int i=0; i<data.length; i++) {
				sql="select count(*) AS total from note_like_cnt where travel_id=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, data[i].getNote_ID());
				rs=pstmt.executeQuery();
				
				while(rs.next()) {
					data[i].setLike(rs.getInt("total"));
				}
			}
		} catch (Exception e) {
			System.out.println("getTop_Note ERROR : " + e);
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
		return data;
	}
	public ArrayList<Top100_Member_Bean> getTop_Member(int page_num) {
		ArrayList<Top100_Member_Bean> data=new ArrayList<Top100_Member_Bean>();
		
		try {
			String sql="SELECT email_id, follow_id, COUNT(follow_id) as follow_cnt FROM member_follow GROUP BY follow_id ORDER BY COUNT(follow_id) DESC limit ?,20";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, ((page_num-1)*20));
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				data.add(new Top100_Member_Bean());
				data.get(data.size()-1).setFollow_cnt(rs.getInt("follow_cnt"));
				data.get(data.size()-1).setEmail_id(rs.getString("follow_id"));
			}
			
			for(int i=0; i<data.size(); i++) {
				sql="select * from member where email_id=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, data.get(i).getEmail_id());
				rs=pstmt.executeQuery();
				
				while(rs.next()) {
					data.get(i).setNikname(rs.getString("nikname"));
					data.get(i).setImg(rs.getString("imgfile"));
				}
				
				sql="select count(*) as note_cnt from note_info1 where email_id=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, data.get(i).getEmail_id());
				rs=pstmt.executeQuery();
				
				while(rs.next()) {
					data.get(i).setNote_cnt(rs.getInt("note_cnt"));
				}
			}
			
		} catch (Exception e) {
			System.out.println("getTop_Member ERROR : " + e);
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
		
		return data;
	}
}
