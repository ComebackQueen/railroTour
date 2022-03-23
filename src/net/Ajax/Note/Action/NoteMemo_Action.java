package net.Ajax.Note.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import net.Ajax.Note.db.Note_Memo_DAO;
import net.commons.action.Action;
import net.commons.action.ActionForward;
import net.note.db.Note_Plans_List_DAO;

public class NoteMemo_Action implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		ActionForward forward=new ActionForward();
		HttpSession session=request.getSession();
		
		int Note_ID=Integer.parseInt(request.getParameter("Note_ID"));
		int orders=Integer.parseInt(request.getParameter("orders"));
		int day_orders=Integer.parseInt(request.getParameter("day_orders"));
		String memo=request.getParameter("memo");
		
		Note_Memo_DAO memo_dao=new Note_Memo_DAO();
		memo_dao.Memo_Update(Note_ID, orders, day_orders, memo);
		
		return null;
	}
}