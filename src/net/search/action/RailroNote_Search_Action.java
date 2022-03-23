package net.search.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.commons.action.Action;
import net.commons.action.ActionForward;
import net.note.db.Note_Plans_List_Bean;
import net.search.db.Search_DAO;
import net.search.db.Tour_Food_Bean;

public class RailroNote_Search_Action implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		ActionForward forward = new ActionForward();
		HttpSession session=request.getSession();
		String SearchWord = request.getParameter("search_word"); // 해당 뷰 폼에서 검색어를 가져온다.
		int page_num=Integer.parseInt(request.getParameter("page_num"));
		System.out.println("내일로 노트 검색어 : "+SearchWord);
		
		Search_DAO search_dao=new Search_DAO();
		search_dao.Popular_keyword(SearchWord);
		ArrayList<Note_Plans_List_Bean> Railro_Note=new ArrayList<Note_Plans_List_Bean>();
		
		int totalcount=search_dao.Filter_Note_Count(SearchWord);
		Railro_Note=search_dao.Filter_RailroNote_Search(SearchWord, page_num, totalcount);
		
		request.setAttribute("data", Railro_Note);
		request.setAttribute("totalcount", totalcount);
		request.setAttribute("keyword", SearchWord);
		request.setAttribute("page_num", page_num);
		forward.setRedirect(false); // 접속 끊었다가 다시 연결하면서 새로운 정보를 보여준다.
		forward.setPath("./Search/RailroNote_Search.jsp");
		return forward;
	}

}