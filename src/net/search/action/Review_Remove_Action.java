package net.search.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.commons.action.Action;
import net.commons.action.ActionForward;
import net.search.db.Review_DAO;

public class Review_Remove_Action implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		ActionForward forward = new ActionForward();
		HttpSession session=request.getSession();
		int review_num=Integer.parseInt(request.getParameter("num"));
		int contentid=Integer.parseInt(request.getParameter("contentid"));
		int contenttypeid=Integer.parseInt(request.getParameter("contenttypeid"));
		System.out.println("삭제할 리뷰 번호 : "+review_num);
		
		Review_DAO review_dao=new Review_DAO();
		
		int remove_count=review_dao.RemoveReview(contentid, contenttypeid, review_num);
		
		System.out.println("삭제된 리뷰 개수 : "+remove_count);
		
		return null;
	}

}