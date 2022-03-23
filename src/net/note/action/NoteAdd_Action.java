package net.note.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.commons.action.Action;
import net.commons.action.ActionForward;
import net.note.db.Note_Add_DAO;
import net.note.db.Note_Plans_List_DAO;

public class NoteAdd_Action implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		ActionForward forward=new ActionForward();
		HttpSession session=request.getSession();
		int Note_ID=Integer.parseInt(request.getParameter("Note_ID"));
		
		System.out.println("노트 담기 ID : "+Note_ID);
		
		Note_Add_DAO add=new Note_Add_DAO();
		add.Note_Add(session.getAttribute("ID").toString(), Note_ID);

		return null;
	}
}
