package net.note.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.commons.action.Action;
import net.commons.action.ActionForward;
import net.note.db.Note_Plans_List_Bean;
import net.note.db.Note_Plans_List_DAO;

public class Note_Plans_List_Action implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		ActionForward forward=new ActionForward();
		HttpSession session=request.getSession();
		String ID=(String) session.getAttribute("ID");
		
		System.out.println("ID : "+ID);
		
		ArrayList<Note_Plans_List_Bean> Plans_List=new ArrayList<Note_Plans_List_Bean>(); //각 노트 정보들을 담을 배열
		Note_Plans_List_DAO Select_Note_List=new Note_Plans_List_DAO();
		
		Plans_List=Select_Note_List.Select_Note_List(ID);
		
		request.setAttribute("PlanList", Plans_List);
        forward.setRedirect(false); // true시 접속 끊었다가 다시 연결하면서 새로운 정보를 보여준다.
        forward.setPath("./planner_writer/Railro_Note_Plans_List.jsp"); //노트 목록으로 이동
		return forward;
	}

}
