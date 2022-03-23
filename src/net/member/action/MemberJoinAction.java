package net.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.commons.action.Action;
import net.commons.action.ActionForward;
import net.member.db.MemberBean;
import net.member.db.MemberDAO;

public class MemberJoinAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		ActionForward forward=new ActionForward();
		MemberDAO memberdao=new MemberDAO(); 
		MemberBean member=new MemberBean(); 
		boolean result=false; 
		
		member.setMEMBER_ID(request.getParameter("MEMBER_ID"));
		member.setMEMBER_PW(request.getParameter("MEMBER_PW"));
		member.setMEMBER_NAME(request.getParameter("MEMBER_NAME"));
		member.setMEMBER_NIKNAME(request.getParameter("MEMBER_NIKNAME"));
		member.setMEMBER_GENDER(request.getParameter("MEMBER_GENDER"));
		member.setPHONE_NUMBER(request.getParameter("MEMBER_PHONE"));
		member.setIMG_NAME("./mynote_jpg/man-user.png");
		member.setSTAMP_CNT(0);
		
		
		if(memberdao.joinIDCheck(member) == true) {
			System.out.println("아이디가 있음!");
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.println("<script>");
			out.println("alert('아이디가 이미 존재합니다.');");
			out.println("location.href='./MemberJoin2.me'");
			out.println("</script>");
			out.close();
			return null;
		}
		if(memberdao.joinNickNameCheck(member)==true) {
			System.out.println("닉네임이 있음!");
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.println("<script>");
			out.println("alert('닉네임이 이미 존재합니다.');");
			out.println("location.href='./MemberJoin2.me'");
			out.println("</script>");
			out.close();
			return null;
		}
		result=memberdao.joinMember(member);
		
		if(result==false) {
			System.out.println("회원가입 실패");
			return null;
		}
		
		System.out.println("회원가입 완료!");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out=response.getWriter();
		out.println("<script>");
		out.println("alert('회원가입이 완료되었습니다.');");
		out.println("location.href='./Main.me'");
		out.println("</script>");
		out.close();
//		forward.setRedirect(true);
//		forward.setPath("./Main.me");
		

		return null;
	}
	
}
