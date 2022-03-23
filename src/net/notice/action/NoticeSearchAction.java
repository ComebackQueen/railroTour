package net.notice.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.commons.action.Action;
import net.commons.action.ActionForward;
import net.notice.db.NoticeDAO;

public class NoticeSearchAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession();
		
		NoticeDAO noticedao = new NoticeDAO();
		List noticelist = new ArrayList();
		
		int page =1;
		int limit = 10;
		
		if(request.getParameter("page") != null){
			page = Integer.parseInt(request.getParameter("page"));
		}
		String opt = request.getParameter("opt");
		String condition = request.getParameter("condition");
		
		System.out.println(opt);
		System.out.println(condition);
		HashMap<String, Object> listOpt = new HashMap<String, Object>();
		listOpt.put("opt", opt);
		listOpt.put("condition", condition);
		int listcount = noticedao.getSearchCount(listOpt); //총 리스트 수
		noticelist = noticedao.getSearchNoticeList(page, limit, listOpt); // 리스트
		
		//총 페이지 수
		int maxpage=(int)((double)listcount/limit+0.95); // 0.95를 더해 올림처리
		//현재 페이지에 보여줄 시작 페이지 수(1,11,21....)
		int startpage = (((int)((double)page/limit + 0.9)) - 1) * limit + 1;
		//현재 페이지에 보여줄 마지막 페이지 수(10,20,30...)
		int endpage = maxpage;
		
		if(endpage < startpage+limit-1) endpage=maxpage;
		
		request.setAttribute("page", page); // 현재 페이지 수
		request.setAttribute("maxpage", maxpage); // 최대 페이지 수
		request.setAttribute("startpage", startpage); // 현재 페이지에 표시할 첫 페이지 수
		request.setAttribute("endpage", endpage); // 현재 페이지에 표시할 끝 페이지 수
		request.setAttribute("listcount", listcount); //글 수
		request.setAttribute("limit", limit);
		request.setAttribute("noticelist", noticelist);
		request.setAttribute("opt", opt);
		request.setAttribute("condition", condition);
		
		forward.setRedirect(false);
		forward.setPath("./Noticelist/Notice_searchResult.jsp");
		return forward;
	}
}
