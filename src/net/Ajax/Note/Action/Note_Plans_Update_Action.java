package net.Ajax.Note.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.Ajax.Note.db.Note_Step2_Ajax_DAO;
import net.commons.action.Action;
import net.commons.action.ActionForward;

public class Note_Plans_Update_Action implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		ActionForward forward=new ActionForward();
		int StartIndex=Integer.parseInt(request.getParameter("StartIndex"));
		int EndIndex=Integer.parseInt(request.getParameter("EndIndex"));
		int day_orders=Integer.parseInt(request.getParameter("day_orders"));
		int sigungucode=Integer.parseInt(request.getParameter("sigungucode"));
    	int areacode=Integer.parseInt(request.getParameter("areacode"));
    	String date=request.getParameter("date");
    	String day=request.getParameter("day");
    	int Content_ID=Integer.parseInt(request.getParameter("Content_ID"));
    	int Content_Type_ID=Integer.parseInt(request.getParameter("Content_Type_ID"));
		int NoteID=Integer.parseInt(request.getParameter("NoteID"));
		
//    	System.out.println("day_orders : "+day_orders);
//    	System.out.println("sigungucode : "+sigungucode);
//    	System.out.println("areacode : "+areacode);
//    	System.out.println("date : "+date);
//    	System.out.println("day : "+day);
//    	System.out.println("id : "+Content_ID);
//    	System.out.println("type : "+Content_Type_ID);
		
		Note_Step2_Ajax_DAO update=new Note_Step2_Ajax_DAO();
		update.Plans_Update_Action(StartIndex, EndIndex, day_orders, sigungucode, areacode, date, day, Content_ID, Content_Type_ID, NoteID);
		
		return null;
	}
}
