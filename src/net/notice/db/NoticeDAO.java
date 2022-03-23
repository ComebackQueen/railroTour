package net.notice.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import etc.function.DB_Connection;

public class NoticeDAO extends DB_Connection{

	/*글의 개수 구하기(전체 글의 개수)*/
	public int getListCount(){
		int x = 0;
		try{
			pstmt=con.prepareStatement("select count(*) from notice");
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				x=rs.getInt(1); //count한값을 x에 저장
			}
		} catch(Exception e){
			System.out.println("getListCount 에러: " + e);
		} finally{
			if(rs != null) try{rs.close();} catch(SQLException e){}
			if(pstmt != null) try{pstmt.close();}catch(SQLException e){}
		}
		return x;
	}
	
	public int getSearchCount(HashMap<String,Object> listopt){
		int result = 0;
		String sql;
		String opt = (String)listopt.get("opt");
		String condition = (String)listopt.get("condition");
		
		try{
			if(opt.equals("0")){
				sql = "select count(*) from notice where title like ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, '%'+condition+'%');
			} else if(opt.equals("1")){
				sql = "select count(*) from notice where content like ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, '%'+condition+'%');
			}
		
			rs = pstmt.executeQuery();	
			if(rs.next()){
				result=rs.getInt(1); //count한값을 x에 저장
			}
		} catch(Exception e){
			System.out.println(opt);
			System.out.println("getSearchCount 에러: " + e);
		} finally{
			if(rs != null) try{rs.close();} catch(SQLException e){}
			if(pstmt != null) try{pstmt.close();}catch(SQLException e){}
		}
		return result;
	}
	/*글 목록 보기 List객체로 반환한다. 인수1) 출력할 페이지, 인수2) 한 페이지당 표시할 글 수 */
	public List getNoticeList(int page, int limit){
		String notice_list_sql = "select * from"+
				"(select  rownum rnum, num, emailid, title, content, " + 
				"dates, hits, file_name1, file_name2, file_name3, file_name4, file_name5, file_path" +
				" from notice)" +
				" where rnum >= ? and rnum <= ?";
		
		List list = new ArrayList();
		int startrow=(page-1)*10+1; // 읽기 시작할 row 번호
		int endrow=startrow+limit-1; // 읽을 마지막 row 번호
		try{
			pstmt = con.prepareStatement(notice_list_sql);
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, endrow);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				NoticeBean notice = new NoticeBean();
				notice.setNum(rs.getInt("num"));
				notice.setEmailid(rs.getString("emailid"));
				notice.setTitle(rs.getString("title"));
				notice.setContent(rs.getString("content"));
				notice.setDates(rs.getDate("dates"));
				notice.setReadcnt(rs.getInt("hits"));
				notice.setFileName_1(rs.getString("file_name1"));
				notice.setFileName_2(rs.getString("file_name2"));
				notice.setFileName_3(rs.getString("file_name3"));
				notice.setFileName_4(rs.getString("file_name4"));
				notice.setFileName_5(rs.getString("file_name5"));
				notice.setFilePath(rs.getString("file_path"));
				list.add(notice);
			}
			return list;
		} catch(Exception e){
			System.out.println("getNoticeList 에러: " + e);
		} finally{
			if(rs!=null) try{rs.close();}catch(SQLException e) {}
			if(pstmt != null) try{pstmt.close();}catch(SQLException e){}
		}
		return null;
	}
	
	public List getSearchNoticeList(int page, int limit, HashMap<String, Object> listOpt){
		String sql;
		
		String opt = (String)listOpt.get("opt"); // 검색옵션
		String condition = (String)listOpt.get("condition");//검색내용
		List list = new ArrayList();
		int startrow=(page-1)*10+1; // 읽기 시작할 row 번호
		int endrow=startrow+limit-1; // 읽을 마지막 row 번호
		
		try{
			if(opt.equals("0")) {
				sql = "select * from"+
						"(select  rownum rnum, num, emailid, title, content, " + 
				"dates, hits, file_name1, file_name2, file_name3, file_name4, file_name5, file_path" +
						" from" +
						"(select * from notice where title like ?))" +
						" where rnum >= ? and rnum <= ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%"+condition+"%");
				pstmt.setInt(2, startrow);
				pstmt.setInt(3, endrow);
			} else if(opt.equals("1")) {
				sql = "select * from"+
						"(select  rownum rnum, num, emailid, title, content, " + 
				"dates, hits, file_name1, file_name2, file_name3, file_name4, file_name5, file_path" +
						" from" +
						"(select * from notice where content like ?))" +
						" where rnum >= ? and rnum <= ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%"+condition+"%");
				pstmt.setInt(2, startrow);
				pstmt.setInt(3, endrow);
			}
			
			rs = pstmt.executeQuery();
			while(rs.next()){
				NoticeBean notice = new NoticeBean();
				notice.setNum(rs.getInt("num"));
				notice.setEmailid(rs.getString("emailid"));
				notice.setTitle(rs.getString("title"));
				notice.setContent(rs.getString("content"));
				notice.setDates(rs.getDate("dates"));
				notice.setReadcnt(rs.getInt("hits"));
				notice.setFileName_1(rs.getString("file_name1"));
				notice.setFileName_2(rs.getString("file_name2"));
				notice.setFileName_3(rs.getString("file_name3"));
				notice.setFileName_4(rs.getString("file_name4"));
				notice.setFileName_5(rs.getString("file_name5"));
				notice.setFilePath(rs.getString("file_path"));
				list.add(notice);
			}
			return list;
		} catch(Exception e){
			System.out.println("getSearchNoticeList 에러: " + e);
		} finally{
			if(rs!=null) try{rs.close();}catch(SQLException e) {}
			if(pstmt != null) try{pstmt.close();}catch(SQLException e){}
		}
		return null;
	}
	
	/* 글 내용 보기(글 레코드 번호를 인수로 받아온다)*/
	public NoticeBean getDetail(int num) throws Exception{
		NoticeBean notice = null;
		try{
			pstmt = con.prepareStatement("select * from notice where num = ?");
			pstmt.setInt(1, num);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				notice = new NoticeBean();
				notice.setNum(rs.getInt("num"));
				notice.setEmailid(rs.getString("emailid"));
				notice.setTitle(rs.getString("title"));
				notice.setContent(rs.getString("content"));
				notice.setDates(rs.getDate("dates"));
				notice.setReadcnt(rs.getInt("hits"));
				notice.setFileName_1(rs.getString("file_name1"));
				notice.setFileName_2(rs.getString("file_name2"));
				notice.setFileName_3(rs.getString("file_name3"));
				notice.setFileName_4(rs.getString("file_name4"));
				notice.setFileName_5(rs.getString("file_name5"));
				notice.setFilePath(rs.getString("file_path"));
			}
			return notice;
		} catch(Exception e){
			System.out.println("getDetail 에러: " + e);
		} finally{
			if(rs != null) try{rs.close();}catch(SQLException e){}
			if(pstmt != null)try{pstmt.close();}catch(SQLException e){}
		}
		return null;
	}
	
	/*글 등록
	 * 1) num필드의 최대값을 얻어온다.(이유는 중복되지 않은 값을 가져오기 위해)
	 */
	public boolean noticeInsert(NoticeBean notice){
		int num=0;
		String sql="";
		
		int result = 0;
		
		try{
			pstmt=con.prepareStatement("select max(num) from notice");
			rs = pstmt.executeQuery();
			
			if(rs.next())
				num = rs.getInt(1) + 1; // 글이 등록되어있으면 글 번호 + 1
			else
				num=1;
			
			sql = "insert into notice (num, emailid, title,";
			sql += "content, file_name1, file_name2, file_name3, file_name4, file_name5, file_path, " +
					"hits, dates) values(?,?,?,?,?,?,?,?,?,?,?,sysdate)";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, notice.getEmailid());
			pstmt.setString(3, notice.getTitle());
			pstmt.setString(4, notice.getContent());
			pstmt.setString(5, notice.getFileName_1());
			pstmt.setString(6, notice.getFileName_2());
			pstmt.setString(7, notice.getFileName_3());
			pstmt.setString(8, notice.getFileName_4());
			pstmt.setString(9, notice.getFileName_5());
			pstmt.setString(10, notice.getFilePath());
			pstmt.setInt(11, 0);
			
			result = pstmt.executeUpdate();
			if(result == 0) return false;
			
			return true;
		} catch(Exception e){
			System.out.println("noticeInsert 에러: " + e);
		} finally {
			if(rs != null) try{rs.close();}catch(SQLException e) {}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException e){}
		}
		return false;
	}
	
	
	// 글 수정
	public boolean noticeModify(NoticeBean modifynotice) throws Exception{
		String sql = "update notice set title=?,";
		sql += "content=?, file_name1=?, file_name2=?, file_name3=?, file_name4=?, file_name5=?, file_path=? where num=?";
		
		try{
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, modifynotice.getTitle());
			pstmt.setString(2, modifynotice.getContent());
			pstmt.setString(3, modifynotice.getFileName_1());
			pstmt.setString(4, modifynotice.getFileName_2());
			pstmt.setString(5, modifynotice.getFileName_3());
			pstmt.setString(6, modifynotice.getFileName_4());
			pstmt.setString(7, modifynotice.getFileName_5());
			pstmt.setString(8, modifynotice.getFilePath());
			pstmt.setInt(9, modifynotice.getNum());
			pstmt.executeUpdate();
			return true;
		}catch(Exception e){
			System.out.println("noticeModify 에러 : " + e);
		} finally {
			if(rs!=null) try{rs.close();}catch(SQLException e){}
			if(pstmt!=null)try{pstmt.close();}catch(SQLException e){}
		}
		return false;
	}
	
	//글 삭제(액션 클래스에서 비밀번호 일치 여부 확인후 이 메서드를 수행)
	public boolean noticeDelete(int num){
		String notice_delete_sql="delete from notice where num=?";
		
		int result = 0;
		
		try{
			pstmt=con.prepareStatement(notice_delete_sql);
			pstmt.setInt(1, num);
			result=pstmt.executeUpdate();
			if(result==0) return false;
			
			return true;
		} catch(Exception e){
			System.out.println("noticeDelete 에러: " + e);
		} finally{
			try{
				if(pstmt!=null)pstmt.close();
			} catch(Exception e) {}
		}
		
		return false;
	}
	
	//조회수 업데이트(글 내용을 확인하는 순간 호출)
	public void setReadCountUpdate(int num) throws Exception{
		String sql="update notice set hits = " +
				"hits + 1 where num = " + num;
		
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.executeUpdate();
		}catch(SQLException e){
			System.out.println("setReadCountUpdate 에러 : " + e);
		}
	}
	
	//글쓴이인지 확인(글쓴이를 확인할 글의 정보를 얻는다)
	public boolean isNoticeWriter(int num, String id){
		String notice_sql="select * from notice where num=?";
		
		try{
			pstmt=con.prepareStatement(notice_sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			rs.next();

			if(id.equals(rs.getString("emailid"))){
				return true;
			} 
			
		}catch(SQLException e){
			System.out.println("isNoticeWriter 에러 : " + e);
		}
		return false;
	}
	
	
}
