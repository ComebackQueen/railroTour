package net.search.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.commons.action.Action;
import net.commons.action.ActionForward;

public class SearchFrontController extends HttpServlet implements Servlet{
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
        if(command.equals("/SearchAction.se")) { //검색 액션(망작)
        	action=new SearchAction();
        	try {
        		forward=action.execute(request, response);
        	}catch(Exception e) {
        		e.printStackTrace();
        	}
        }
        else if(command.equals("/Search.se")){ //검색 페이지 이동
        	forward=new ActionForward();
        	forward.setRedirect(false);
        	forward.setPath("./Search/Search_Form.jsp");
        }
        else if(command.equals("/Detail_Info.se")){ //음식점/관광지 상세보기 이동
        	action=new Detail_Info_Search_Action();
        	try {
        		forward=action.execute(request, response);
        	}catch(Exception e) {
        		e.printStackTrace();
        	}
        }
        else if(command.equals("/Review_Add.se")){ //리뷰 추가
        	action=new Review_Add_Action();
        	try {
        		forward=action.execute(request, response);
        	}catch(Exception e) {
        		e.printStackTrace();
        	}
        }
        else if(command.equals("/Review_Remove.se")){ //리뷰 삭제
        	action=new Review_Remove_Action();
        	try {
        		forward=action.execute(request, response);
        	}catch(Exception e) {
        		e.printStackTrace();
        	}
        }
        else if(command.equals("/Review_Additional.se")){ //리뷰 추가로 불러오기
        	action=new Review_Additional_Action();
        	try {
        		forward=action.execute(request, response);
        	}catch(Exception e) {
        		e.printStackTrace();
        	}
        }
        else if(command.equals("/Wordcloud.se")){//워드 클라우드 생성
        	action=new Wordcloud_Action();
        	try {
        		forward=action.execute(request, response);
        	}catch(Exception e) {
        		e.printStackTrace();
        	}
        }
        else if(command.equals("/All_Search.se")){//통합검색
        	action=new All_Search_Action();
        	try {
        		forward=action.execute(request, response);
        	}catch(Exception e) {
        		e.printStackTrace();
        	}
        }
        else if(command.equals("/Tour_Search.se")){//관광지 필터 검색
        	action=new Tour_Search_Action();
        	try {
        		forward=action.execute(request, response);
        	}catch(Exception e) {
        		e.printStackTrace();
        	}
        }
        else if(command.equals("/Food_Search.se")){//음식점 필터 검색
        	action=new Food_Search_Action();
        	try {
        		forward=action.execute(request, response);
        	}catch(Exception e) {
        		e.printStackTrace();
        	}
        }
        else if(command.equals("/RailroNote_Search.se")){//노트 필터 검색
        	action=new RailroNote_Search_Action();
        	try {
        		forward=action.execute(request, response);
        	}catch(Exception e) {
        		e.printStackTrace();
        	}
        }
        else if(command.equals("/Member_Search.se")){//내일러 필터 검색
        	action=new Member_Search_Action();
        	try {
        		forward=action.execute(request, response);
        	}catch(Exception e) {
        		e.printStackTrace();
        	}
        }
        else if(command.equals("/Top100_Search.se")){//Top 100 관광지/음식점 검색
        	action=new Top100_Search_Action();
        	try {
        		forward=action.execute(request, response);
        	}catch(Exception e) {
        		e.printStackTrace();
        	}
        }
        else if(command.equals("/Top100_Note.se")){//Top 100 내일로 노트 검색
        	action=new Top100_Note_Action();
        	try {
        		forward=action.execute(request, response);
        	}catch(Exception e) {
        		e.printStackTrace();
        	}
        }
        else if(command.equals("/Top100_Member.se")){//Top 100 내일러 멤버 검색
        	action=new Top100_Member_Action();
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