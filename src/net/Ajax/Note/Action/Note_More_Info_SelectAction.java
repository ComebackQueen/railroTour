package net.Ajax.Note.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import net.Ajax.Note.db.Note_Step2_Ajax_DAO;
import net.commons.action.Action;
import net.commons.action.ActionForward;

public class Note_More_Info_SelectAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		ActionForward forward=new ActionForward();
		
		Note_Step2_Ajax_DAO note_Step2_Ajax_DAO=new Note_Step2_Ajax_DAO();
		
		int content_id=Integer.parseInt(request.getParameter("contentid"));
		int content_Type_id=Integer.parseInt(request.getParameter("contenttypeid"));
		
		JSONObject json=note_Step2_Ajax_DAO.More_Info_Select(content_id, content_Type_id);
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(json);
		return null;
	}
}
