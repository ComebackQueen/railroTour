package net.MyPage.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.MyPage.db.MemberINFO_Bean;
import net.MyPage.db.MyPage_DAO;
import net.commons.action.Action;
import net.commons.action.ActionForward;

public class MyPageHomeAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession(); // 회원 인증 성공시 아이디를 세션에 등록할 세션 객체 생성
		
		MyPage_DAO mypage=new MyPage_DAO();
		MemberINFO_Bean member=new MemberINFO_Bean();
		
		member=mypage.getMemberInfo(session.getAttribute("ID").toString());
		
		request.setAttribute("member", member);
		
		forward.setRedirect(false); // 접속 끊었다가 다시 연결하면서 새로운 정보를 보여준다.
		forward.setPath("./mypage/MyPage_Management.jsp"); // 메인으로
		return forward;
	}

}