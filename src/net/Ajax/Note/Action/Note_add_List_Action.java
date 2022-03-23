package net.Ajax.Note.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.json.simple.JSONObject;

import net.Ajax.Note.db.Note_Step2_Ajax_DAO;
import net.commons.action.Action;
import net.commons.action.ActionForward;
import net.note.db.Note_Plans_List_DAO;

public class Note_add_List_Action implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		ActionForward forward=new ActionForward();
		HttpSession session=request.getSession();
		int note_cnt=Integer.parseInt(request.getParameter("Note_Count"));
		String id=(String)session.getAttribute("ID");
		
		System.out.println("현재 노트 개수 : "+note_cnt);
		
		Note_Plans_List_DAO note_add_List=new Note_Plans_List_DAO();
		JSONObject json=note_add_List.Note_Add_List(note_cnt, id);
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(json);
		return null;
	}
}