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

public class All_Search_Action implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		ActionForward forward = new ActionForward();
		HttpSession session=request.getSession();
		String SearchWord = request.getParameter("search_word"); // 해당 뷰 폼에서 검색어를 가져온다.
		System.out.println("통합검색어 : "+SearchWord);
		
		Search_DAO search_dao=new Search_DAO();
		
		search_dao.Popular_keyword(SearchWord);
		
		ArrayList<Tour_Food_Bean> Tour=new ArrayList<Tour_Food_Bean>();
		ArrayList<Tour_Food_Bean> Food=new ArrayList<Tour_Food_Bean>();
		ArrayList<Note_Plans_List_Bean> Railro_Note=new ArrayList<Note_Plans_List_Bean>();
		ArrayList<MemberBean> Member=new ArrayList<MemberBean>();
		
		System.out.println("관광지");
		Tour=search_dao.getTour(SearchWord);
		
		System.out.println("음식점");
		Food=search_dao.getFood(SearchWord);
		
		System.out.println("노트");
		Railro_Note=search_dao.getNote(SearchWord);
		
		System.out.println("내일러");
		if(session.getAttribute("ID")!=null) {
			Member=search_dao.getMember(SearchWord, session.getAttribute("ID").toString());
		}
		else {
			Member=search_dao.getMember(SearchWord, null);
		}
		request.setAttribute("Tour", Tour);
		request.setAttribute("keyword", SearchWord);
		request.setAttribute("Food", Food);
		request.setAttribute("Note", Railro_Note);
		request.setAttribute("Member", Member);
		forward.setRedirect(false); // 접속 끊었다가 다시 연결하면서 새로운 정보를 보여준다.
		forward.setPath("./Search/All_Search.jsp");
		return forward;
	}

}