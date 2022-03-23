package net.Ajax.Note.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import net.Ajax.Note.db.Note_Step2_Ajax_DAO;
import net.commons.action.Action;
import net.commons.action.ActionForward;

public class Note_Plans_RESET_Action implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		ActionForward forward=new ActionForward();
		int travel_id=Integer.parseInt(request.getParameter("NoteID"));
		int day_orders=Integer.parseInt(request.getParameter("day_orders"));
		
		Note_Step2_Ajax_DAO reset=new Note_Step2_Ajax_DAO();

		reset.Plans_Reset_Action(travel_id, day_orders);
		return null;
	}
}
