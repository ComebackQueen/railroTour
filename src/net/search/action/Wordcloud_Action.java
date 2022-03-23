package net.search.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import net.commons.action.Action;
import net.commons.action.ActionForward;
import net.search.db.Review_DAO;

public class Wordcloud_Action implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		ActionForward forward = new ActionForward();
		HttpSession session=request.getSession();
		
		int contenttypeid=Integer.parseInt(request.getParameter("contenttypeid"));
		int contentid=Integer.parseInt(request.getParameter("contentid"));
		
		Review_DAO wordcloud=new Review_DAO();
		JSONObject json=new JSONObject();
		json=wordcloud.Wordcloud(contentid, contenttypeid);
		System.out.println("워드클라우드 : "+json);
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(json);
		return null;
	}

}