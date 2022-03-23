package net.search.action;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.oreilly.servlet.MultipartRequest;

import net.commons.action.Action;
import net.commons.action.ActionForward;
import net.search.db.Review_Bean;
import net.search.db.Review_DAO;

public class Review_Add_Action implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		ActionForward forward = new ActionForward();
		HttpSession session=request.getSession();
		int max=1024*1024*10;
		MultipartRequest multi;
		Review_Bean review_data=new Review_Bean();
		
	    SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");//dd/MM/yyyy
	    Date now = new Date();
	    String strDate = sdfDate.format(now);
	    System.out.println(strDate);
		//File dir = new File("C:/Users/손준호/eclipse-workspace/Railro_Tour_WEB1234/WebContent/ReviewImg/"+session.getAttribute("ID").toString());
		File dir = new File("/cartoonpoet/tomcat/webapps/ROOT/ReviewImg/"+session.getAttribute("ID").toString());
		// 디렉토리들이 있는지 확인
		if(dir.isDirectory()){//디렉토리가 있으면
			//multi=new MultipartRequest(request, "C:/Users/손준호/eclipse-workspace/Railro_Tour_WEB1234/WebContent/ReviewImg/"+session.getAttribute("ID").toString(), max, "UTF-8");
			multi=new MultipartRequest(request, "/cartoonpoet/tomcat/webapps/ROOT/ReviewImg/"+session.getAttribute("ID").toString(), max, "UTF-8");
		}
		else{//디렉토리가 없으면
			dir.mkdirs();
			//multi=new MultipartRequest(request, "C:/Users/손준호/eclipse-workspace/Railro_Tour_WEB1234/WebContent/ReviewImg/"+session.getAttribute("ID").toString(), max, "UTF-8");
			multi=new MultipartRequest(request, "/cartoonpoet/tomcat/webapps/ROOT/ReviewImg/"+session.getAttribute("ID").toString(), max, "UTF-8");
		}
		
		for(int i=0; i<5; i++) {
			if(multi.getFilesystemName("file"+i)!=null) {
				review_data.getFilesPath().add(new String("./ReviewImg/"+session.getAttribute("ID")+"/"+multi.getFilesystemName("file"+i)));
			}
		}
		for(int i=0; i<10; i++) {
			if(multi.getParameter("tag"+i)!=null) {
				review_data.getTags().add(new String(multi.getParameter("tag"+i)));
				System.out.println("태그 : "+multi.getParameter("tag"+i));
			}
		}
		review_data.setDatetime(strDate);
		review_data.setContentid(Integer.parseInt(multi.getParameter("contentid").toString()));
		review_data.setContenttypeid(Integer.parseInt(multi.getParameter("contenttypeid")));
		review_data.setMemo(multi.getParameter("textarea").toString());
		review_data.setLike_yn(Integer.parseInt(multi.getParameter("like")));

		Review_DAO review_insert=new Review_DAO();
		review_data=review_insert.Review_Insert(session.getAttribute("ID").toString(), review_data);
		
		System.out.println("완료");
		JSONObject json=new JSONObject();
		
		json.put("date", review_data.getDatetime().substring(0, 19));
		json.put("memo", review_data.getMemo());
		json.put("like_yn", review_data.getLike_yn());
		json.put("nikname", review_insert.getNikname(session.getAttribute("ID").toString()));
		json.put("review_num", review_data.getReview_num());
		json.put("profile", review_insert.getProfile(session.getAttribute("ID").toString()));
		JSONArray array=new JSONArray();
		for(int i=0; i<review_data.getFilesPath().size(); i++) {
			array.add(review_data.getFilesPath().get(i));
		}
		json.put("files", array);
		
		array=new JSONArray();
		for(int i=0; i<review_data.getTags().size(); i++) {
			array.add(review_data.getTags().get(i));
		}
		json.put("tags", array);
		
		System.out.println(json);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(json);
		return null;
	}

}