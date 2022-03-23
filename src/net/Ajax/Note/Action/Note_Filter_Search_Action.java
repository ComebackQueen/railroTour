package net.Ajax.Note.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import net.Ajax.Note.db.Note_Step2_Ajax_DAO;
import net.commons.action.Action;
import net.commons.action.ActionForward;

public class Note_Filter_Search_Action implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		ActionForward forward=new ActionForward();
		int contenttypeid=Integer.parseInt(request.getParameter("contenttypeid"));
		int sigungucode=Integer.parseInt(request.getParameter("sigungucode"));
		int areacode=Integer.parseInt(request.getParameter("areacode"));
		JSONObject json = null;
		
		System.out.println("필터명 : "+contenttypeid);
		System.out.println("시군구 코드 : "+sigungucode);
		System.out.println("지역 코드 : "+areacode);
		
		Note_Step2_Ajax_DAO note_Step2_Ajax_DAO=new Note_Step2_Ajax_DAO();
		
		if(contenttypeid==0) { //전체 검색
			json=note_Step2_Ajax_DAO.ALL_Select_Action(contenttypeid, sigungucode, areacode);
		}
		else if(contenttypeid==12) { //관광지 검색
			//json=note_Step2_Ajax_DAO.Filter_Tour_Select(contenttypeid, sigungucode, areacode);
			json=note_Step2_Ajax_DAO.TourAPI_Select(contenttypeid, sigungucode, areacode);
		}
		else if(contenttypeid==39) { //음식점 검색
			//json=note_Step2_Ajax_DAO.Filter_Tour_Select(contenttypeid, sigungucode, areacode);
			json=note_Step2_Ajax_DAO.TourAPI_Select(contenttypeid, sigungucode, areacode);
		}
		else {//여행 바구니 검색
			
		}
		
		System.out.println("필터 검색 : "+json);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(json);
		return null;
	}
}
