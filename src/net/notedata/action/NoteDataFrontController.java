package net.notedata.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.commons.action.Action;
import net.commons.action.ActionForward;

/**
 * Servlet implementation class AroundFrontController
 */
@WebServlet("/NoteDataFrontController")
public class NoteDataFrontController extends HttpServlet {
static final long serialVersionUID=1L;
	
	
    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{ 
    	
    	String RequestURI=request.getRequestURI(); 
        String contextPath=request.getContextPath(); 
        String command = RequestURI.substring(contextPath.length()); 
        ActionForward forward = null; 
        Action action = null; 
        System.out.println("일단 노트데이터 들어옴");
        /*
         * 페이지 이동부분
         */
        if(command.equals("/NoteDataAction.nd")) { //검색 액션
        	action=new NoteDataAction();
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
