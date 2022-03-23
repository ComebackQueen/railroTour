package net.member.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.MyPage.action.MyPageChangePwAction;
import net.MyPage.action.MyPageDeleteAction;
import net.MyPage.action.MyPageHomeAction;
import net.MyPage.action.MyPage_IMG_Change;
import net.commons.action.Action;
import net.commons.action.ActionForward;


public class MemberFrontController extends HttpServlet implements Servlet{
	static final long serialVersionUID=1L;
    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{ 
        String RequestURI=request.getRequestURI(); 
        String contextPath=request.getContextPath(); 
        String command = RequestURI.substring(contextPath.length()); 
        ActionForward forward = null; 
        Action action = null; 
        
        /*
         * 페이지 이동부분
         */
        if(command.equals("/MemberJoin1.me")) { //Agreement agreement page
        	forward=new ActionForward();
        	forward.setRedirect(false);
        	forward.setPath("./member/sign_up_Form_1.jsp");
        }
        else if(command.equals("/MemberJoin2.me")){ //Sign UP page
        	forward=new ActionForward();
        	forward.setRedirect(false);
        	forward.setPath("./member/sign_up_Form_2.jsp");
        }
        else if(command.equals("/IntroRailro.me")){ //Introduce Railro
        	forward=new ActionForward();
        	forward.setRedirect(false);
        	forward.setPath("./member/Introduce.jsp");
        }
        else if(command.equals("/Main.me")) { //MainPage
        	action=new Main_Action();
        	try {
        		forward=action.execute(request, response);
        	}catch(Exception e) {
        		e.printStackTrace();
        	}
        }
        else if(command.equals("/MyPageManagement.me")){ //마이페이지 관리로 이동
        	action=new MyPageHomeAction();
        	try {
        		forward=action.execute(request, response);
        	}catch(Exception e) {
        		e.printStackTrace();
        	}
        }
        else if(command.equals("/MyPageIMGChange.me")){ //마이페이지 관리로 이동
        	action=new MyPage_IMG_Change();
        	try {
        		forward=action.execute(request, response);
        	}catch(Exception e) {
        		e.printStackTrace();
        	}
        }
        else if(command.equals("/MyPageRemove.me")){ //마이페이지 회원탈퇴로 이동
        	forward=new ActionForward();
        	forward.setRedirect(false);
        	forward.setPath("./mypage/MyPage_Remove.jsp");
        }
        else if(command.equals("/MyPageRemoveAction.me")){ //마이페이지 회원탈퇴 액션
        	action=new MyPageDeleteAction();
        	try {
        		forward=action.execute(request, response);
        	}catch(Exception e) {
        		e.printStackTrace();
        	}
        }
        else if(command.equals("/MyPagePW.me")){ //마이페이지 비밀번호 변경으로 이동
        	forward=new ActionForward();
        	forward.setRedirect(false);
        	forward.setPath("./mypage/MyPage_PW.jsp");
        }
        else if(command.equals("/MyPagePWAction.me")){ //마이페이지 비밀번호 변경 액션
        	action=new MyPageChangePwAction();
        	try {
        		forward=action.execute(request, response);
        	}catch(Exception e) {
        		e.printStackTrace();
        	}
        }
        else if(command.equals("/MyPageHome.me")){ //마이페이지 메인홈 이동
        	forward=new ActionForward();
        	forward.setRedirect(false);
        	forward.setPath("./mypage/MyPage_Home.jsp");
        }
        else if(command.equals("/MemberLogin.me")) {//LoginPage
        	forward=new ActionForward();
        	forward.setRedirect(false);
        	forward.setPath("./member/loginForm.jsp");
        }
        /*
         * 아래는 처리 부분
         */
        else if(command.equals("/MemberJoinAction.me")){ //Sign up Action page
        	action=new MemberJoinAction();
        	try {
        		forward=action.execute(request, response);
        	}catch(Exception e) {
        		e.printStackTrace();
        	}
        }
        else if(command.equals("/MemberLoginAction.me")) { //Login Action page
        	action=new MemberLoginAction();
        	try {
        		forward=action.execute(request, response);
        	}catch(Exception e) {
        		e.printStackTrace();
        	}
        }
        else if(command.equals("/MemberLogoutAction.me")) {//Logout Action Page
        	action=new MemberLogoutAction();
        	try {
        		forward=action.execute(request, response);
        	}catch(Exception e) {
        		e.printStackTrace();
        	}
        }
        else if(command.equals("/MemberFollowAction.me")) {//내일러 팔로우
        	action=new MemberFollowAction();
        	try {
        		forward=action.execute(request, response);
        	}catch(Exception e) {
        		e.printStackTrace();
        	}
        }
        
        if(forward!=null){ 
            if(forward.isRedirect()){ 
                response.sendRedirect(forward.getPath()); 
            }else{ 
                RequestDispatcher dispatcher=request.getRequestDispatcher(forward.getPath()); 
                dispatcher.forward(request, response); 
            } 
        } 
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{ 
        doProcess(request, response); 
    } 
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{ 
        doProcess(request, response); 
    } 
}
