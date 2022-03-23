package net.search.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.commons.action.Action;
import net.commons.action.ActionForward;
import net.search.db.Search_DAO;
import net.search.db.Top100_Member_Bean;

public class Top100_Member_Action implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		ActionForward forward = new ActionForward();
		HttpSession session=request.getSession();
		int page_num=Integer.parseInt(request.getParameter("page_num"));
		
		Search_DAO search_dao=new Search_DAO();
		
		ArrayList<Top100_Member_Bean> data=new ArrayList<Top100_Member_Bean>();
		
		data=search_dao.getTop_Member(page_num);
		
		request.setAttribute("data", data);
		request.setAttribute("page_num", page_num);
		forward.setRedirect(false); // 접속 끊었다가 다시 연결하면서 새로운 정보를 보여준다.
		forward.setPath("./Search/Top100_Member.jsp");
		return forward;
	}

}