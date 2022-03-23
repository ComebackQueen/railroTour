package net.search.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import net.commons.action.Action;
import net.commons.action.ActionForward;
import net.search.db.Review_DAO;

public class Review_Additional_Action implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		ActionForward forward = new ActionForward();
		HttpSession session=request.getSession();
		JSONObject json=new JSONObject();
		
		int num=Integer.parseInt(request.getParameter("num"));
		int contenttypeid=Integer.parseInt(request.getParameter("contenttypeid"));
		int contentid=Integer.parseInt(request.getParameter("contentid"));
		
		Review_DAO review_dao=new Review_DAO();
		
		json=review_dao.getAdditional_Review(contentid, contenttypeid, num*5);
		
		System.out.println("보내기전 최종 JSON : "+json);
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(json);
		return null;
	}

}