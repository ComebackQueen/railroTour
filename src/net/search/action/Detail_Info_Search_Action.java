package net.search.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.commons.action.Action;
import net.commons.action.ActionForward;
import net.search.db.DetailInfo_Bean;
import net.search.db.DetailInfo_DAO;
import net.search.db.DetailInfo_Review;
import net.search.db.DetailInfo_analysis;

public class Detail_Info_Search_Action implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		ActionForward forward = new ActionForward();
		int contentid=Integer.parseInt(request.getParameter("contentid"));
		int contenttypeid=Integer.parseInt(request.getParameter("contenttypeid"));
		System.out.println("contentid : "+contentid);
		System.out.println("contenttypeid : "+contenttypeid);
		
		DetailInfo_DAO detailinfo_dao=new DetailInfo_DAO();
		DetailInfo_Bean DetailInfo_bean=new DetailInfo_Bean();
		DetailInfo_bean=detailinfo_dao.getInfo(contentid, contenttypeid);
		
		
		DetailInfo_bean.setContentid(contentid);
		DetailInfo_bean.setContenttypeid(contenttypeid);
		System.out.println("완료");
		
		request.setAttribute("Info", DetailInfo_bean);
		
		//리뷰 데이터들
		ArrayList<DetailInfo_Review> reviews=new ArrayList<DetailInfo_Review>();
		reviews=detailinfo_dao.getReviews(contentid, contenttypeid);
		
		request.setAttribute("reviews", reviews);
		
		//리뷰 데이터 분석한 결과
		DetailInfo_analysis analysis=new DetailInfo_analysis();
		analysis=detailinfo_dao.getAnalysis_Result(contentid, contenttypeid);
		
		request.setAttribute("review_analysis", analysis);
		forward.setRedirect(false); // 접속 끊었다가 다시 연결하면서 새로운 정보를 보여준다.
		forward.setPath("./Search/Detail_Info.jsp");
		return forward;
	}

}
