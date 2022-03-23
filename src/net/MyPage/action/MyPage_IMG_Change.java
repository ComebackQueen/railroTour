package net.MyPage.action;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import net.MyPage.db.MemberINFO_Bean;
import net.MyPage.db.MyPage_DAO;
import net.commons.action.Action;
import net.commons.action.ActionForward;

public class MyPage_IMG_Change implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession(); // 회원 인증 성공시 아이디를 세션에 등록할 세션 객체 생성
		int max=1024*1024*10;
		MultipartRequest multi;
		MyPage_DAO mypage=new MyPage_DAO();
		
		//File dir = new File("C:/Users/손준호/eclipse-workspace/Railro_Tour_WEB1234/WebContent/file/"+session.getAttribute("ID").toString());
		File dir = new File("/cartoonpoet/tomcat/webapps/ROOT/file/"+session.getAttribute("ID").toString());
		// 디렉토리들이 있는지 확인
		if(dir.isDirectory()){//디렉토리가 있으면
			//multi=new MultipartRequest(request, "C:/Users/손준호/eclipse-workspace/Railro_Tour_WEB1234/WebContent/file/"+session.getAttribute("ID").toString(), max, "UTF-8");
			multi=new MultipartRequest(request, "/cartoonpoet/tomcat/webapps/ROOT/file/"+session.getAttribute("ID").toString(), max, "UTF-8");
		}
		else{//디렉토리가 없으면
			dir.mkdirs();
			//multi=new MultipartRequest(request, "C:/Users/손준호/eclipse-workspace/Railro_Tour_WEB1234/WebContent/file/"+session.getAttribute("ID").toString(), max, "UTF-8");
			multi=new MultipartRequest(request, "/cartoonpoet/tomcat/webapps/ROOT/file/"+session.getAttribute("ID").toString(), max, "UTF-8");
		}
		String url="./file/"+session.getAttribute("ID").toString()+"/"+multi.getFilesystemName("imgfile");
		System.out.println(url);
		
		mypage.setProFileImg(url, session.getAttribute("ID").toString());
		
		response.setContentType("application/text");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(url);
		return null;
	}

}