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

public class Tour_Search_Action implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		ActionForward forward = new ActionForward();
		HttpSession session=request.getSession();
		String SearchWord = request.getParameter("search_word"); // 해당 뷰 폼에서 검색어를 가져온다.
		int page_num=Integer.parseInt(request.getParameter("page_num"));
		System.out.println("관광지 검색어 : "+SearchWord);
		
		Search_DAO search_dao=new Search_DAO();
		search_dao.Popular_keyword(SearchWord);
		ArrayList<Tour_Food_Bean> Tour=new ArrayList<Tour_Food_Bean>();

		Tour=search_dao.Filter_Search(page_num, SearchWord, 12);
		
		request.setAttribute("data", Tour);
		request.setAttribute("keyword", SearchWord);
		request.setAttribute("page_num", page_num);
		forward.setRedirect(false); // 접속 끊었다가 다시 연결하면서 새로운 정보를 보여준다.
		forward.setPath("./Search/Tour_Search.jsp");
		return forward;
	}

}