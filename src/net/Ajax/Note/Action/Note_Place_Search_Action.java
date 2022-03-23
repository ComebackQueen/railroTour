package net.Ajax.Note.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import net.Ajax.Note.db.Note_Step2_Ajax_DAO;
import net.commons.action.Action;
import net.commons.action.ActionForward;

public class Note_Place_Search_Action implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		ActionForward forward=new ActionForward();
		String keyword=request.getParameter("keyword");
		int sigungucode=Integer.parseInt(request.getParameter("sigungucode"));
		int areacode=Integer.parseInt(request.getParameter("areacode"));
		String search_type=request.getParameter("search_type");
		
		System.out.println("시군구 코드 : "+sigungucode);
		System.out.println("지역 코드 : "+areacode);
		System.out.println("키워드 : "+keyword);
		System.out.println("검색 구분 : "+search_type);
		
		Note_Step2_Ajax_DAO note_Step2_Ajax_DAO=new Note_Step2_Ajax_DAO();
		JSONObject json = null;
		if(search_type.compareTo("area_search")==0) { // 지역 내 검색 일시
			json=note_Step2_Ajax_DAO.Search_within_city(keyword, sigungucode, areacode);
		}
		else if(search_type.compareTo("all_search")==0) { // 전체 검색 일 시
			json=note_Step2_Ajax_DAO.Search_within_Do(keyword, sigungucode);
		}
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(json);
		return null;
	}
}
