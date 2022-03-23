package net.Ajax.Note.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.Ajax.Note.db.Note_Like_DAO;
import net.Ajax.Note.db.Note_Step2_Ajax_DAO;
import net.commons.action.Action;
import net.commons.action.ActionForward;

public class Note_Like_Action implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		ActionForward forward=new ActionForward();
		int Like_YN=Integer.parseInt(request.getParameter("YN")); //1이면 좋아요, 0이면 좋아요 취소
		int NoteID=Integer.parseInt(request.getParameter("Note_ID"));
		HttpSession session=request.getSession();
		
		Note_Like_DAO like=new Note_Like_DAO();
		
		if(Like_YN==1) {
			like.Note_Like_UP(NoteID, session.getAttribute("ID").toString());
		}
		else if(Like_YN==0) {
			like.Note_Like_DOWN(NoteID, session.getAttribute("ID").toString());
		}
		
		return null;
	}

}
