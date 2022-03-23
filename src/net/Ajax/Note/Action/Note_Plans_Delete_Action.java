package net.Ajax.Note.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.Ajax.Note.db.Note_Step2_Ajax_DAO;
import net.commons.action.Action;
import net.commons.action.ActionForward;

public class Note_Plans_Delete_Action implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		ActionForward forward=new ActionForward();
		int travel_id=Integer.parseInt(request.getParameter("NoteID"));
		int day_orders=Integer.parseInt(request.getParameter("day_orders"));
		int position=Integer.parseInt(request.getParameter("position"));
		
//		System.out.println("travel_id : "+travel_id);
//		System.out.println("day_orders : "+day_orders);
//		System.out.println("position : "+position);
		
		Note_Step2_Ajax_DAO delete=new Note_Step2_Ajax_DAO();
		delete.Plans_Delete_Action(travel_id, day_orders, position);
		
		return null;
	}
}
