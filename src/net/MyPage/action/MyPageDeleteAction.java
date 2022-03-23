package net.MyPage.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.MyPage.db.MemberINFO_Bean;
import net.MyPage.db.MyPage_DAO;
import net.commons.action.Action;
import net.commons.action.ActionForward;

public class MyPageDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession(); // 회원 인증 성공시 아이디를 세션에 등록할 세션 객체 생성
		String id = (String)session.getAttribute("id");
		String pw = (String)request.getParameter("pw");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		//System.out.println(pw);
		MyPage_DAO mypage=new MyPage_DAO();
		
		int check = mypage.deleteMember(id, pw);
		
		//System.out.println(check);
		if(check == 1){
			session.invalidate(); // 삭제했다면 세션정보를 삭제한다.
			out.println("<script>");
			out.println("alert('탈퇴처리 되었습니다.');");
			out.println("location.href='./Main.me';");
			out.println("</script>");
			out.close();
			return null;
		} else {
			out.println("<script>");
			out.println("alert('비밀번호가 맞지 않습니다.');");
			out.println("location.href='./MyPageRemove.me';");
			out.println("</script>");
			out.close();
			return null;
		}
		
	}

}