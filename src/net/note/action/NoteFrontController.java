package net.note.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.Ajax.Note.Action.NoteImg_Change_Action;
import net.Ajax.Note.Action.NoteMemo_Action;
import net.Ajax.Note.Action.Note_Filter_Search_Action;
import net.Ajax.Note.Action.Note_Like_Action;
import net.Ajax.Note.Action.Note_More_Info_SelectAction;
import net.Ajax.Note.Action.Note_Name_UpdateAction;
import net.Ajax.Note.Action.Note_Place_Search_Action;
import net.Ajax.Note.Action.Note_Plans_Delete_Action;
import net.Ajax.Note.Action.Note_Plans_Info2_Import_Action;
import net.Ajax.Note.Action.Note_Plans_RESET_Action;
import net.Ajax.Note.Action.Note_Plans_Update_Action;
import net.Ajax.Note.Action.Note_add_List_Action;
import net.Ajax.Note.Action.Note_plans_Save_Action;
import net.commons.action.Action;
import net.commons.action.ActionForward;
import net.member.action.MemberJoinAction;

public class NoteFrontController extends HttpServlet implements Servlet{
	static final long serialVersionUID=1L;
    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{ 
        String RequestURI=request.getRequestURI(); 
        String contextPath=request.getContextPath(); 
        String command = RequestURI.substring(contextPath.length()); 
        ActionForward forward = null; 
        Action action = null; 
        HttpSession session = request.getSession();
        String id = (String)session.getAttribute("id");
    	
        /*
         * 페이지 이동부분
         */
        
        if(command.equals("/Railro_Note_Step1.pl")) { //내일로노트 스탭 1 이동
//        	if(id == null){
//    			response.setContentType("text/html;charset=UTF-8");
//    			PrintWriter out = response.getWriter();
//    			out.println("<script>");
//    			out.println("alert('로그인 후 이용해주세요.');");
//    			out.println("history.back();");
//    			out.println("</script>");
//    			out.close();
//    			return;
//    		}
        	forward=new ActionForward();
        	forward.setRedirect(false);
        	forward.setPath("./planner_writer/Railro_Note_Step1.jsp");
        }
        else if(command.equals("/Note_Step2_SelectAction.pl")) {
        	action=new Note_Step2_SelectAction();
        	try {
        		forward=action.execute(request, response);
        	}catch(Exception e) {
        		e.printStackTrace();
        	}
        }
        else if(command.equals("/Note1InsertAction.pl")){ //Sign up Action page
        	action=new Note_Step1_InsertAction();
        	try {
        		forward=action.execute(request, response);
        	}catch(Exception e) {
        		e.printStackTrace();
        	}
        }
        else if(command.equals("/Note_Name_Update.pl")) {
        	action=new Note_Name_UpdateAction();
        	try {
        		forward=action.execute(request, response);
        	}catch(Exception e) {
        		e.printStackTrace();
        	}
        }
        else if(command.equals("/Note_More_Info.pl")) {
        	action=new Note_More_Info_SelectAction();
        	try {
        		forward=action.execute(request, response);
        	}catch(Exception e) {
        		e.printStackTrace();
        	}
        }
        else if(command.equals("/Note_Filter_Search.pl")){
        	action=new Note_Filter_Search_Action();
        	try {
        		forward=action.execute(request, response);
        	}catch(Exception e) {
        		e.printStackTrace();
        	}
        }
        else if(command.equals("/Note_Place_Search.pl")) {
        	action=new Note_Place_Search_Action();
        	try {
        		forward=action.execute(request, response);
        	}catch(Exception e) {
        		e.printStackTrace();
        	}
        }
        else if(command.equals("/Note_Plans_Save.pl")) {
        	action=new Note_plans_Save_Action();
        	try {
        		forward=action.execute(request, response);
        	}catch(Exception e) {
        		e.printStackTrace();
        	}
        }
        else if(command.equals("/Note_Plans_Update.pl")) {
        	action=new Note_Plans_Update_Action();
        	try {
        		forward=action.execute(request, response);
        	}catch(Exception e) {
        		e.printStackTrace();
        	}
        }
        else if(command.equals("/Note_Plans_RESET.pl")) {
        	action=new Note_Plans_RESET_Action();
        	try {
        		forward=action.execute(request, response);
        	}catch(Exception e) {
        		e.printStackTrace();
        	}
        }
        else if(command.equals("/Note_Plans_Delete.pl")) {
        	action=new Note_Plans_Delete_Action();
        	try {
        		forward=action.execute(request, response);
        	}catch(Exception e) {
        		e.printStackTrace();
        	}
        }
        else if(command.equals("/Note_Plans_Info2_Import.pl")) { //노트 파트2 수정 하기위해 데이터 가져오는 액션
        	action=new Note_Plans_Info2_Import_Action();
        	try {
        		forward=action.execute(request, response);
        	}catch(Exception e) {
        		e.printStackTrace();
        	}
        }
        else if(command.equals("/Note_Plans_List.pl")) { //노트 목록 가져오는 액션
        	if(id == null){
    			response.setContentType("text/html;charset=UTF-8");
    			PrintWriter out = response.getWriter();
    			out.println("<script>");
    			out.println("alert('로그인 후 이용해주세요.');");
    			out.println("history.back();");
    			out.println("</script>");
    			out.close();
    			return;
    		}
        	
        	action=new Note_Plans_List_Action();
        	try {
        		forward=action.execute(request, response);
        	}catch(Exception e) {
        		e.printStackTrace();
        	}
        }
        else if(command.equals("/Note_Remove.pl")) { //노트 삭제
        	action=new Note_Remove_Action();
        	try {
        		forward=action.execute(request, response);
        	}catch(Exception e) {
        		e.printStackTrace();
        	}
        }
        else if(command.equals("/Note_add_List.pl")) { //추가 노트 목록
        	action=new Note_add_List_Action();
        	try {
        		forward=action.execute(request, response);
        	}catch(Exception e) {
        		e.printStackTrace();
        	}
        }
        else if(command.equals("/NoteDetail.pl")) { //추가 노트 목록
        	action=new Note_Detail_Action();
        	try {
        		forward=action.execute(request, response);
        	}catch(Exception e) {
        		e.printStackTrace();
        	}
        }
        else if(command.equals("/NoteLike.pl")) { //노트 좋아요/좋아요 취소
        	action=new Note_Like_Action();
        	try {
        		forward=action.execute(request, response);
        	}catch(Exception e) {
        		e.printStackTrace();
        	}
        }
        else if(command.equals("/NoteAdd.pl")) { //상대 노트 담기
        	action=new NoteAdd_Action();
        	try {
        		forward=action.execute(request, response);
        	}catch(Exception e) {
        		e.printStackTrace();
        	}
        }
        else if(command.equals("/NoteImg_Change.pl")) { //노트 커버사진 변경
        	action=new NoteImg_Change_Action();
        	try {
        		forward=action.execute(request, response);
        	}catch(Exception e) {
        		e.printStackTrace();
        	}
        }
        else if(command.equals("/NoteMemo_Save.pl")) { //노트 장소 메모 저장
        	action=new NoteMemo_Action();
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
