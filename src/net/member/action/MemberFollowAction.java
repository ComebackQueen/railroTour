package net.member.action;

import java.io.PrintWriter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import etc.function.MobileCheck;
import net.commons.action.Action;
import net.commons.action.ActionForward;
import net.member.db.MemberBean;
import net.member.db.MemberDAO;

public class MemberFollowAction implements Action{
	
	   @Override
	   public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	      ActionForward forward=new ActionForward();
	      HttpSession session=request.getSession(); //회원 인증 성공시 아이디를 세션에 등록할 세션 객체 생성
	      int Note_ID=Integer.parseInt(request.getParameter("Note_ID"));
	      int Follow_YN=Integer.parseInt(request.getParameter("follow_YN"));
	      
	      MemberDAO memberdao=new MemberDAO();
	      
	      if(Follow_YN==1) {
	    	  memberdao.FollowMember(session.getAttribute("ID").toString(), Note_ID);
	      }
	      else if(Follow_YN==0){
	    	  memberdao.unFollowMember(session.getAttribute("ID").toString(), Note_ID);
	      }
	      return null;
	   }
	   
}