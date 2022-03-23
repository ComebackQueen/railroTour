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

public class MemberLoginAction implements Action{
	
   @Override
   public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
      ActionForward forward=new ActionForward();
      HttpSession session=request.getSession(); //회원 인증 성공시 아이디를 세션에 등록할 세션 객체 생성
      MemberDAO memberdao=new MemberDAO();
      MemberBean member=new MemberBean();
      String ID = null, PW = null;

      
      int result=-1; //기본 결과 값을 -1 (아이디가 존재하지 않는 다는 의미로 정의)
      System.out.println("우선 로그인 들어옴");
      /*
       * 로그인 폼에서 입력한 값을 memberbean객체에 저장
       */
      
      member.setMEMBER_ID(request.getParameter("ID"));
      member.setMEMBER_PW(request.getParameter("PW"));
      result=memberdao.isMember(member);
      boolean mobileCheck =MobileCheck.isMobile(request);
      //Login Fail
      if(result==0) { //PW fail
         response.setContentType("text/html;charset=UTF-8");
         PrintWriter out=response.getWriter();
         
         if(!mobileCheck) { //안드가아니라면==요청이 웹에서라면
            out.println("<script>");
            out.println("alert('비밀번호가 존재하지 않습니다.');");
            out.println("location.href='./MemberLogin.me';");
            out.println("</script>");
         } else  //안드가아니라면==요청이 웹에서라면
         {
            out.print("passwordOff"); 
         }
         out.close();
         return null;
      }
      else if(result==-1) {//ID fail
         response.setContentType("text/html;charset=UTF-8");
         PrintWriter out=response.getWriter();
         
         if(!mobileCheck) { //안드가아니라면==요청이 웹에서라면
            out.println("<script>");
            out.println("alert('아이디가 존재하지 않습니다.');");
            out.println("location.href='./MemberLogin.me';");
            out.println("</script>");
         } else  //안드가아니라면==요청이 웹에서라면
         {
            out.print("idOff"); 
         }
         
         out.close();
         return null;
      }
      
      //로그인 성공시
      if(!mobileCheck) 
      { //안드가아니라면==요청이 웹에서라면
         System.out.println("로그인 성공");
         ID = request.getParameter("ID");
         PW = request.getParameter("PW");
         String auto_login = request.getParameter("auto_login");
         if(auto_login != null){
            Cookie uidCookie = new Cookie("ID", ID);
            uidCookie.setMaxAge(60*60*24*7);
            uidCookie.setPath("/");
            response.addCookie(uidCookie);
            Cookie pwdCookie = new Cookie("PW", PW);
            pwdCookie.setMaxAge(60*60*24*7);
            pwdCookie.setPath("/");
            response.addCookie(pwdCookie);
         }
         session.setAttribute("ID", ID);
         session.setAttribute("PW", PW);
         session.setAttribute("id", member.getMEMBER_ID()); //Session Registration
         forward.setRedirect(true); //접속 끊었다가 다시 연결하면서 새로운 정보를 보여준다.
         forward.setPath("./Main.me"); //메인으로   
         return forward;
      }
      else //앱이라면
      {
         response.setContentType("text/html;charset=UTF-8");
         PrintWriter out=response.getWriter();
         System.out.println();
         out.print("loginSuccess");
         out.close();
      }
      return null;
   }
   
}