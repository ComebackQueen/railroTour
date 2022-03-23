package net.around_me.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.commons.action.Action;
import net.commons.action.ActionForward;
import net.search.action.SearchAction;

/**
 * Servlet implementation class AroundFrontController
 */
@WebServlet("/AroundFrontController")
public class AroundFrontController extends HttpServlet {
static final long serialVersionUID=1L;
	
	
    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{ 
    	
    	String RequestURI=request.getRequestURI(); 
        String contextPath=request.getContextPath(); 
        String command = RequestURI.substring(contextPath.length()); 
        ActionForward forward = null; 
        Action action = null; 
        System.out.println("일단 내주변 들어옴");
        /*
         * 페이지 이동부분
         */
        if(command.equals("/AroundAction.am")) { //검색 액션
        	action=new AroundAction();
        	try {
        		forward=action.execute(request, response);
        	}catch(Exception e) {
        		e.printStackTrace();
        	}
        } else if(command.equals("/DetailsAroundAction.am")) { //검색 액션
        	action=new DetailsAroundAction();
        	try {
        		forward=action.execute(request, response);
        	}catch(Exception e) {
        		e.printStackTrace();
        	}
        } else if(command.equals("/ImageAroundAction.am")) { //검색 액션
        	action=new ImageAroundAction();
        	try {
        		forward=action.execute(request, response);
        	}catch(Exception e) {
        		e.printStackTrace();
        	}
        }
        else if(command.equals("/Around.am")){ //검색 페이지 이동 사실상 빼야핡같지만 잘몰라서 못빼겠다.
        	forward=new ActionForward();
        	forward.setRedirect(false);
        	forward.setPath("./Around/Around_Form.jsp");
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
