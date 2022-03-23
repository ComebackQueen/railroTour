package net.search.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.commons.action.Action;
import net.commons.action.ActionForward;
import net.search.db.Search_DAO;
import net.search.db.Top100_Bean;

public class Top100_Search_Action implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		ActionForward forward = new ActionForward();
		HttpSession session=request.getSession();
		int contenttypeid=Integer.parseInt(request.getParameter("contenttypeid"));
		int page_num=Integer.parseInt(request.getParameter("page_num"));

		Search_DAO search=new Search_DAO();
		
		Top100_Bean[] top100=new Top100_Bean[20];
		
		top100=search.getTop100(contenttypeid, page_num);
		
		request.setAttribute("page_num", page_num);
		request.setAttribute("top100", top100);
		request.setAttribute("contenttypeid", contenttypeid);
		forward.setRedirect(false); // 접속 끊었다가 다시 연결하면서 새로운 정보를 보여준다.
		forward.setPath("./Search/Top100.jsp");
		return forward;
	}

}