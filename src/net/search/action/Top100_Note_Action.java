package net.search.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.commons.action.Action;
import net.commons.action.ActionForward;
import net.member.db.MemberBean;
import net.note.db.Note_Plans_List_Bean;
import net.search.db.Search_DAO;
import net.search.db.Tour_Food_Bean;

public class Top100_Note_Action implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		ActionForward forward = new ActionForward();
		HttpSession session=request.getSession();
		int page_num=Integer.parseInt(request.getParameter("page_num"));
		
		Search_DAO search_dao=new Search_DAO();
		
		Note_Plans_List_Bean[] data=new Note_Plans_List_Bean[20];
		
		data=search_dao.getTop_Note(page_num);
		
		request.setAttribute("page_num", page_num);
		request.setAttribute("data", data);
		forward.setRedirect(false); // 접속 끊었다가 다시 연결하면서 새로운 정보를 보여준다.
		forward.setPath("./Search/Top100_Note.jsp");
		return forward;
	}

}