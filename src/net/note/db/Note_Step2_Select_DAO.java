package net.note.db;

import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

import etc.function.DB_Connection;

public class Note_Step2_Select_DAO extends DB_Connection{

	public double Search_mapX(int sigungucode, int areacode) {
		String sql="select * from area_locate where sigungu_code=? and area_code=?";
		double mapX=0;
		
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, sigungucode);
			pstmt.setInt(2, areacode);
			rs=pstmt.executeQuery();
			
			while(rs.next())
				mapX=rs.getDouble("mapx");
			

		}catch(Exception ex) {
			System.out.println("Search_mapX ERROR : "+ex);
		}finally {
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
	        if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
		}
		
		return mapX;
	}
	public double Search_mapY(int sigungucode, int areacode) {
		String sql="select * from area_locate where sigungu_code=? and area_code=?";
		double mapY=0;
		
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, sigungucode);
			pstmt.setInt(2, areacode);
			rs=pstmt.executeQuery();
			
			while(rs.next())
				mapY=rs.getDouble("mapy");
			

		}catch(Exception ex) {
			System.out.println("Search_mapY ERROR : "+ex);
		}finally {
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
	        if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
		}
		
		return mapY;
	}
	public String Search_Note_Name(int Travel_ID, String Email_ID) {
		String sql="select * from note_info1 where travel_id=? and email_id=?";
		
		String Note_name="";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, Travel_ID);
			pstmt.setString(2, Email_ID);
			rs=pstmt.executeQuery();
			
			while(rs.next())
				Note_name=rs.getString("note_name");
			

		}catch(Exception ex) {
			System.out.println("Search_Note_Name ERROR : "+ex);
		}finally {
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
	        if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
		}
		return Note_name;
	}
	
	public Date Search_Travel_Start_Day(int Travel_ID, String Email_ID) {
		String sql="select * from note_info1 where travel_id=? and email_id=?";
		
		String Start_Day = "";
		Date to = null;
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, Travel_ID);
			pstmt.setString(2, Email_ID);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				Start_Day=rs.getString("travel_start_day");
			}
			
		
			SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");

			to = transFormat.parse(Start_Day);
			
		}catch(Exception ex) {
			System.out.println("Search_Travel_Start_Day ERROR : "+ex);
		}finally {
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
	        if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
		}


		return to;
	}
	public int Search_Travel_Day(int Travel_ID, String Email_ID) {
		String sql="select * from note_info1 where travel_id=? and email_id=?";
		
		int Travel_day = 0;
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, Travel_ID);
			pstmt.setString(2, Email_ID);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				Travel_day=rs.getInt("travel_day");
			}
			

			
		}catch(Exception ex) {
			System.out.println("Search_Travel_Day ERROR : "+ex);
		}finally {
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
	        if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
		}
		
		return Travel_day;
	}
	
	public String Search_Travel_Tema(int Travel_ID, String Email_ID) {
		String sql="select * from note_info1 where travel_id=? and email_id=?";
		
		String Tema="";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, Travel_ID);
			pstmt.setString(2, Email_ID);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				Tema=rs.getString("travel_tema");
			}

		}catch(Exception ex) {
			System.out.println("Search_Travel_Tema ERROR : "+ex);
		}finally {
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
	        if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
		}
		
		return Tema;
	}
	public int Search_Travel_People(int Travel_ID, String Email_ID) {
		String sql="select * from note_info1 where travel_id=? and email_id=?";
		
		int people=0;
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, Travel_ID);
			pstmt.setString(2, Email_ID);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				people=rs.getInt("travel_people");
			}

		}catch(Exception ex) {
			System.out.println("Search_Travel_People ERROR : "+ex);
		}finally {
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
	        if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
		}
		
		return people;
	}
	
	public ArrayList<Note_Step2_Day_List_Bean> Search_Day_List(int Travel_ID){
		ArrayList<Note_Step2_Day_List_Bean> Day_List=new ArrayList<Note_Step2_Day_List_Bean>();
		String sql="select * from note_travel_area where travel_id=? order by travel_area_day asc";
		
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, Travel_ID);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				Day_List.add(new Note_Step2_Day_List_Bean(rs.getString("travel_area_name"), rs.getInt("travel_area_day")));
			}

		}catch(Exception ex) {
			System.out.println("Search_Travel_People ERROR : "+ex);
		}finally {
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
	        if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
		}
		
		return Day_List;
	}
	public int do_code_Action(String area) {
		switch(area) {
			case "?????? ?????????": return 5;
			case "?????? ??????": return 5;
			case "?????? ??????": return 5;
			case "?????? ??????": return 5;
			case "?????? ??????": return 5;
			case "?????????": return 37;
			case "?????????": return 37;
			case "?????????": return 37;
			case "?????????": return 37;
			case "?????????": return 37;
			case "?????????": return 37;
			case "?????????": return 37;
			case "?????????": return 37;
			case "?????????": return 37;
			case "?????????": return 37;
			case "?????????": return 37;
			case "?????????": return 37;
			case "?????????": return 37;
			case "?????????": return 37;
			case "?????????": return 38;
			case "?????????": return 38;
			case "?????????": return 38;
			case "?????????": return 38;
			case "?????????": return 38;
			case "?????????": return 38;
			case "?????????": return 38;
			case "?????????": return 38;
			case "?????????": return 38;
			case "?????????": return 38;
			case "?????????": return 38;
			case "?????????": return 38;
			case "?????????": return 38;
			case "?????????": return 38;
			case "?????????": return 38;
			case "?????????": return 38;
			case "?????????": return 38;
			case "?????????": return 38;
			case "?????????": return 38;
			case "?????????": return 38;
			case "?????????": return 38;
			case "?????????": return 38;
		}
		return -1;
	}
	public int area_code_Action(String area) {
		switch(area) {
		case "?????? ?????????": return 1;
		case "?????? ??????": return 2;
		case "?????? ??????": return 4;
		case "?????? ??????": return 3;
		case "?????? ??????": return 5;
		case "?????????": return 1;
		case "?????????": return 2;
		case "?????????": return 3;
		case "?????????": return 4;
		case "?????????": return 5;
		case "?????????": return 6;
		case "?????????": return 7;
		case "?????????": return 8;
		case "?????????": return 9;
		case "?????????": return 10;
		case "?????????": return 11;
		case "?????????": return 12;
		case "?????????": return 13;
		case "?????????": return 14;
		case "?????????": return 1;
		case "?????????": return 2;
		case "?????????": return 3;
		case "?????????": return 4;
		case "?????????": return 5;
		case "?????????": return 6;
		case "?????????": return 7;
		case "?????????": return 8;
		case "?????????": return 9;
		case "?????????": return 10;
		case "?????????": return 11;
		case "?????????": return 12;
		case "?????????": return 13;
		case "?????????": return 16;
		case "?????????": return 17;
		case "?????????": return 18;
		case "?????????": return 19;
		case "?????????": return 20;
		case "?????????": return 21;
		case "?????????": return 22;
		case "?????????": return 23;
		case "?????????": return 24;
	}
		return -1;
	}
	
	public ArrayList<Note_Step2_ALL_INFO_Bean> Area_Info_Select_Action(int areacode, int sigungucode){
		ArrayList<Note_Step2_ALL_INFO_Bean> Info_List=new ArrayList<Note_Step2_ALL_INFO_Bean>();
		JSONObject Part_Item;
		URL url;
		try {
			url=new URL("http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaBasedList?serviceKey="+Key+"&pageNo=1&startPage=1&numOfRows=1&pageSize=1&MobileApp=RailroTour&MobileOS=ETC&arrange=O&contentTypeId=12&areaCode="+sigungucode+"&sigunguCode="+areacode+"&listYN=Y&_type=json");
			InputStreamReader isr = new InputStreamReader(url.openConnection().getInputStream(),"UTF-8");
			
			JSONObject items = (JSONObject) JSONValue.parseWithException(isr); 
			JSONObject obj=(JSONObject) items.get("response");
			obj=(JSONObject) obj.get("body");
			
			String total=obj.get("totalCount").toString();
			int totalCount=Integer.parseInt(total);
			
			System.out.println("???????????? ????????? ?????? : "+totalCount+"???");
			url=new URL("http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaBasedList?serviceKey="+Key+"&pageNo=1&startPage=1&numOfRows="+total+"&pageSize=1&MobileApp=RailroTour&MobileOS=ETC&arrange=O&contentTypeId=12&areaCode="+sigungucode+"&sigunguCode="+areacode+"&listYN=Y&_type=json");
			isr = new InputStreamReader(url.openConnection().getInputStream(),"UTF-8");
			
			items=(JSONObject) JSONValue.parseWithException(isr);
			obj=(JSONObject) items.get("response");
			obj=(JSONObject) obj.get("body");
			obj=(JSONObject) obj.get("items");
			JSONArray jsonarray=(JSONArray) obj.get("item");

			
			for(int i=0; i<jsonarray.size(); i++) {
				
				Part_Item=(JSONObject) jsonarray.get(i);
				
				Info_List.add(new Note_Step2_ALL_INFO_Bean());
				
				
				Info_List.get(i).setAddr1(Part_Item.get("addr1").toString());
				
				if(Part_Item.get("addr2")!=null) {
					Info_List.get(i).setAddr2(Part_Item.get("addr2").toString());
				}
				else {
					Info_List.get(i).setAddr2(null);
				}
				
				Info_List.get(i).setAreacode(Integer.parseInt(Part_Item.get("areacode").toString()));

				
				if(Part_Item.get("cat1")!=null) {
					Info_List.get(i).setCat1(Part_Item.get("cat1").toString());
				}
				else {
					Info_List.get(i).setCat1(null);
				}
			
				if(Part_Item.get("cat2")!=null) {
					Info_List.get(i).setCat2(Part_Item.get("cat2").toString());
				}
				else {
					Info_List.get(i).setCat2(null);
				}
				
				if(Part_Item.get("cat3")!=null) {
					Info_List.get(i).setCat3(Part_Item.get("cat3").toString());
				}
				else {
					Info_List.get(i).setCat3(null);
				}
				
				Info_List.get(i).setContent_id(Integer.parseInt(Part_Item.get("contentid").toString()));
				Info_List.get(i).setContenttype_id(Integer.parseInt(Part_Item.get("contenttypeid").toString()));

				
				if(Part_Item.get("firstimage")!=null) {
					Info_List.get(i).setFirstimage(Part_Item.get("firstimage").toString());
				}
				else {
					Info_List.get(i).setFirstimage("./jpg/no_image.gif");
				}
				
				if(Part_Item.get("firstimage2")!=null) {
					Info_List.get(i).setFirstimage2(Part_Item.get("firstimage2").toString());
				}
				else {
					Info_List.get(i).setFirstimage2("./jpg/no_image.gif");
				}
	
				Info_List.get(i).setMapx(Double.parseDouble(Part_Item.get("mapy").toString()));
				Info_List.get(i).setMapy(Double.parseDouble(Part_Item.get("mapx").toString()));
				Info_List.get(i).setSigungucode(Integer.parseInt(Part_Item.get("sigungucode").toString()));
				Info_List.get(i).setTitle(Part_Item.get("title").toString());
				
			}
		}catch(Exception e) {
			System.out.println("Area_Info_Select_Action ERROR1  : "+e);
		}
		
		try {
			url=new URL("http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaBasedList?serviceKey="+Key+"&pageNo=1&startPage=1&numOfRows=1&pageSize=1&MobileApp=RailroTour&MobileOS=ETC&arrange=O&contentTypeId=39&areaCode="+sigungucode+"&sigunguCode="+areacode+"&listYN=Y&_type=json");
			InputStreamReader isr = new InputStreamReader(url.openConnection().getInputStream(),"UTF-8");
			
			JSONObject items = (JSONObject) JSONValue.parseWithException(isr); 
			JSONObject obj=(JSONObject) items.get("response");
			obj=(JSONObject) obj.get("body");
			
			String total=obj.get("totalCount").toString();
			int size=Info_List.size();
			int totalCount=size+Integer.parseInt(total);
			System.out.println("????????? ????????? ?????? : "+totalCount);
			
			url=new URL("http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaBasedList?serviceKey="+Key+"&pageNo=1&startPage=1&numOfRows="+totalCount+"&pageSize=1&MobileApp=RailroTour&MobileOS=ETC&arrange=O&contentTypeId=39&areaCode="+sigungucode+"&sigunguCode="+areacode+"&listYN=Y&_type=json");
			isr = new InputStreamReader(url.openConnection().getInputStream(),"UTF-8");
			
			items=(JSONObject) JSONValue.parseWithException(isr);
			obj=(JSONObject) items.get("response");
			obj=(JSONObject) obj.get("body");
			obj=(JSONObject) obj.get("items");
			JSONArray jsonarray=(JSONArray) obj.get("item");

			for(int i=size; i<totalCount; i++) {

				Part_Item=(JSONObject) jsonarray.get(i%size);
				
				Info_List.add(new Note_Step2_ALL_INFO_Bean());
				Info_List.get(i).setAddr1(Part_Item.get("addr1").toString());
				
				if(Part_Item.get("addr2")!=null) {
					Info_List.get(i).setAddr2(Part_Item.get("addr2").toString());
				}
				else {
					Info_List.get(i).setAddr2(null);
				}
				Info_List.get(i).setAreacode(Integer.parseInt(Part_Item.get("areacode").toString()));

				
				if(Part_Item.get("cat1")!=null) {
					Info_List.get(i).setCat1(Part_Item.get("cat1").toString());
				}
				else {
					Info_List.get(i).setCat1(null);
				}
			
				if(Part_Item.get("cat2")!=null) {
					Info_List.get(i).setCat2(Part_Item.get("cat2").toString());
				}
				else {
					Info_List.get(i).setCat2(null);
				}
				
				if(Part_Item.get("cat3")!=null) {
					Info_List.get(i).setCat3(Part_Item.get("cat3").toString());
				}
				else {
					Info_List.get(i).setCat3(null);
				}
				
				Info_List.get(i).setContent_id(Integer.parseInt(Part_Item.get("contentid").toString()));
				Info_List.get(i).setContenttype_id(Integer.parseInt(Part_Item.get("contenttypeid").toString()));

				
				if(Part_Item.get("firstimage")!=null) {
					Info_List.get(i).setFirstimage(Part_Item.get("firstimage").toString());
				}
				else {
					Info_List.get(i).setFirstimage("./jpg/no_image.gif");
				}
				
				if(Part_Item.get("firstimage2")!=null) {
					Info_List.get(i).setFirstimage2(Part_Item.get("firstimage2").toString());
				}
				else {
					Info_List.get(i).setFirstimage2("./jpg/no_image.gif");
				}
				
				Info_List.get(i).setMapx(Double.parseDouble(Part_Item.get("mapy").toString()));
				Info_List.get(i).setMapy(Double.parseDouble(Part_Item.get("mapx").toString()));
				Info_List.get(i).setSigungucode(Integer.parseInt(Part_Item.get("sigungucode").toString()));
				Info_List.get(i).setTitle(Part_Item.get("title").toString());
			}
		}catch(Exception e) {
			System.out.println("Area_Info_Select_Action ERROR2  : "+e);
		}
		
		return Info_List;
	}
	public void resetRoute(int travel_id) {
		String sql="delete from note_info2 where travel_id=?";
		int result=0;
		try {
			
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, travel_id);
			result=pstmt.executeUpdate();

		}catch(Exception ex) {
			System.out.println("resetRoute ERROR : "+ex);
		}finally {
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
	        if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
		}
	}
	
	public ArrayList<Note_All_Plans_Bean> Note_info2_All_Select(int travel_id){
		String sql="select * from note_info2 where travel_id=? order by day_orders asc, orders asc";
		ArrayList<Note_All_Plans_Bean> NoteInfo2_List=new ArrayList<Note_All_Plans_Bean>();
		
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, travel_id);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				NoteInfo2_List.add(new Note_All_Plans_Bean());
				NoteInfo2_List.get(rs.getRow()-1).setSigungucode(rs.getInt("sigungu_code"));
				NoteInfo2_List.get(rs.getRow()-1).setAreacode(rs.getInt("area_code"));
				NoteInfo2_List.get(rs.getRow()-1).setContent_ID(rs.getInt("content_id"));
				NoteInfo2_List.get(rs.getRow()-1).setContent_Type_ID(rs.getInt("content_type_id"));
				NoteInfo2_List.get(rs.getRow()-1).setDate(rs.getString("dates"));
				NoteInfo2_List.get(rs.getRow()-1).setDay(rs.getString("days"));
				NoteInfo2_List.get(rs.getRow()-1).setKind1(rs.getString("kinds_1"));
				NoteInfo2_List.get(rs.getRow()-1).setKind2(rs.getString("kinds_2"));
				NoteInfo2_List.get(rs.getRow()-1).setDay_orders(rs.getInt("day_orders"));
				NoteInfo2_List.get(rs.getRow()-1).setOrders(rs.getInt("orders"));
				NoteInfo2_List.get(rs.getRow()-1).setWeek(rs.getString("weeks"));
				NoteInfo2_List.get(rs.getRow()-1).setMemo(rs.getString("memo"));
			}

			URL url;
			InputStreamReader isr;
			
			JSONObject items; 

			System.out.println("?????? ?????? ?????? : "+NoteInfo2_List.size());
			
			for(int i=0; i<NoteInfo2_List.size(); i++) {
				if(NoteInfo2_List.get(i).getKind1().compareTo("??????")==0) {
					url=new URL("http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailCommon?serviceKey="+Key+"&numOfRows=1&pageSize=1&pageNo=1&startPage=1&MobileOS=ETC&MobileApp=AppTest&contentId="+NoteInfo2_List.get(i).getContent_ID()+"&contentTypeId="+NoteInfo2_List.get(i).getContent_Type_ID()+"&defaultYN=Y&firstImageYN=Y&areacodeYN=N&catcodeYN=N&addrinfoYN=N&mapinfoYN=Y&overviewYN=N&_type=json");
					isr = new InputStreamReader(url.openConnection().getInputStream(),"UTF-8");
					items=new JSONObject();
					items=(JSONObject) JSONValue.parseWithException(isr); 
					items=(JSONObject) items.get("response");
					items=(JSONObject) items.get("body");
					items=(JSONObject) items.get("items");
					items=(JSONObject) items.get("item");
					NoteInfo2_List.get(i).setTitle(items.get("title").toString());
					if(items.get("firstimage")!=null) {
						NoteInfo2_List.get(i).setImg(items.get("firstimage").toString());
					}
					else {
						NoteInfo2_List.get(i).setImg("./jpg/no_image.gif");
					}
					NoteInfo2_List.get(i).setLat(Double.parseDouble(items.get("mapy").toString()));
					NoteInfo2_List.get(i).setLng(Double.parseDouble(items.get("mapx").toString()));
				}
			}
			
		}catch(Exception ex) {
			System.out.println("Note_info2_All_Select ERROR : "+ex);
		}finally {
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
	        if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
		}
		
		return NoteInfo2_List;
	}
}
