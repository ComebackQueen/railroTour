package net.search.db;

import java.sql.SQLException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import etc.function.DB_Connection;

public class Review_DAO extends DB_Connection{
	public Review_Bean Review_Insert(String email_id, Review_Bean review){
		int result=0;
		try {
			
			String sql="select count(*) as cnt from review where content_id=? and content_type_id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, review.getContentid());
			pstmt.setInt(2, review.getContenttypeid());
			rs=pstmt.executeQuery();
			
			int cnt=0;
			
			while(rs.next()) {
				cnt=rs.getInt("cnt");
			}
			
			System.out.println('1');
			
			if(cnt>0) {
				sql = "insert into review select ?, ?, ?, ?, MAX(review_num)+1, ?, ? from review where content_id=? and content_type_id=?";

				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, email_id);
				pstmt.setInt(2, review.getContentid());
				pstmt.setInt(3, review.getLike_yn());
				pstmt.setString(4, review.getMemo());
				pstmt.setString(5, review.getDatetime());
				pstmt.setInt(6, review.getContenttypeid());
				pstmt.setInt(7, review.getContentid());
				pstmt.setInt(8, review.getContenttypeid());
				result = pstmt.executeUpdate();
			}
			else {
				sql = "insert into review values(?, ?, ?, ?, 0, ?, ?)";

				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, email_id);
				pstmt.setInt(2, review.getContentid());
				pstmt.setInt(3, review.getLike_yn());
				pstmt.setString(4, review.getMemo());
				pstmt.setString(5, review.getDatetime());
				pstmt.setInt(6, review.getContenttypeid());
				result = pstmt.executeUpdate();
			}
			
