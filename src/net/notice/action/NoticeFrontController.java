package net.notice.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.commons.action.Action;
import net.commons.action.ActionForward;

public class NoticeFrontController extends HttpServlet implements Servlet{
	static final long serialVersionUID=1L;
    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{ 
        String RequestURI=request.getRequestURI(); 
        String contextPath=request.getContextPath(); 
        String command = RequestURI.substring(contextPath.length()); 
        ActionForward forward = null; 
        Action action = null;
        HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
        
        // 공지사항 페이지로 이동
        if(command.equals("/NoticeList.no")) {
        	action = new NoticeListAction();
        	try{
        		forward = action.execute(request, response);
        	} catch(Exception e){
        		e.printStackTrace();
        	} 
        }
        else if(command.equals("/NoticeWrite.no")){
        	if(!(id.equals("admin"))){
    			response.setContentType("text/html;charset=UTF-8");
    			PrintWriter out = response.getWriter();
    			out.println("<script>");
    			out.println("alert('글쓰기 권한이 없습니다.');");
    			out.println("location.href='./NoticeList.no'");
    			out.println("</script>");
    			out.close();
    			return;
    		}
        	forward = new ActionForward();
        	forward.setRedirect(false);
        	forward.setPath("./Noticelist/Notice_write.jsp");
        } else if(command.equals("/NoticeModify.no")){
        	action=new NoticeModifyView();
        	try{
        		forward = action.execute(request, response);
        	} catch(Exception e){
        		e.printStackTrace();
        	} 
        } else if(command.equals("/NoticeAddAction.no")){
    		action=new NoticeAddAction();
    		try{
        		forward = action.execute(request, response);
        	} catch(Exception e){
        		e.printStackTrace();
        	}
    	} else if(command.equals("/NoticeModifyAction.no")){
    		action=new NoticeModifyAction();
    		try{
        		forward = action.execute(request, response);
        	} catch(Exception e){
        		e.printStackTrace();
        	}
    	} else if(command.equals("/NoticeDelete.no")){
    		action=new NoticeDeleteAction();
    		try{
        		forward = action.execute(request, response);
        	} catch(Exception e){
        		e.printStackTrace();
        	}
    	} else if(command.equals("/NoticeDetailAction.no")){
    		action=new NoticeDetailAction();
    		try{
        		forward = action.execute(request, response);
        	} catch(Exception e){
        		e.printStackTrace();
        	}
    	} else if(command.equals("/NoticeSearchAction.no")){
    		action=new NoticeSearchAction();
    		try{
    			forward = action.execute(request, response);
    		} catch(Exception e){
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
