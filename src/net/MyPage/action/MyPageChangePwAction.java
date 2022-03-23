package net.MyPage.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.MyPage.db.MemberINFO_Bean;
import net.MyPage.db.MyPage_DAO;
import net.commons.action.Action;
import net.commons.action.ActionForward;

public class MyPageChangePwAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession(); // 회원 인증 성공시 아이디를 세션에 등록할 세션 객체 생성
		String id = (String)session.getAttribute("id");
		String oldPw = (String)request.getParameter("oldpassword");
		String newPw = (String)request.getParameter("newpassword");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		MyPage_DAO mypage=new MyPage_DAO();
		
		int check = mypage.changePassword(id, oldPw, newPw);
		
		if(check == 1){
			out.println("<script>");			
			out.println("alert('비밀번호 변경처리 되었습니다.');");			
			out.println("location.href='./Main.me';");		
			out.println("</script>");		
			out.close();	
			return null;
		}else {
			out.println("<script>");			
			out.println("alert('현재 비밀번호가 틀립니다.');");			
			out.println("location.href='./MyPagePW.me';");		
			out.println("</script>");		
			out.close();	
			return null;
		}
	}

}