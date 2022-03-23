package net.Ajax.Note.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.Ajax.Note.db.Note_Step2_Ajax_DAO;
import net.commons.action.Action;
import net.commons.action.ActionForward;

public class Note_Name_UpdateAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		ActionForward forward=new ActionForward();
		String NoteName=request.getParameter("NoteName");
		String NoteID=request.getParameter("NoteID");
		
		System.out.println(NoteName+NoteID);
		
		Note_Step2_Ajax_DAO NoteName_Update=new Note_Step2_Ajax_DAO();
		
		NoteName_Update.Note_Name_Update(Integer.parseInt(NoteID), NoteName);
		
		
		return null;
	}

}