			sql="select * from review where email_id=? and content_id=? and like_yn=? and memo=? and date_time=? and content_type_id=?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email_id);
			pstmt.setInt(2, review.getContentid());
			pstmt.setInt(3, review.getLike_yn());
			pstmt.setString(4, review.getMemo());
			pstmt.setString(5, review.getDatetime());
			pstmt.setInt(6, review.getContenttypeid());
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				review.setReview_num(rs.getInt("review_num"));
			}
			System.out.println("review num : "+review.getReview_num());
			
			sql="insert into review_hashtag values(?, ?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, review.getContentid());
			pstmt.setInt(2, review.getContenttypeid());
			pstmt.setInt(3, review.getReview_num());
			for(int i=0; i<review.getTags().size(); i++) {
				pstmt.setString(4, review.getTags().get(i));
				pstmt.executeUpdate();
			}
			
			sql="insert into review_imgfile values(?, ?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, review.getContentid());
			pstmt.setInt(2, review.getContenttypeid());
			pstmt.setInt(3, review.getReview_num());
			for(int i=0; i<review.getFilesPath().size(); i++) {
				pstmt.setString(4, review.getFilesPath().get(i));
				pstmt.executeUpdate();
			}
		}catch(Exception ex) {
			System.out.println("Review_Insert ERROR : "+ex);
		}finally {
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
	        if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
		}
		
		return review; //넣기 성공!
	}
	public String getProfile(String email_id) {
		String sql="select * from member where email_id=?";
		String name=null;
		try {
			
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, email_id);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				name=rs.getString("imgfile");
			}
	
		}catch(Exception ex) {
			System.out.println("getNikname ERROR : "+ex);
		}finally {
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
	        if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
		}
		return name;
	}
	public String getNikname(String email_id) {
		String sql="select * from member where email_id=?";
		String name=null;
		try {
			
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, email_id);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				name=rs.getString("nikname");
			}
	
		}catch(Exception ex) {
			System.out.println("getNikname ERROR : "+ex);
		}finally {
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
	        if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
		}
		return name;
	}
	public int RemoveReview(int contentid, int contenttypeid, int review_num) {
		String sql="delete from review where content_id=? and content_type_id=? and review_num=?";
		int result=0;
		try {
			
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, contentid);
			pstmt.setInt(2, contenttypeid);
			pstmt.setInt(3, review_num);
			result=pstmt.executeUpdate();
			
			sql="delete from review_hashtag where content_id=? and content_type_id=? and review_num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, contentid);
			pstmt.setInt(2, contenttypeid);
			pstmt.setInt(3, review_num);
			result=pstmt.executeUpdate();
			
			sql="delete from review_imgfile where content_id=? and content_type_id=? and review_num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, contentid);
			pstmt.setInt(2, contenttypeid);
			pstmt.setInt(3, review_num);
			result=pstmt.executeUpdate();
		}catch(Exception ex) {
			System.out.println("getNikname ERROR : "+ex);
		}finally {
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
	        if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
		}
		return result;
	}
	public JSONObject getAdditional_Review(int contentid, int contenttypeid, int rownum) {
		JSONObject result=new JSONObject();
		String sql="SELECT * FROM review WHERE content_id=? and content_type_id=? ORDER BY date_time desc limit "+(rownum-5)+",5";
		ArrayList<Review_Bean> review_list=new ArrayList<Review_Bean>();
		
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, contentid);
			pstmt.setInt(2, contenttypeid);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				review_list.add(new Review_Bean());
				review_list.get(review_list.size()-1).setContentid(rs.getInt("content_id"));
				review_list.get(review_list.size()-1).setContenttypeid(rs.getInt("content_type_id"));
				review_list.get(review_list.size()-1).setDatetime(rs.getString("date_time"));
				review_list.get(review_list.size()-1).setLike_yn(rs.getInt("like_yn"));
				review_list.get(review_list.size()-1).setMemo(rs.getString("memo"));
				review_list.get(review_list.size()-1).setEmail_id(rs.getString("email_id"));
				review_list.get(review_list.size()-1).setReview_num(rs.getInt("review_num"));
			}
			
			for(int i=0; i<review_list.size(); i++) {
				sql="select * from member where email_id=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, review_list.get(i).getEmail_id());
				rs=pstmt.executeQuery();
				
				while(rs.next()) {
					review_list.get(i).setNikname(rs.getString("nikname"));
					review_list.get(i).setProfile_img(rs.getString("imgfile"));
				}
				
				sql="select * from review_hashtag where content_id=? and content_type_id=? and review_num=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, review_list.get(i).getContentid());
				pstmt.setInt(2, review_list.get(i).getContenttypeid());
				pstmt.setInt(3, review_list.get(i).getReview_num());
				rs=pstmt.executeQuery();
				
				while(rs.next()) {
					review_list.get(i).getTags().add(rs.getString("hashtag"));
				}
				
				sql="select * from review_imgfile where content_id=? and content_type_id=? and review_num=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, review_list.get(i).getContentid());
				pstmt.setInt(2, review_list.get(i).getContenttypeid());
				pstmt.setInt(3, review_list.get(i).getReview_num());
				rs=pstmt.executeQuery();
				
				while(rs.next()) {
					review_list.get(i).getFilesPath().add(rs.getString("file_path"));
				}
			}
			
			JSONArray jsonarray=new JSONArray();
			JSONObject jsonobject, fileobject, tagobject;
			JSONArray filearray, tagarray;
			for(int i=0; i<review_list.size(); i++) {
				jsonobject=new JSONObject();
				fileobject=new JSONObject();
				tagobject=new JSONObject();
				
				jsonobject.put("nikname", review_list.get(i).getNikname());
				jsonobject.put("like_yn", review_list.get(i).getLike_yn());
				jsonobject.put("memo", review_list.get(i).getMemo());
				jsonobject.put("datetime", review_list.get(i).getDatetime().substring(0, 19));
				jsonobject.put("profileimg", review_list.get(i).getProfile_img());
				jsonobject.put("review_num", review_list.get(i).getReview_num());
				
				for(int o=0; o<review_list.get(i).getTags().size(); o++) {
					tagobject.put("tag"+o, review_list.get(i).getTags().get(o));
				}
				tagarray=new JSONArray();
				tagarray.add(tagobject);
				
				for(int p=0; p<review_list.get(i).getFilesPath().size(); p++) {
					fileobject.put("file"+p, review_list.get(i).getFilesPath().get(p));
				}
				filearray=new JSONArray();
				filearray.add(fileobject);
				
				jsonobject.put("tags", tagarray);
				jsonobject.put("files", filearray);
				
				jsonarray.add(jsonobject);
			}
			result.put("reviews", jsonarray);
		}catch(Exception ex) {
			System.out.println("getAdditional_Review ERROR : "+ex);
		}finally {
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
	        if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
		}
		return result;
	}
	public JSONObject Wordcloud(int contentid, int contenttypeid) {
		JSONObject result=new JSONObject();
		String sql="SELECT hashtag, COUNT(*) as cnt FROM review_hashtag WHERE content_id=? AND content_type_id=? GROUP BY hashtag";
		ArrayList<WordcloudBean> wordcloudList=new ArrayList<WordcloudBean>();
		
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, contentid);
			pstmt.setInt(2, contenttypeid);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				wordcloudList.add(new WordcloudBean());
				wordcloudList.get(wordcloudList.size()-1).setCount(rs.getInt("cnt"));
				wordcloudList.get(wordcloudList.size()-1).setText(rs.getString("hashtag"));
			}
			
			JSONObject word;
			JSONArray list=new JSONArray();
			for(int i=0; i<wordcloudList.size(); i++) {
				word=new JSONObject();
				word.put("size", wordcloudList.get(i).getCount());
				word.put("word", wordcloudList.get(i).getText());
				
				list.add(word);
			}
			result.put("words", list);

		}catch(Exception ex) {
			System.out.println("getAdditional_Review ERROR : "+ex);
		}finally {
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
	        if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
		}
		return result;
	}
}
